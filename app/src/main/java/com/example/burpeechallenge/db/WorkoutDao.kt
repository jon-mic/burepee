package com.example.burpeechallenge.db

import android.view.View
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout")
    fun getAll(): List<Workout>

    @Query("SELECT * FROM workout WHERE uid IN (:workoutIds)")
    fun getAllByIds(workoutIds: IntArray): List<Workout>

    @Query("SELECT * FROM workout WHERE date(date) == (:date)")
    fun getByDate(date: String): List<Workout>

    @Query("SELECT SUM(count) FROM workout WHERE date(date) == (:date)")
    fun getTodaysBurpeeCount(date: String): Int

    @Insert
    fun insertWorkout(workout: Workout)

    @Delete
    fun delete(workout: Workout)

}