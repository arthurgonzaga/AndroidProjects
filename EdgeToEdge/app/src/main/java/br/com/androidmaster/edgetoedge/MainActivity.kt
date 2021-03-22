package br.com.androidmaster.edgetoedge.activities.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.androidmaster.edgetoedge.R
import br.com.androidmaster.edgetoedge.adapters.RecyclerViewAdapter
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setUpRecyclerView()
        setUpEdgeToEdge()
        setUpStatusBar()
    }

    fun setUpRecyclerView(){
        val list = listOf(
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dignissim feugiat scelerisque. Donec finibus tincidunt nunc eu vestibulum."
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = RecyclerViewAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapter

    }



    fun setUpEdgeToEdge(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.setDecorFitsSystemWindows(false)
        }else{
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        setUpStatusBar()
        }
    }

    fun setUpStatusBar(): Int{
        when(getString(R.string.mode)){
            "Day" -> {
                Log.i("AGR", "setUpStatusBar: DAY_MODE")

                return View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            "Night" -> {
                Log.i("AGR", "setUpStatusBar: NIGHT_MODE")
            }
        }
        return View.SYSTEM_UI_FLAG_VISIBLE
    }
}