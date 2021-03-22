package br.com.androidmaster.edgetoedge.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.androidmaster.edgetoedge.R

class RecyclerViewAdapter(val list: List<String>) : RecyclerView.Adapter<RecyclerViewAdapter.VH>(){



    data class VH(val itemView: View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item,
            parent,
            false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.textView.text = list[position]
    }

    override fun getItemCount(): Int = list.size
}