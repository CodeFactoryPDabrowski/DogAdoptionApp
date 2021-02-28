package com.example.androiddevchallenge.ui.feature.dog_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.androiddevchallenge.domain.entity.Dog

@Composable
fun DogListScreen(
    viewModel: DogListViewModel,
    navController: NavController,
) {
    val dogsList: List<Dog> by viewModel.dogs.observeAsState(listOf())

    Scaffold {
        DogsList(dogs = dogsList)
    }
}

@Composable
private fun DogsList(dogs: List<Dog>) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        itemsIndexed(dogs) { index, dog ->
            Text("Item #${dog.name}")
        }
    }
}