package com.example.memorygame2.models

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

data class BoardSize(val numPairs : Int){

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

    enum class Difficulty(val numPairs : Int){
        EASY(4),
        MEDIUM(10),
        HARD(15)
    }

}
