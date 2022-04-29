package com.example.memorygame2.models

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

data class BoardSize(val numPairs : Int){

    // some expressions to decide how to arrange the rows and colums based on num pairs
    private var columns = floor(sqrt(numPairs.toDouble()*2)).toInt()
    private var rows = ceil(numPairs.toDouble()*2 / columns).toInt()

    fun getRows(): Int {
        return rows
    }

    fun getColumns(): Int {
        return columns
    }

    fun getNumItems() : Int{
        return numPairs*2
    }

    // I also made an option and added this so that one of three layouts can be selected.
    // They are however run through the parent class using the same logic to create the layout.
    enum class Difficulty(val numPairs : Int){
        EASY(4),
        MEDIUM(10),
        HARD(15)
    }

}
