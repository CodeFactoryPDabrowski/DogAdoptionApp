package com.example.androiddevchallenge.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androiddevchallenge.data.source.entity.DogDb

@Dao
abstract class DogRoomDao {

    @Insert
    abstract suspend fun save(dogs: List<DogDb>)

    @Query("SELECT * FROM dogs ORDER BY id ASC")
    abstract suspend fun getAll(): List<DogDb>

    @Query("SELECT * FROM dogs WHERE id = :id")
    abstract suspend fun get(id: Int): DogDb


}