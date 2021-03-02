package com.example.androiddevchallenge.ui.feature.dog_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.domain.entity.Dog
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DogListScreen(
    viewModel: DogListViewModel,
    navController: NavController,
) {
    val dogsList: List<Dog> by viewModel.dogs.observeAsState(listOf())

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Dog adoption", style = MaterialTheme.typography.h5)
            })
        },
        content = {
            DogsList(dogs = dogsList) { id -> navController.navigate("dogDetails/$id") }
        }
    )

}

@Composable
private fun DogsList(dogs: List<Dog>, onItemClick: (id: Int) -> Unit) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        itemsIndexed(dogs) { _, dog -> DogItem(dog, onItemClick) }
    }
}

@Composable
private fun DogItem(dog: Dog, onItemClick: (id: Int) -> Unit) {
    Row(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { onItemClick.invoke(dog.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoilImage(
            data = dog.imageUrl,
            contentDescription = "My content description",
            modifier = Modifier.size(72.dp, 72.dp),
            requestBuilder = {
                transformations(CircleCropTransformation())
            }
        )

        Column(
            Modifier
                .padding(8.dp)
                .fillMaxHeight()
        ) {
            Text(text = dog.name, style = MaterialTheme.typography.h5)
            Text(text = dog.breed, style = MaterialTheme.typography.subtitle2)
        }
    }
}