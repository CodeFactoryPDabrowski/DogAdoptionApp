package com.example.androiddevchallenge.ui.feature.dog_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.navigate
import androidx.navigation.NavController
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.domain.entity.Dog
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DogListScreen(
    viewModel: DogListViewModel,
    navController: NavController,
) {
    val dogsList: List<Dog> by viewModel.dogs.observeAsState(listOf())

    Scaffold {
        DogsList(dogs = dogsList) { id ->
            navController.navigate("dogDetails/$id")
        }
    }
}

@Composable
private fun DogsList(dogs: List<Dog>, onItemClick: (id: Int) -> Unit) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        itemsIndexed(dogs) { index, dog ->
            DogItem(dog, onItemClick)
        }
    }
}

@Composable
private fun DogItem(dog: Dog, onItemClick: (id: Int) -> Unit) {
    Row(Modifier.clickable { onItemClick.invoke(dog.id) }) {
        CoilImage(
            data = "https://picsum.photos/300/300",
            contentDescription = "My content description",
            requestBuilder = {
                transformations(CircleCropTransformation())
            }
        )
    }
}