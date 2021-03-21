package br.com.androidmaster.edgetoedge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
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
}