package com.example.memorygame2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame2.databinding.MemoryCardBinding
import kotlin.math.min

class MemoryBoardAdapter(private val context: Context, private val numItems: Int)
    : RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : ViewHolder {
        /*
        LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)
        */

        // when using viewbinding
        val width = parent.width/2
        val height = parent.height/4
        val cardSideLength = min(width, height)

        val view = MemoryCardBinding.inflate(LayoutInflater.from(context) )
        view.cardView.layoutParams.width = cardSideLength
        view.cardView.layoutParams.height = cardSideLength
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = numItems





    // inner class
    /*
    inner class ViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {
    }
    */
    // when using viewbinding
    inner class ViewHolder(memoryCardBinding: MemoryCardBinding)
        : RecyclerView.ViewHolder(memoryCardBinding.root) {


            fun bind(position: Int){

            }

    }


}
