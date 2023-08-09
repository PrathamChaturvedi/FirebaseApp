package com.example.firebaseapp.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firebaseapp.model.ItemEntity

@Database(entities = [ItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}