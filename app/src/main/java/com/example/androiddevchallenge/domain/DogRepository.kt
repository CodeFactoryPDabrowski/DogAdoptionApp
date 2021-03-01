package com.example.androiddevchallenge.domain

import com.example.androiddevchallenge.domain.entity.Dog
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    fun getAll(): Flow<List<Dog>>

    suspend fun get(id: Int): Dog
}