package com.example.burpeechallenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_workout.*
import android.os.Handler
import android.os.SystemClock.*
import android.widget.Toast


class WorkoutActivity : AppCompatActivity() {
    private var count = 0
    private var started = false

    private val cdt = object : CountDownTimer(15000, 1000) {
        var finished = false
        override fun onTick(millisUntilFinished: Long) {
            counter.text = (millisUntilFinished / 1000).toString()
        }

        override fun onFinish() {
            counter.text = "GO!"
            this.finished = true
            chrono.base = elapsedRealtime()
            chrono.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        counter.text = "Tap to start!"


        counter.setOnClickListener {
            this.checkCountdown()
        }

        add10Button.setOnClickListener {
            if (this.cdt.finished) {
                count += 10
                counter.text = count.toString()

                if (count == 90) {
                    Toast.makeText(
                        this,
                        "Good job! Only 10 more to go.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (count == 100) {
                    chrono.stop()
                    counter.text = "Done in:"
                    val tmp = Handler()
                    tmp.postDelayed({ finish() }, 5000)
                }
            } else {
                Toast.makeText(
                    this,
                    "You cannot start before the countdown finished.",
                    Toast.LENGTH_LONG
                ).show()
            }


        }

        breakButton.setOnClickListener {
            finish()
        }
    }


    private fun checkCountdown() {
        if (this.started) {
            this.cdt.onFinish()
            this.cdt.cancel()
        } else {
            cdt.start()
            this.started = true
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}