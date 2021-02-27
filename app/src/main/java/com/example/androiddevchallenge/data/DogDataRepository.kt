package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.domain.DogRepository
import com.example.androiddevchallenge.domain.entity.Dog

class DogDataRepository : DogRepository {

    override suspend fun getAll(): List<Dog> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: String): Dog {
        TODO("Not yet implemented")
    }
}