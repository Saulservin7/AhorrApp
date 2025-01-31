package com.servin.ahorrapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "goals")
data class Goals(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

    @ColumnInfo
    val userId: Int=0,

    @ColumnInfo
    val type: String="",

    @ColumnInfo

    val amount: Double=0.0,

)
