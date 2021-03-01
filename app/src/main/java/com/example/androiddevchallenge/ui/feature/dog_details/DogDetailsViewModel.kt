package com.example.androiddevchallenge.ui.feature.dog_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androiddevchallenge.domain.DogRepository
import com.example.androiddevchallenge.domain.entity.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DogDetailsViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {

    fun getDetails(id: Int): LiveData<Dog> {
        return liveData {
            val data = dogRepository.get(id)
            emit(data)
        }
    }
}