package br.com.androidmaster.recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        // Not working in androidX
        lastNonConfigurationInstance.let { savedMessages ->
            if(savedMessages is MutableList<*>){
                Log.d("MAIN", "messages $messages")
                messages.addAll(savedMessages.filterIsInstance(Message::class.java))
                Log.d("MAIN", "messages $messages")
            }
        }

        initRecyclerView()
        binding.fabAdd.setOnClickListener {
            addMessage()
        }
    }

    private fun initSwipeGesture(){

        val swipe = object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                messages.removeAt(position)
                adapter.notifyItemRemoved(position)
            }

        }
        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(binding.rvMessages)
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

        initSwipeGesture()
    }

    private fun onMessageItemClick(message: Message){
        Toast.makeText(this@MainActivity,
                getString(R.string.added, message.title),
                Toast.LENGTH_SHORT).show()
    }

    // Not working in androidX
    override fun onRetainCustomNonConfigurationInstance(): Any {
        return messages
    }
}