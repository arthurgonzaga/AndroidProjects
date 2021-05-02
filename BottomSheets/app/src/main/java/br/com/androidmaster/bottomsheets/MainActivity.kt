package br.com.androidmaster.bottomsheets

import android.R
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.androidmaster.bottomsheets.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun openBottomSheetDialog(view: View) {
        supportFragmentManager.let {
            TestBottomSheetFragment.newInstance(this).apply {
                show(it, tag)
            }
        }
    }
}