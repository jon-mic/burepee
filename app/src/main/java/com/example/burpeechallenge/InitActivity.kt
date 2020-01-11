package com.example.burpeechallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.burpeechallenge.db.Athlete
import com.example.burpeechallenge.db.BurpeeDatabase
import kotlinx.android.synthetic.main.activity_init.*
import java.time.Instant.now


class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = BurpeeDatabase.getInstance(this)
        this.startHomeOrCreateAthlete(db)

    }

    override fun onResume() {
        super.onResume()
        finish()
    }

    fun startHomeOrCreateAthlete(db: BurpeeDatabase) {
        val athlete = db.athleteDao().loadByLatestLogin()
        d("jomic", "${athlete}")
        if (athlete != null) {
            athlete.lastLogin = (System.currentTimeMillis() / 1000).toInt()
            db.athleteDao().update(athlete)
            startActivity(Intent(this, HomeActivity::class.java))
        } else {
            setContentView(R.layout.activity_init)
            nameEnterButton.setOnClickListener {
                val name = editName.text.toString()
                d("jomic", "pressed by $name!")
                db.athleteDao().insertAthlete(
                    Athlete(
                        athleteName = name,
                        lastLogin = (System.currentTimeMillis() / 1000).toInt()
                    )
                )
                startHomeOrCreateAthlete(db)
            }

        }

    }

}
