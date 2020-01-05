package com.example.burpeechallenge.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time

@Entity
data class Workout(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    var date: Date,
    var athlete: String,
    var startTime: Time,
    var finishTime: Time,
    var count: Int = 0
    // var mood: Int = 0
)