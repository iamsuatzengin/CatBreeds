package com.suatzengin.catbreeds.data.local

import androidx.room.*
import com.suatzengin.catbreeds.domain.model.CatBreed

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cat: CatBreed)

    @Delete
    suspend fun delete(cat: CatBreed)

    @Query("SELECT * FROM favorites")
    fun getFavorites(): List<CatBreed>

    @Query("SELECT * FROM favorites WHERE id = :id")
    fun getFavoriteById(id: String): CatBreed
}