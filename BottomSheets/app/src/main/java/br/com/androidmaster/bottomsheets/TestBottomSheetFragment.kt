package br.com.androidmaster.bottomsheets

import android.animation.LayoutTransition
import android.app.Activity
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
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

    var currentPosition = 0f

    lateinit var constraintLayout: ConstraintLayout
    lateinit var constraintSet: ConstraintSet

    lateinit var bottomSheetDialog: BottomSheetDialog
    lateinit var bottomSheet: FrameLayout

    val myString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In mattis, " +
            "justo quis vulputate venenatis, mi felis viverra arcu, sed blandit tellus nisl " +
            "vitae tortor. Etiam nec ligula nec felis scelerisque consectetur. Vestibulum at " +
            "ligula id turpis sodales faucibus ultricies et augue. Mauris semper quam lectus," +
            " id convallis orci porttitor non. Vestibulum a mauris ultrices, gravida elit " +
            "consectetur, rhoncus diam. Phasellus sit amet orci libero. Suspendisse ac odio" +
            " tristique, scelerisque lorem in, vulputate felis. In tempus ligula in quam" +
            " scelerisque, eu sagittis nulla luctus. Vivamus viverra, mauris quis dignissim" +
            " sodales, felis sem cursus ex, nec scelerisque arcu neque a mauris. " +
            "Duis pretium tristique risus ut pulvinar. Aenean at laoreet mauris. Duis a lobortis mi."+
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In mattis, " +
            "justo quis vulputate venenatis, mi felis viverra arcu, sed blandit tellus nisl " +
            "vitae tortor. Etiam nec ligula nec felis scelerisque consectetur. Vestibulum at " +
            "ligula id turpis sodales faucibus ultricies et augue. Mauris semper quam lectus," +
            " id convallis orci porttitor non. Vestibulum a mauris ultrices, gravida elit " +
            "consectetur, rhoncus diam. Phasellus sit amet orci libero. Suspendisse ac odio" +
            " tristique, scelerisque lorem in, vulputate felis. In tempus ligula in quam" +
            " scelerisque, eu sagittis nulla luctus. Vivamus viverra, mauris quis dignissim" +
            " sodales, felis sem cursus ex, nec scelerisque arcu neque a mauris. " +
            "Duis pretium tristique risus ut pulvinar. Aenean at laoreet mauris. Duis a lobortis mi."

    lateinit var textView: ToggleEditTextView
    lateinit var textView4: TextView
    lateinit var relativeLayout: RelativeLayout
    lateinit var lp: ViewGroup.LayoutParams

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        peekHeight = resources.getDimensionPixelSize(R.dimen.peekHeight_small)

        val mView = layoutInflater.inflate(R.layout.bottom_sheet, container, false)
        constraintLayout = mView.findViewById(R.id.constraint_layout)
        constraintSet = ConstraintSet().apply { clone(constraintLayout) }
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


    private fun setupContent(bottomSheet: FrameLayout){
        textView = view?.findViewById(R.id.textView2)!!
        textView4= view?.findViewById(R.id.textView4)!!
        relativeLayout = view?.findViewById(R.id.relativeLayout)!!
        textView.textView.ellipsize = TextUtils.TruncateAt.END

        textView.setText(myString.slice(0..799))
        textView.editText.filters = arrayOf(InputFilter.LengthFilter(800))
        textView.textView.filters = arrayOf(InputFilter.LengthFilter(800))


        textView.textView.setOnClickListener {
            textView.setEditing(true, true)
            textView.editText.requestFocus()
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
            KeyboardUtils.toggleKeyboardVisibility(requireContext())
            Log.d(TAG, "getLines: ${textView.textView.lineCount}")
            Log.d(TAG, "getLines: ${textView.textView.text.toSet().distinct()}")
        }

        textView.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE)
        textView.editText.isSingleLine = false;


        KeyboardUtils.addKeyboardToggleListener(activity){ isVisible ->
            if(!isVisible){
                val text = textView.editText.text.replace(Regex("[\r\n]+"), "\n\n");
                textView.editText.setText(text)
                textView.setEditing(false, true)
            }
        }

        // ToogleEditTextView
        lp = textView.layoutParams
        lp.height = resources.getDimension(R.dimen.textViewColapsed).toInt()
        textView.layoutParams = lp

        // Textview
        lp = textView.textView.layoutParams
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        textView.textView.layoutParams = lp

        // EditText
        lp = textView.editText.layoutParams
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        textView.editText.layoutParams = lp


        BottomSheetBehavior.from(bottomSheet).addBottomSheetCallback(bottomSheetCallback)
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

    private val bottomSheetCallback = object: BottomSheetBehavior.BottomSheetCallback() {

        override fun onStateChanged(bottomSheet: View, newState: Int) = when(newState){
            BottomSheetBehavior.STATE_COLLAPSED -> {

                textView.setEditing(false, true)
                val lp = textView.layoutParams
                lp?.height = resources.getDimension(R.dimen.textViewColapsed).toInt()
                textView.layoutParams = lp

                textView4.gravity = Gravity.TOP
            }
            else -> {}
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            val diff = currentPosition - slideOffset
            if (diff != 0f) {
                if (diff < 0) {
                    Log.d(TAG, "onSlide: UP")
                    textView.setMaxLines(1000)
                    textView4.gravity = Gravity.BOTTOM


                    lp = textView.layoutParams
                    lp.height = 0
                    textView.layoutParams = lp
                } else {
                    Log.d(TAG, "onSlide: DOWN")

                    Timer("onSlideDown",false).schedule(300){
                        textView4.gravity = Gravity.TOP
                    }


                    textView.textView.scrollTo(0,0)
                    textView.editText.scrollTo(0,0)
                    lp = textView.layoutParams
                    lp.height = resources.getDimension(R.dimen.textViewColapsed).toInt()
                    textView.layoutParams = lp
                }
            }

            currentPosition = slideOffset
        }

    }


    companion object{
        fun newInstance() = TestBottomSheetFragment()
        const val TAG = "TestBottomSheetFragment"
    }

}