package com.example.memorygame2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame2.databinding.MemoryCardBinding
import kotlin.math.min

class MemoryBoardAdapter(private val context: Context, private val columns: Int, private val rows: Int)
    : RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : ViewHolder {
        /*
        LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)
        */
        val view = MemoryCardBinding.inflate(LayoutInflater.from(context) )

        // when using viewbinding
        val cardWidth = parent.width/columns
        val cardHeight = parent.height/rows
        val cardSideLength = min(cardWidth, cardHeight)

        val adjHeight = (parent.height-(cardSideLength*rows))/rows
        view.cardView.layoutParams.width = cardSideLength
        view.cardView.layoutParams.height = cardSideLength+adjHeight

        val margins = cardSideLength/15
        view.memoryCardBtn.layoutParams.width = cardSideLength-margins
        view.memoryCardBtn.layoutParams.height = cardSideLength-margins

        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = columns*rows





    // inner class
    /*
    inner class ViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {
    }
    */
    // when using viewbinding
    inner class ViewHolder(private val memoryCardBinding: MemoryCardBinding)
        : RecyclerView.ViewHolder(memoryCardBinding.root) {



        fun bind(position: Int){
            memoryCardBinding.memoryCardBtn.setOnClickListener {
                Log.i(this.toString(),"Clicked on position $position")
            }
        }

    }


}
