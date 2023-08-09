package com.example.firebaseapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.firebaseapp.model.ItemEntity

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItems(): LiveData<List<ItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<ItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(items: ItemEntity)
}