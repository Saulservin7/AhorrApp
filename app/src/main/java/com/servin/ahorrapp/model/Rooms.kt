package com.servin.ahorrapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.servin.ahorrapp.data.Converters
import com.servin.ahorrapp.data.Game


@Entity(tableName = "rooms")
@TypeConverters(Converters::class)
data class Rooms(

    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

    @ColumnInfo
    val game:Game,

    @ColumnInfo
    val userId:Int=0,

    @ColumnInfo
    val totalSaving:Long=0,

    @ColumnInfo
    val usedNumbers:String?=""

)
