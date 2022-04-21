package com.suatzengin.catbreeds.data.local

import androidx.room.*

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: FavoritesModel)

    @Delete
    suspend fun delete(cat: FavoritesModel)

    @Query("SELECT * FROM favorites")
    fun getFavorites(): List<FavoritesModel>

    @Query("SELECT * FROM favorites WHERE id = :id")
    fun getFavoriteById(id: Int): FavoritesModel
}