package com.example.androiddevchallenge.domain

import com.example.androiddevchallenge.domain.entity.Dog

interface DogRepository {
    suspend fun getAll(): List<Dog>

    suspend fun get(id: Int): Dog
}