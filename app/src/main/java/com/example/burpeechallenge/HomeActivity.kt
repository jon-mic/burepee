package com.example.burpeechallenge

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onResume() {
        super.onResume()
        workoutToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                startActivity(Intent(this, WorkoutActivity::class.java))
                d("jomic", "Checked")
            } else {
                Toast.makeText(
                    this,
                    "You are already DONE for the day! Good job :)",
                    Toast.LENGTH_LONG
                ).show()
                workoutToggle.isChecked = true
                d("jomic", "unchecked!")
            }
        }

    }

}

