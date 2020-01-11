package com.example.burpeechallenge

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.burpeechallenge.db.BurpeeDatabase
import kotlinx.android.synthetic.main.activity_home.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class HomeActivity : AppCompatActivity() {
    val db = BurpeeDatabase.getInstance(this)
    private val now = LocalDateTime.now()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        dateTextView.text = this.now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
    }

    override fun onResume() {
        super.onResume()
        val burpeeCount =
            this.db.workoutDao().getTodaysBurpeeCount(this.now.format(DateTimeFormatter.ISO_DATE))

        d("jomic", "Today is ${this.now} and we have done ${burpeeCount} burpees!")
        progress.progress = burpeeCount

        setStartButtonText(burpeeCount)
        startButton.setOnClickListener {
            if (burpeeCount != 100) {
                startActivity(Intent(this, WorkoutActivity::class.java))
            } else {
                Toast.makeText(
                    this,
                    "You are already done for today. Good Job!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        summaryButton.setOnClickListener {
            startActivity(Intent(this, SummaryActivity::class.java))
        }

        resetButton.setOnClickListener {
            db.workoutDao().getByDate(this.now.format(DateTimeFormatter.ISO_DATE))
                .map { db.workoutDao().delete(it) }
            this.onResume()
        }

    }

    fun setStartButtonText(burpeeCount: Int) {
        if (burpeeCount != 100) {
            startButton.setBackgroundColor(Color.parseColor("#d3d3d3"))
            startButton.text = getString(R.string.start_workout)
        } else {
            startButton.setBackgroundColor(Color.parseColor("#FF0000"))
            startButton.text = getString(R.string.done)
        }
    }

}

