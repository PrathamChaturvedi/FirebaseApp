package com.example.firebaseapp


import android.app.Application
import com.example.firebaseapp.data.ItemRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var itemRepository: ItemRepository

    override fun onCreate() {
        super.onCreate()

    }
}