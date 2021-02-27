package com.example.androiddevchallenge.data.source.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogs")
data class DogDb(
    @PrimaryKey val id: Int,
    val name: String,
    val breed: String,
    val imageUrl: String,
)
