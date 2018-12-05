package com.eihror.coroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val scope = GlobalScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.launch(Dispatchers.Main) {
            doBlock()
        }
    }

    suspend fun doBlock() {
        for (i in 10 downTo 1) {
            textView.text = "Countdown: $i..."
            delay(1_000)
        }
        textView.text = "That's begin of Coroutines!"
    }

}
