package com.suatzengin.catbreeds.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoritesModel::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}