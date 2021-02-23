package br.com.androidmaster.recycler

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.androidmaster.recycler.databinding.ItemMessageBinding

class MessageAdapter(
        val messages: List<Message>,
        val callback: (Message) -> Unit):
        RecyclerView.Adapter<MessageAdapter.VH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context))
        val vh = VH(binding);
        vh.itemView.setOnClickListener {
            val message = messages[vh.adapterPosition]
            callback(message)
        }
        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val (title, message) = messages[position]
        holder.txtTitle.text = title
        holder.txtMessage.text = message
    }

    override fun getItemCount(): Int = messages.size

    class VH(binding: ItemMessageBinding): RecyclerView.ViewHolder(binding.root){
        val txtTitle = binding.txtTitle
        val txtMessage = binding.txtMessage
    }
}