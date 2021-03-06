package br.com.androidmaster.roomwordssample.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.androidmaster.roomwordssample.R
import br.com.androidmaster.roomwordssample.applications.WordsApplication

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object{
        val application = WordsApplication()
    }
}