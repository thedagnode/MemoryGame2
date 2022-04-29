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

        val columns = 3
        val rows = 5
        binding.boardRV.adapter = MemoryBoardAdapter(this, columns, rows)
        binding.boardRV.setHasFixedSize(true)
        binding.boardRV.layoutManager = GridLayoutManager(this, columns)





    }

}