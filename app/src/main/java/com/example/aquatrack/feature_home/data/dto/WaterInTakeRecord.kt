package com.example.aquatrack.feature_home.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aquatrack.util.AppConstants

@Entity(tableName = AppConstants.WATER_INTAKE_TABLE)
data class WaterInTakeRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val timestamp: Long,
    val amountInMl: Int
)
