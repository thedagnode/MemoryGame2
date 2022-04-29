package com.example.memorygame2.models

data class MemoryCard(
    val id          : Int,
    var isFaceUp    : Boolean = false,
    var isMatched   : Boolean = false
)
