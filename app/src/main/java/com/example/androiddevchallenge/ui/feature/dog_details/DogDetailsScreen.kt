package com.example.androiddevchallenge.ui.feature.dog_details

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController

@Composable
fun DogDetailsScreen(
    id: Int,
    dogDetailsViewModel: DogDetailsViewModel,
    navController: NavController
) {

    val details = dogDetailsViewModel.getDetails(id).observeAsState()

    Scaffold {
        Text(text = "Id: $id")
    }
}