package br.com.androidmaster.chrometabs

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ActivityCompat
import br.com.androidmaster.chrometabs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            openChromeTab()
        }

    }

    fun openChromeTab(){
        val url = "https://android-master.com.br/"
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ActivityCompat.getColor(this, R.color.purple_500))
        val customTab = builder.build()
        customTab.launchUrl(this, Uri.parse(url))
    }
}