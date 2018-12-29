package com.example.saren.lab1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var player = 0;
    private var gameStarted = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.visibility = View.INVISIBLE;
        button2.visibility = View.INVISIBLE;
        button3.visibility = View.INVISIBLE;
        button4.visibility = View.INVISIBLE;
        button5.visibility = View.INVISIBLE;
        button6.visibility = View.INVISIBLE;
        button7.visibility = View.INVISIBLE;
        button8.visibility = View.INVISIBLE;
        button9.visibility = View.INVISIBLE;
    }
    fun gameStart(view: View){
        this.gameStarted = true;
        this.player = 1; //1st player turn
        textView.text = "0 turn"
        button.visibility = View.VISIBLE;
        button2.visibility = View.VISIBLE;
        button3.visibility = View.VISIBLE;
        button4.visibility = View.VISIBLE;
        button5.visibility = View.VISIBLE;
        button6.visibility = View.VISIBLE;
        button7.visibility = View.VISIBLE;
        button8.visibility = View.VISIBLE;
        button9.visibility = View.VISIBLE;
        button.text = "";
        button2.text = "";
        button3.text = "";
        button4.text = "";
        button5.text = "";
        button6.text = "";
        button7.text = "";
        button8.text = "";
        button9.text = "";
    }

    fun step(view:View){
        if(gameStarted == true) {
            var playerSign = "0";
            if (player == 1) {
                playerSign = "0";
                textView.text = "X turn"
                player = 2;
            } else {
                playerSign = "X"
                textView.text = "0 turn"
                player = 1;
            }
            when (view.id) {
                R.id.button -> {
                    if (button.text == "") button.text = playerSign
                }
                R.id.button2 -> {
                    if (button2.text == "") button2.text = playerSign
                }
                R.id.button3 -> {
                    if (button3.text == "") button3.text = playerSign
                }
                R.id.button4 -> {
                    if (button4.text == "") button4.text = playerSign
                }
                R.id.button5 -> {
                    if (button5.text == "") button5.text = playerSign
                }
                R.id.button6 -> {
                    if (button6.text == "") button6.text = playerSign
                }
                R.id.button7 -> {
                    if (button7.text == "") button7.text = playerSign
                }
                R.id.button8 -> {
                    if (button8.text == "") button8.text = playerSign
                }
                R.id.button9 -> {
                    if (button9.text == "") button9.text = playerSign
                }
            }
            checkGameOver(view)
        }
    }

    fun checkGameOver(view:View){
        if(button.text == button2.text && button2.text== button3.text && button.text != "")
        {
            gameStarted = false;
            if(button.text == "X")
                textView.text = "X win!"
            else textView.text = "0 win!"
        }
        if(button.text == button4.text && button.text== button7.text && button.text != "")
        {
            gameStarted = false;
            if(button.text == "X")
                textView.text = "X win!"
            else textView.text = "0 win!"
        }
        if(button4.text == button5.text && button4.text== button6.text && button4.text != "")
        {
            gameStarted = false;
            if(button.text == "X")
                textView.text = "X win!"
            else textView.text = "0 win!"
        }
        if(button7.text == button8.text && button7.text== button9.text && button7.text != "")
        {
            gameStarted = false;
            if(button.text == "X")
                textView.text = "X win!"
            else textView.text = "0 win!"
        }
        if(button2.text == button5.text && button2.text== button8.text && button2.text != "")
        {
            gameStarted = false;
            if(button.text == "X")
                textView.text = "X win!"
            else textView.text = "0 win!"
        }
        if(button3.text == button6.text && button3.text== button9.text && button3.text != "")
        {
            gameStarted = false;
            if(button.text == "X")
                textView.text = "X win!"
            else textView.text = "0 win!"
        }
        if(button.text == button5.text && button.text== button9.text && button.text != "")
        {
            gameStarted = false;
            if(button.text == "X")
                textView.text = "X win!"
            else textView.text = "0 win!"
        }
        if(button3.text == button5.text && button3.text== button7.text && button3.text != "")
        {
            gameStarted = false;
            if(button.text == "X")
                textView.text = "X win!"
            else textView.text = "0 win!"
        }
    }
}
