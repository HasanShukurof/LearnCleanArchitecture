package com.example.learncleanarchitecture.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Sadə ekran. Burada Room haqqında heç nə yoxdur.
 * Sən yalnız aşağıdakı TODO-ları dolduracaqsan.
 */
@Composable
fun NotesScreen() {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    // TODO: Room-dan gələn qeydlər siyahısı burada olacaq

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
                // TODO: qeydi bazaya yaz
                title = ""
                content = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Əlavə et")
        }

        HorizontalDivider()

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // TODO: items(notes) { note -> ... }
        }
    }
}
