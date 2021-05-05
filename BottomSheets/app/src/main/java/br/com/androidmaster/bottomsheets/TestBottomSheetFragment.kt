package br.com.androidmaster.bottomsheets

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import android.text.InputType
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import br.com.androidmaster.bottomsheets.databinding.BottomSheetBinding
import br.com.androidmaster.bottomsheets.util.KeyboardUtils
import com.camerash.toggleedittextview.ToggleEditTextView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.w3c.dom.Text
import kotlin.properties.Delegates


class TestBottomSheetFragment() : BottomSheetDialogFragment(){

    var peekHeight by Delegates.notNull<Int>()

    var currentPosition = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        peekHeight = resources.getDimensionPixelSize(R.dimen.peekHeight_small)
        return layoutInflater.inflate(R.layout.bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener {
            Toast.makeText(requireContext(), "TESTE", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialogInterface ->

            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        bottomSheetDialog.behavior.peekHeight = peekHeight


        val textView: ToggleEditTextView? = view?.findViewById(R.id.textView2)
        val textView4: TextView? = view?.findViewById(R.id.textView4)
        textView?.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In mattis, justo quis vulputate venenatis, mi felis viverra arcu, sed blandit tellus nisl vitae tortor. Etiam nec ligula nec felis scelerisque consectetur. Vestibulum at ligula id turpis sodales faucibus ultricies et augue. Mauris semper quam lectus, id convallis orci porttitor non. Vestibulum a mauris ultrices, gravida elit consectetur, rhoncus diam. Phasellus sit amet orci libero. Suspendisse ac odio tristique, scelerisque lorem in, vulputate felis. In tempus ligula in quam scelerisque, eu sagittis nulla luctus. Vivamus viverra, mauris quis dignissim sodales, felis sem cursus ex, nec scelerisque arcu neque a mauris. Duis pretium tristique risus ut pulvinar. Aenean at laoreet mauris. Duis a lobortis mi.")

        textView?.textView?.setOnClickListener {
            textView.setEditing(true, true)
            textView.editText.requestFocus()
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
            KeyboardUtils.toggleKeyboardVisibility(requireContext())

        }

        textView?.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE)
        textView?.editText?.isSingleLine = false;


        KeyboardUtils.addKeyboardToggleListener(activity){ isVisible ->
            if(isVisible == false){
                textView?.setEditing(false, true)
            }
        }

        BottomSheetBehavior.from(bottomSheet).addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

                /*when(newState){
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        textView?.maxLines = 5
                    }
                }*/
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val diff = currentPosition - slideOffset
                if (diff != 0f) {
                    if (diff < 0) {
                        Log.d(TAG, "onSlide: UP")

                        if (textView?.textView?.maxLines == 5) {
                            textView.setMaxLines(5000)
                            textView.editText.setLines(5000)
                        }
                        textView4?.gravity = Gravity.BOTTOM
                    }else{
                        Log.d(TAG, "onSlide: DOWN")

                        if (textView?.textView?.maxLines == 5000) {
                            textView.setMaxLines(5)
                            textView.editText.setLines(5)
                        }

                        textView4?.gravity = Gravity.TOP
                    }
                }

                currentPosition = slideOffset
            }

        })
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }


    companion object{
        fun newInstance() = TestBottomSheetFragment()
        const val TAG = "TestBottomSheetFragment"
    }

}