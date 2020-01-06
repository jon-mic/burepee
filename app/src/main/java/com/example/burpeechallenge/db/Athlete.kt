package com.example.burpeechallenge.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["athlete_name"], unique = true)])
data class Athlete(
    @PrimaryKey(autoGenerate = true) var uid: Int=0,
    @ColumnInfo(name="athlete_name") var athleteName: String,
    var lastLogin: Int
)