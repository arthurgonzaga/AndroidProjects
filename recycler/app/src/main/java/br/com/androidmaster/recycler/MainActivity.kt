package br.com.androidmaster.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.androidmaster.recycler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var messages = mutableListOf<Message>()
    private var adapter = MessageAdapter(messages, this::onMessageItemClick)
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun initRecyclerView(){
        binding.rvMessages.adapter = adapter
    }

    private fun onMessageItemClick(message: Message){

    }
}