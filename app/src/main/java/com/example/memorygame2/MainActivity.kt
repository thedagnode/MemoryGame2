package com.example.memorygame2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memorygame2.databinding.ActivityMainBinding
import com.example.memorygame2.models.BoardSize

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // difficulty 4:10:15

    private var boardSize : BoardSize  = BoardSize(BoardSize.Difficulty.MEDIUM.numPairs)
    // or choose freely
    //private var boardSize : BoardSize  = BoardSize(6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.boardRV.adapter = MemoryBoardAdapter(this, boardSize)
        binding.boardRV.setHasFixedSize(true)
        binding.boardRV.layoutManager = GridLayoutManager(this, boardSize.getColumns())



    }

}