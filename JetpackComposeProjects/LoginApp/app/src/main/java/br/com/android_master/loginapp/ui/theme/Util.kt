package br.com.android_master.loginapp.ui.theme

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object Util {

    fun hideKeyboard(context: Context) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}