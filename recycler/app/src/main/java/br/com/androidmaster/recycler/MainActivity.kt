package br.com.androidmaster.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
        initRecyclerView()
        binding.fabAdd.setOnClickListener {
            addMessage()
        }
    }

    private fun addMessage() {
        val message = Message(
                binding.edtTitle.text.toString(),
                binding.edtMessage.text.toString()
        )
        messages.add(message)
        adapter.notifyItemInserted(messages.lastIndex)
        binding.edtTitle.text?.clear()
        binding.edtMessage.text?.clear()
        binding.edtTitle.requestFocus()
    }

    private fun initRecyclerView(){
        binding.rvMessages.adapter = adapter
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvMessages.layoutManager = layoutManager
    }

    private fun onMessageItemClick(message: Message){
        Toast.makeText(this@MainActivity,
                getString(R.string.added, message.title),
                Toast.LENGTH_SHORT).show()
    }
}