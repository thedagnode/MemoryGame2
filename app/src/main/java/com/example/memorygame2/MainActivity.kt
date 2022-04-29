package com.example.memorygame2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memorygame2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //2*3
        //4*6
        val columns = 3
        val rows = 5

        // divisible by 2 check
        // column*rows%2 == 0

        binding.boardRV.adapter = MemoryBoardAdapter(this, columns, rows)
        binding.boardRV.setHasFixedSize(true)
        binding.boardRV.layoutManager = GridLayoutManager(this, columns)



    }

}