package com.example.firebaseapp.data

import androidx.lifecycle.LiveData
import com.example.firebaseapp.model.ItemEntity
import javax.inject.Inject

class ItemRepository @Inject constructor(private val itemDao: ItemDao) {

    val allItems: LiveData<List<ItemEntity>> = itemDao.getAllItems()

    suspend fun insertItemsAndUpdate(items: List<ItemEntity>) {
        itemDao.insertItems(items)
    }
}
