package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.data.source.DogRoomDao
import com.example.androiddevchallenge.data.source.entity.DogDb
import com.example.androiddevchallenge.domain.DogRepository
import com.example.androiddevchallenge.domain.entity.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DogDataRepository @Inject constructor(
    private val dogRoomDao: DogRoomDao
) : DogRepository {

    override fun getAll(): Flow<List<Dog>> =
        dogRoomDao.getAll().map { it.toBlo() }

    override suspend fun get(id: Int): Dog =
        dogRoomDao.get(id).toBlo()

    private fun List<DogDb>.toBlo(): List<Dog> = map { dbModel -> dbModel.toBlo() }

    private fun DogDb.toBlo(): Dog =
        with(this) {
            Dog(
                id,
                name,
                breed,
                imageUrl
            )
        }
}