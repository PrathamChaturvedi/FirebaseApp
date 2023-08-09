package com.example.firebaseapp.di

import android.content.Context
import androidx.room.Room
import com.example.firebaseapp.data.AppDatabase
import com.example.firebaseapp.data.ItemDao
import com.example.firebaseapp.data.ItemRepository
import com.example.firebaseapp.ui.ItemViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "my-database"
        ).build()
    }

    @Provides
    fun provideItemDao(appDatabase: AppDatabase): ItemDao {
        return appDatabase.itemDao()
    }

    @Provides
    fun provideItemRepository(itemDao: ItemDao): ItemRepository {
        return ItemRepository(itemDao)
    }

    @Provides
    fun provideItemViewModel(repository: ItemRepository): ItemViewModel {
        return ItemViewModel(repository)
    }
}
