package br.com.androidmaster.bottomsheets

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.*
import br.com.androidmaster.bottomsheets.util.KeyboardUtils
import com.camerash.toggleedittextview.ToggleEditTextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import kotlin.concurrent.schedule
import kotlin.properties.Delegates


class TestBottomSheetFragment() : BottomSheetDialogFragment(){

    var peekHeight by Delegates.notNull<Int>()
    var currentScrollPosition = 0f


    val myString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In mattis, " +
            "justo quis vulputate venenatis, mi felis viverra arcu, sed blandit tellus nisl " +
            "vitae tortor. Etiam nec ligula nec felis scelerisque consectetur. Vestibulum at."

    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var bottomSheet: FrameLayout

    lateinit var toggleEditText: ToggleEditTextView
    lateinit var txtDate: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        peekHeight = resources.getDimensionPixelSize(R.dimen.peekHeight_small)

        val mView = layoutInflater.inflate(R.layout.bottom_sheet, container, false)

        mView.findViewById<Button>(R.id.button).setOnClickListener {
            Toast.makeText(requireContext(), "TESTE", Toast.LENGTH_SHORT).show()
        }

        return mView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialogInterface ->
            bottomSheetDialog = dialogInterface as BottomSheetDialog
            bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout

            setupContent(bottomSheet)
            setupFullHeight(bottomSheet, bottomSheetDialog)
        }
        return dialog
    }


    private fun findViews(){
        toggleEditText = view?.findViewById(R.id.toggleEditText)!!
        txtDate = view?.findViewById(R.id.txtDate)!!
        toggleEditText.textView.ellipsize = TextUtils.TruncateAt.END
        toggleEditText.editText.filters = arrayOf(InputFilter.LengthFilter(800))
        toggleEditText.textView.filters = arrayOf(InputFilter.LengthFilter(800))
        toggleEditText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE)
        toggleEditText.editText.isSingleLine = false;
    }

    private fun setupContent(bottomSheet: FrameLayout){
        findViews()
        toggleEditText.setText(myString)


        colapse(toggleEditText)
        expand(toggleEditText.textView, WindowManager.LayoutParams.MATCH_PARENT)
        expand(toggleEditText.editText, WindowManager.LayoutParams.MATCH_PARENT)

        BottomSheetBehavior.from(bottomSheet).addBottomSheetCallback(bottomSheetCallback)

        KeyboardUtils.addKeyboardToggleListener(activity){ isVisible ->
            if(!isVisible){
                val text = toggleEditText.editText.text.replace(Regex("[\r\n]+"), "\n\n");
                toggleEditText.editText.setText(text)
                toggleEditText.setEditing(false, true)
            }
        }

        toggleEditText.textView.setOnClickListener {
            toggleEditText.setEditing(true, true)
            toggleEditText.editText.requestFocus()
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
            KeyboardUtils.toggleKeyboardVisibility(requireContext())
        }
    }

    private val bottomSheetCallback = object: BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(bottomSheet: View, newState: Int) = when(newState){
            BottomSheetBehavior.STATE_COLLAPSED -> {
                toggleEditText.setEditing(false, true)
                colapse(toggleEditText)
                txtDate.gravity = Gravity.TOP
            }
            else -> {}
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            val difference = currentScrollPosition - slideOffset

            if (difference != 0f) {
                if (difference < 0) {
                    Log.i(TAG, "onSlide: UP")
                    txtDate.gravity = Gravity.BOTTOM
                    expand(toggleEditText)
                } else {
                    Log.i(TAG, "onSlide: DOWN")
                    Timer("onSlideDown",false).schedule(300){
                        txtDate.gravity = Gravity.TOP
                    }

                    toggleEditText.textView.scrollTo(0,0)
                    toggleEditText.editText.scrollTo(0,0)
                    colapse(toggleEditText)
                }
            }

            currentScrollPosition = slideOffset
        }

    }


    private fun expand(view: View){
        val layoutParams = view.layoutParams
        layoutParams.height = 0
        view.layoutParams = layoutParams
    }

    private fun expand(view: View, value: Int){
        val layoutParams = view.layoutParams
        layoutParams.height = value
        view.layoutParams = layoutParams
    }

    private fun colapse(view: View){
        val layoutParams = view.layoutParams
        layoutParams.height = resources.getDimension(R.dimen.textViewColapsed).toInt()
        view.layoutParams = layoutParams
    }



    private fun setupFullHeight(bottomSheet: FrameLayout, bottomSheetDialog: BottomSheetDialog) {
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        bottomSheetDialog.behavior.peekHeight = peekHeight
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }


    companion object{
        fun newInstance() = TestBottomSheetFragment()
        const val TAG = "TestBottomSheetFragment"
    }

}