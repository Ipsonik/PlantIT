package com.example.plantit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.plantit.model.PlantRequirement

@Entity(tableName = "plants")
data class Plant(

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "plant_name")
    val plantName: String,

    @ColumnInfo(name = "plant_requirement")
    val plantRequirement: PlantRequirement,

    @ColumnInfo(name = "plant_photo")
    val plantPhoto: String

)