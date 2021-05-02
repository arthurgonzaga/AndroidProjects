package br.com.androidmaster.bottomsheets

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openBottomSheet(view: View) {
        supportFragmentManager.let {
            TestBottomSheetFragment.newInstance().apply {
                show(it, tag)
            }
        }
    }
}