package com.example.burpeechallenge.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Workout::class, Athlete::class], version = 1)
abstract class BurpeeDatabase : RoomDatabase() {
    abstract fun WorkoutDao(): WorkoutDao
    abstract fun AthleteDao(): AthleteDao
}