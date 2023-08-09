package com.example.firebaseapp.utils


import androidx.lifecycle.MutableLiveData
import com.example.firebaseapp.model.ItemEntity

object FcmEvent {
    val itemInserted = MutableLiveData<ItemEntity>()
}