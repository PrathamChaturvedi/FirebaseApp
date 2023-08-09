package com.example.firebaseapp.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseapp.model.ItemEntity
import com.example.firebaseapp.data.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val repository: ItemRepository) : ViewModel() {
    val allItems: LiveData<List<ItemEntity>> = repository.allItems

    fun insertItemsAndUpdate(items: List<ItemEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertItemsAndUpdate(items)
        }
    }
}