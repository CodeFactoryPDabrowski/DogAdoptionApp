package com.example.androiddevchallenge.ui.feature.dog_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.androiddevchallenge.domain.entity.Dog
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DogDetailsScreen(
    id: Int,
    dogDetailsViewModel: DogDetailsViewModel,
    navController: NavController
) {

    val dog: Dog? by dogDetailsViewModel.getDetails(id).observeAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = dog?.name ?: "", style = MaterialTheme.typography.h5)
            }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, "Back action")
                }
            })
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                CoilImage(
                    data = dog?.imageUrl ?: "",
                    contentDescription = "My content description",
                    modifier = Modifier
                        .size(128.dp, 128.dp)
                        .padding(bottom = 16.dp),
                    requestBuilder = {
                        transformations(CircleCropTransformation())
                    }
                )

                Text(text = dog?.breed ?: "", style = MaterialTheme.typography.h5)
            }
        }
    )
}