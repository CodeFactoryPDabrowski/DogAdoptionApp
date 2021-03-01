package com.example.androiddevchallenge.ui.feature.dog_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androiddevchallenge.domain.DogRepository
import com.example.androiddevchallenge.domain.entity.Dog
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DogListViewModel @Inject constructor(
    dogRepository: DogRepository
) : ViewModel() {
    val dogs: LiveData<List<Dog>> = dogRepository.getAll().asLiveData()
}