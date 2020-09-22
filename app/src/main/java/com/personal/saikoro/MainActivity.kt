package com.personal.saikoro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tag = "Dice game"
    private var yourDice = 0
    private var droidDice = 0
    private var winCount = 0
    private var loseCount = 0
    private var drawCount = 0
    private var gameStarted = false
    private var diceThrew = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        throwBtn.setOnClickListener {
            if(gameStarted && !diceThrew){
                showDice()
                diceThrew = false
            }
        }

        resetBtn.setOnClickListener {
            yourDice = 0
            droidDice = 0
            winCount = 0
            loseCount = 0
            drawCount = 0
            gameStarted = false
            diceThrew = false

            winText.text = getString(R.string.win_text)
            loseText.text = getString(R.string.lose_text)
            drawText.text = getString(R.string.draw_text)
            resultText.text = getString(R.string.result_text)

            onResume()
        }

    }

    override fun onResume() {
        super.onResume()
        winCount = 0
        loseCount = 0
        drawCount = 0
        winText.text = getString(R.string.win_text)
        loseText.text = getString(R.string.lose_text)
        drawText.text = getString(R.string.draw_text)
        resultText.text = getString(R.string.result_text)
        gameStarted = true
        throwDice()
    }

    private fun showDice(){
        yourDice = (1..6).random()
        Log.d(tag, "You threw: $yourDice")
        setDiceImageResource(false, yourDice)


        droidDice = (1..6).random()
        Log.d(tag, "Droid threw: $droidDice")
        setDiceImageResource(true, droidDice)

        diceThrew = true
        val balance = droidDice - yourDice
        when {
            balance == 0 -> {
                drawCount ++
                drawText.text = getString(R.string.draw_text) + drawCount
            }
            balance > 0 -> {
                loseCount ++
                loseText.text = getString(R.string.lose_text) + loseCount
            }
            else -> {
                winCount ++
                winText.text = getString(R.string.win_text) + winCount
            }
        }
        gameResult(winCount, loseCount)
    }

    private fun gameResult(winCount: Int, loseCount: Int){
        when {
            winCount == 5 -> {
                resultText.text = getString(R.string.result_win_text)
                gameStarted = false
            }
            loseCount == 5 -> {
                resultText.text = getString(R.string.result_lost_text)
                gameStarted = false
            }
            else -> {
                resultText.text = getString(R.string.result_text)
            }
        }
    }

    private fun throwDice(){
        yourDiceImage.setImageResource(R.drawable.start)
        droidDiceImage.setImageResource(R.drawable.start)
    }

    private fun setDiceImageResource(isDroid: Boolean, dice: Int){
        if(isDroid){
            when(dice){
                1 -> droidDiceImage.setImageResource(R.drawable.saikoro_1)
                2 -> droidDiceImage.setImageResource(R.drawable.saikoro_2)
                3 -> droidDiceImage.setImageResource(R.drawable.saikoro_3)
                4 -> droidDiceImage.setImageResource(R.drawable.saikoro_4)
                5 -> droidDiceImage.setImageResource(R.drawable.saikoro_5)
                6 -> droidDiceImage.setImageResource(R.drawable.saikoro_6)
            }
        } else {
            when(dice){
                1 -> yourDiceImage.setImageResource(R.drawable.saikoro_1)
                2 -> yourDiceImage.setImageResource(R.drawable.saikoro_2)
                3 -> yourDiceImage.setImageResource(R.drawable.saikoro_3)
                4 -> yourDiceImage.setImageResource(R.drawable.saikoro_4)
                5 -> yourDiceImage.setImageResource(R.drawable.saikoro_5)
                6 -> yourDiceImage.setImageResource(R.drawable.saikoro_6)
            }
        }

    }

}