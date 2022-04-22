package com.suatzengin.catbreeds.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suatzengin.catbreeds.domain.model.CatBreed

@Database(entities = [CatBreed::class], version = 2, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}