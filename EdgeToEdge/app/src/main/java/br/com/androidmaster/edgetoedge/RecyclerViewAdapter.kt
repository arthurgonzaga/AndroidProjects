package br.com.androidmaster.edgetoedge

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val list: List<String>) : RecyclerView.Adapter<RecyclerViewAdapter.VH>(){



    data class VH(val itemView: View): RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(
            android.R.layout.simple_list_item_1,
            parent,
            false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.textView.text = list[position]
        Log.d("AGR", "onBindViewHolder: ${list[position]}")
    }

    override fun getItemCount(): Int = list.size
}