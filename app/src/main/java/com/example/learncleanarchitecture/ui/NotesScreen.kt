package com.example.learncleanarchitecture.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.learncleanarchitecture.R


@Composable
fun NotesScreen(
    viewModel: NoteViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val getAllNotes by viewModel.allNote.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Qeydlər")

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Başlıq") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Mətn") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (!title.isEmpty() || !content.isEmpty()){
                    viewModel.insertNote(title,content)
                }
                title = ""
                content = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Əlavə et")
        }

        HorizontalDivider()

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(
                items = getAllNotes,
                key = { it.id }
            )
            { note ->
                NoteItem(
                    note.title,
                    note.content,
                    deleteClick = {
                        viewModel.deleteNote(note.id)
                    }
                )
            }
        }
    }
}

@Composable
fun NoteItem(
    title: String,
    content: String,
    deleteClick: () -> Unit
    ){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .heightIn(min = 50.dp),
            shape = RoundedCornerShape(size = 10.dp),
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 10.dp)
            ) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.size(5.dp))

                Text(
                    text = content,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.size(5.dp))

        IconButton(
            onClick = deleteClick,
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = Color.Red,
                contentDescription = "Delete Button",
            )
        }

    }
}
