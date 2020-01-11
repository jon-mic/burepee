package com.example.burpeechallenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_workout.*
import android.os.Handler
import android.os.SystemClock.*
import android.util.Log.d
import android.widget.Toast
import com.example.burpeechallenge.db.BurpeeDatabase
import com.example.burpeechallenge.db.Workout
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.format.DateTimeFormatter


class WorkoutActivity : AppCompatActivity() {

    private var startTime = 0
    val db = BurpeeDatabase.getInstance(this)
    private val today = now().format(DateTimeFormatter.ISO_DATE)
    private var count = 0
    private val burpeesPerformedToday = db.workoutDao().getTodaysBurpeeCount(today)
    private val athleteName = db.athleteDao().loadByLatestLogin().athleteName
    private var saved = false

    private val cdt = object : CountDownTimer(15000, 1000) {
        var finished = false
        override fun onTick(millisUntilFinished: Long) {
            counter.text = (millisUntilFinished / 1000).toString()
        }

        override fun onFinish() {
            counter.text = "GO!"
            finished = true
            chrono.base = elapsedRealtime()
            chrono.start()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        counter.text = "Tap to start!"


        counter.setOnClickListener {
            if (!cdt.finished) {
                this.checkCountdown()
            }
        }

        add10Button.setOnClickListener {
            if (cdt.finished) {
                if (burpeesPerformedToday + count < 100) {
                    count += 10
                    counter.text = (burpeesPerformedToday + count).toString()
                }

                if (burpeesPerformedToday + count == 90) {
                    Toast.makeText(
                        this,
                        "Good job! Only 10 more to go.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (burpeesPerformedToday + count == 100) {
                    chrono.stop()

                    counter.text = "Done in:"
                    saveWorkout()
                    Handler().postDelayed({
                        finish()
                    }, 5000)
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
            saveWorkout()
            finish()
        }
    }


    private fun checkCountdown() {
        if (startTime != 0) {
            cdt.onFinish()
            cdt.cancel()
        } else {
            cdt.start()
            startTime = (System.currentTimeMillis() / 1000).toInt()
        }

    }

    fun saveWorkout() {
        if (!this.saved) {
            val currentWorkout = Workout(
                date = today,
                athlete = athleteName,
                startTime = startTime,
                finishTime = (System.currentTimeMillis() / 1000).toInt(),
                count = count
            )
            db.workoutDao().insertWorkout(currentWorkout)
            this.saved = true
        } else {
            d("jomic", "Already saved!")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}