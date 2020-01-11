package com.example.burpeechallenge.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true) var uid: Int=0,
    var date: String,
    var athlete: String,
    var startTime: Int,
    var finishTime: Int,
    var count: Int = 0
    // var mood: Int = 0
)