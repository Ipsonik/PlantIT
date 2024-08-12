package com.example.plantit.database

import androidx.room.Dao

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant")
}