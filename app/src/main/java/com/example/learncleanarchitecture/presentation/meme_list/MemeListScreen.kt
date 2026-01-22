package com.example.learncleanarchitecture.presentation.meme_list

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.learncleanarchitecture.domain.model.Meme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeListScreen(
    viewModel: MemeListViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("All Images")
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.error != null -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(state.error ?: "Xeta bash verdi")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = {viewModel.onEvent(MemeListEvent.Refresh)}) {
                            Text("Yeniden cehd edin")
                        }
                    }
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2)
                    ) {
                        items(state.data.size) { index ->
                            MemeItem(viewModel,state.data[index])
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun MemeItem(
    viewModel: MemeListViewModel = hiltViewModel(),
    meme: Meme
) {

    val state by viewModel.state.collectAsState()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            val height = meme.height ?: 0
            val width = meme.width ?: 0
            AsyncImage(
                model = meme.url ?: "",
                contentDescription = meme.name ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

        }
    }
}






























