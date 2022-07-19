package com.example.a7minuteworkout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Hitory-Table")
data class HistoryData(
    @PrimaryKey
     val date:String=""
)
