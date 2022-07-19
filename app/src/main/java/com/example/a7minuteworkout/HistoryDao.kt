package com.example.a7minuteworkout

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    suspend fun InsertItem(historyData: HistoryData)
    @Delete
    suspend fun deleteitem(historyData: HistoryData)
    @Update
    suspend fun updateItem(historyData: HistoryData)
    @Query("Select * from `Hitory-Table`")
     fun getAllItems():Flow<List<HistoryData>>
    @Query("Select * from `Hitory-Table` where date=:id")
     fun getSpeceficItem(id:String):Flow<HistoryData>
}