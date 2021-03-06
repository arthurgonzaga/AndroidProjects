package br.com.androidmaster.roomwordssample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.androidmaster.roomwordssample.data.model.Word
import br.com.androidmaster.roomwordssample.databinding.RecyclerItemBinding

class WordRecyclerAdapter(private val words: List<Word>) : RecyclerView.Adapter<WordRecyclerAdapter.VH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.txtWord.text = words[position].mWord
    }

    override fun getItemCount(): Int = words.size

    data class VH(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        val txtWord = binding.txtWord
    }
}