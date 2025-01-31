package com.servin.ahorrapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.servin.ahorrapp.data.Game


@Entity(tableName = "rooms")
data class Rooms(

    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

    @ColumnInfo
    val game:Game,

    @ColumnInfo
    val userId:Int=0,

    @ColumnInfo
    val totalSaving:Long=0

)
