package com.example.memorygame2

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import com.example.memorygame2.databinding.ActivityMainBinding
import com.example.memorygame2.databinding.MemoryCardBinding
import com.example.memorygame2.databinding.ViewCheckedTextviewBinding
import com.example.memorygame2.models.BoardSize
import com.example.memorygame2.models.MemoryGame
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var memoryGame: MemoryGame
    private lateinit var binding: ActivityMainBinding
    // difficulty 4:10:15
    private var numMoves = 0
    private lateinit var textviewBinding: ViewCheckedTextviewBinding

    private var boardSize : BoardSize  = BoardSize.EASY
    // or choose freely
    //private var boardSize : BoardSize  = BoardSize(6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textviewBinding = ViewCheckedTextviewBinding.inflate(layoutInflater)
        /*
        var level = showAlertDialog()

        if (level.equals("EASY")){
            boardSize = BoardSize.EASY
        }

        if (level.equals("MEDIUM")){
            boardSize = BoardSize.MEDIUM
        }

        if (level.equals("HARD")){
            boardSize = BoardSize.HARD
        }
        */

        // create the game
        setup()
        difficultyDialog()


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

            }
        }

    }




    private fun setup(){
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






    private fun gameWonDialog() {
        val mAlertDialog = AlertDialog.Builder(this@MainActivity)
        mAlertDialog.setIcon(R.mipmap.ic_launcher_round) //set alertdialog icon
        mAlertDialog.setTitle("You won!") //set alertdialog title
        mAlertDialog.setMessage("Play again or exit?") //set alertdialog message
        mAlertDialog.setPositiveButton("Play again") { dialog, id ->
            difficultyDialog()
            //binding.boardRV.adapter?.notifyDataSetChanged()
            //Toast.makeText(this@MainActivity, "Yes", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.setNegativeButton("Exit") { dialog, id ->
            // closes the app. for now. going to implement a splashscreen and a main menu
            finishAffinity()
            //Toast.makeText(this@MainActivity, "No", Toast.LENGTH_SHORT).show()
        }
        mAlertDialog.show()

    }

        /*
        var level = showAlertDialog()

        if (level.equals("EASY")){
            boardSize = BoardSize.EASY
            Toast.makeText(this@MainActivity, "EASY", Toast.LENGTH_SHORT).show()
        }

        if (level.equals("MEDIUM")){
            boardSize = BoardSize.MEDIUM
            Toast.makeText(this@MainActivity, "Yes", Toast.LENGTH_SHORT).show()
        }

        if (level.equals("HARD")){
            boardSize = BoardSize.HARD
            Toast.makeText(this@MainActivity, "Yes", Toast.LENGTH_SHORT).show()
        }
        */



    private fun difficultyDialog() : String {
        val list = listOf("EASY", "MEDIUM", "HARD")

        val adapter = ArrayAdapter<CharSequence>(this, R.layout.view_checked_textview, list)

        var which = 0

        val ad = AlertDialog.Builder(this)
        ad.setTitle("Choose your difficulty level")
        ad.setSingleChoiceItems(adapter, -1) { dialog, which->
            val checkedTv = adapter.getView(which, null, binding.root) as CheckedTextView
            checkedTv.isChecked = true

            Toast.makeText(this, list[which], Toast.LENGTH_SHORT).show()
            when(list[which]){
                "EASY" ->  boardSize = BoardSize.EASY
                "MEDIUM" ->  boardSize = BoardSize.MEDIUM
                "HARD" ->  boardSize = BoardSize.HARD
            }

            setup()


            dialog.dismiss()

        }

        ad.create().show()
        return list[which]

    }


}