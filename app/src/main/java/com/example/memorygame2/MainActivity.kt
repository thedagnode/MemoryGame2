package com.example.memorygame2

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.children
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memorygame2.databinding.ActivityMainBinding
import com.example.memorygame2.models.BoardSize
import com.example.memorygame2.models.MemoryGame
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var memoryGame: MemoryGame
    private lateinit var binding: ActivityMainBinding
    // difficulty 4:10:15
    private var numMoves = 0

    private var boardSize : BoardSize  = BoardSize(BoardSize.Difficulty.EASY.numPairs)
    // or choose freely
    //private var boardSize : BoardSize  = BoardSize(6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // create the game
        memoryGame = MemoryGame(boardSize)

        binding.boardRV.adapter = MemoryBoardAdapter(this, boardSize, memoryGame.cards, object: MemoryBoardAdapter.CardClickListener{
            override fun onCardClicked(position: Int) {
                updateGameWithFlip(position)
            }

        })

        binding.boardRV.setHasFixedSize(true)
        binding.boardRV.layoutManager = GridLayoutManager(this, boardSize.getColumns())

        // init text views
        binding.numPairsTV.text = "0/${boardSize.numPairs.toString()}"



    }

    private fun updateGameWithFlip(position: Int) {



        if (!memoryGame.isCardFaceUp(position)){

            // returns true if there's a match
            if (memoryGame.flipCard(position) ){
                binding.numPairsTV.text = "${memoryGame.numPairsFound}/${boardSize.numPairs}"

            }
            binding.numMovesTV.text = "Moves: ${memoryGame.numMoves}"
            binding.boardRV.adapter?.notifyDataSetChanged()


            if(memoryGame.haveWonGame()){
                //Snackbar.make(binding.root, "You won", Snackbar.LENGTH_LONG).show()
                gameWonDialog()
                //memoryGame.resetGame()
            }
        }

    }




    private fun gameWonDialog() {
        val mAlertDialog = AlertDialog.Builder(this@MainActivity)
        mAlertDialog.setIcon(R.mipmap.ic_launcher_round) //set alertdialog icon
        mAlertDialog.setTitle("You won!") //set alertdialog title
        mAlertDialog.setMessage("Play again or exit?") //set alertdialog message
        mAlertDialog.setPositiveButton("Play again") { dialog, id ->
            //perform some tasks here
            binding.boardRV.adapter?.notifyDataSetChanged()
            //Toast.makeText(this@MainActivity, "Yes", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.setNegativeButton("Exit") { dialog, id ->
            // closes the app. for now. going to implement a splashscreen and a main menu
            finishAffinity()
            //Toast.makeText(this@MainActivity, "No", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.show()

    }


}