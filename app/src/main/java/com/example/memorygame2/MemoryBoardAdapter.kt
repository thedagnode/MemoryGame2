package com.example.memorygame2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.memorygame2.databinding.MemoryCardBinding
import com.example.memorygame2.models.BoardSize
import com.example.memorygame2.models.MemoryCard
import kotlin.math.min

class MemoryBoardAdapter(
    private val context: Context,
    private var boardSize: BoardSize,
    private val cards: List<MemoryCard>,
    private val cardClickListener: CardClickListener
)
    : RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {

    interface CardClickListener{
        fun onCardClicked( position: Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        : ViewHolder {

        // inflate the view
        val view = MemoryCardBinding.inflate(LayoutInflater.from(context) )

        // layout the cards with margins and appropriate rows and columns
        // based on the amount of cards
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



    inner class ViewHolder(private val memoryCardBinding: MemoryCardBinding)
        : RecyclerView.ViewHolder(memoryCardBinding.root) {

        fun bind(position: Int){
            memoryCardBinding.memoryCardBtn.setImageResource( if(cards[position].isFaceUp) cards[position].id else R.drawable.card_bg )
            memoryCardBinding.memoryCardBtn.alpha = if(cards[position].isMatched) .4f else 1.0f


            memoryCardBinding.memoryCardBtn.setOnClickListener {

                cardClickListener.onCardClicked(position)
                Log.i(this.toString(),"Clicked on position $position" )

            }
        }

    }


}
