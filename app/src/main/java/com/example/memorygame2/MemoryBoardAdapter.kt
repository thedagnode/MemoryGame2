package com.example.memorygame2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame2.databinding.MemoryCardBinding
import com.example.memorygame2.models.BoardSize
import kotlin.math.min

class MemoryBoardAdapter(private val context: Context, private var boardSize: BoardSize)
    : RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : ViewHolder {
        /*
        LayoutInflater.from(context).inflate(R.layout.memory_card, parent, false)
        */
        val view = MemoryCardBinding.inflate(LayoutInflater.from(context) )

        // when using viewbinding
        val cardWidth = parent.width/boardSize.getColumns()
        val cardHeight = parent.height/boardSize.getRows()
        val cardSideLength = min(cardWidth, cardHeight)

        val adjHeight = (parent.height-(cardSideLength*boardSize.getRows()))/boardSize.getRows()
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

    override fun getItemCount(): Int {
        return boardSize.getNumItems()

    }





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
                Log.i(this.toString(),"Clicked on position $position" )

            }
        }

    }


}
