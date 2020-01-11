package com.example.burpeechallenge.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Workout::class, Athlete::class], version = 1)
abstract class BurpeeDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao
    abstract fun athleteDao(): AthleteDao

    companion object {

        private var INSTANCE: BurpeeDatabase? = null

        fun getInstance(context: Context): BurpeeDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, BurpeeDatabase::class.java, "burpee_database")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}