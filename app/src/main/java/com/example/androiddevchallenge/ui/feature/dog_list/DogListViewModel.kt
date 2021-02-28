package com.example.androiddevchallenge.ui.feature.dog_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.androiddevchallenge.domain.DogRepository
import com.example.androiddevchallenge.domain.entity.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {
    val dogs: LiveData<List<Dog>> = liveData {
        val data = dogRepository.getAll()
        emit(data)
    }
}