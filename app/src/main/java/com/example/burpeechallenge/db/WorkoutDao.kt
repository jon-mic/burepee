package com.example.burpeechallenge.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.sql.Date

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout")
    fun getAll(): List<Workout>

    @Query("SELECT * FROM workout WHERE uid IN (:workoutIds)")
    fun loadAllByIds(workoutIds: IntArray): List<Workout>

    @Query("SELECT * FROM workout WHERE date == (:date)")
    fun findByDate(date: Date): Workout

    @Insert
    fun insertWorkout(workout: Workout)

    @Delete
    fun delete(workout: Workout)

}