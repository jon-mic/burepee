package com.example.burpeechallenge.db

import androidx.room.*

@Dao
interface AthleteDao {

    // currently designed to hold just one entry/user

    @Query("SELECT * FROM athlete")
    fun getAll(): List<Athlete>

    @Query("SELECT * FROM athlete WHERE uid IN (:athleteIds)")
    fun loadAllByIds(athleteIds: IntArray): List<Athlete>

    @Query("SELECT * FROM athlete WHERE athlete_name LIKE :athleteName")
    fun loadByName(athleteName: String): Athlete

    @Query("SELECT * FROM athlete ORDER BY lastLogin DESC LIMIT 1")
    fun loadByLatestLogin(): Athlete

    @Update                             //Query("UPDATE athlete SET lastLogin = :epoch WHERE")
    fun update(athlete: Athlete)

    @Insert
    fun insertAthlete(athlete: Athlete)

    @Delete
    fun delete(athlete: Athlete)

}
