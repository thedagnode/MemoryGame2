package com.example.memorygame2.models

import com.example.memorygame2.utils.DEFAULT_ICONS

class MemoryGame(private val boardSize : BoardSize) {

    val cards: List<MemoryCard>
    var numPairsFound = 0
    var numMoves: Int = 0

    private var firstSelCard : Int? = null


    init {
        val chosenImages: List<Int> =  DEFAULT_ICONS.shuffled().take(boardSize.numPairs)
        val randomizedImages = (chosenImages+chosenImages).shuffled()
        cards = randomizedImages.map { MemoryCard(it,false, false) }
    }


    fun flipCard(position: Int) : Boolean{
        val card : MemoryCard = cards[position]

        var foundMatch = false

        if( firstSelCard == null){
            restoreCards()
            firstSelCard = position
        }

        else {
            foundMatch = checkForMatch(firstSelCard!!, position)
            firstSelCard = null
            numMoves++
        }
         card.isFaceUp = !card.isFaceUp
        return foundMatch
    }



    private fun checkForMatch(position1: Int, position2: Int): Boolean {

        if(cards[position1].id != cards[position2].id){
            return false
        }

        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }



    private fun restoreCards() {
        for(card in cards){
            if(!card.isMatched){
                card.isFaceUp = false
            }
        }
    }

    fun haveWonGame(): Boolean {

        return numPairsFound == boardSize.numPairs

    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }


    fun resetGame(){
        for(card in cards){
            card.isFaceUp = false
            card.isMatched = false
        }
        numMoves=0
        numPairsFound=0
        firstSelCard = null
    }
}
