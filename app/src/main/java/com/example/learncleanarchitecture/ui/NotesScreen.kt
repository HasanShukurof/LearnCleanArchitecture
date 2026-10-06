package com.example.learncleanarchitecture.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.learncleanarchitecture.data.local_db.category.CategoryEntity


@Composable
fun NotesScreen(viewModel: NoteViewModel = hiltViewModel()) {
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    val categories by viewModel.categories.collectAsStateWithLifecycle()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var newCategory by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf<Long?>(null) }
    var deleting by remember { mutableStateOf<CategoryEntity?>(null) }

    // id → ad, hər qeyd üçün siyahını axtarmamaq üçün
    val categoryNames = remember(categories) { categories.associate { it.id to it.name } }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Kateqoriyalar", fontWeight = FontWeight.Bold)

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(categories, key = { it.id }) { category ->
                FilterChip(
                    selected = category.id == selectedCategoryId,
                    onClick = {
                        selectedCategoryId =
                            if (selectedCategoryId == category.id) null else category.id
                    },
                    label = { Text(category.name) },
                    trailingIcon = {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Sil",
                            modifier = Modifier.size(18.dp).clickable { deleting = category }
                        )
                    }
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newCategory,
                onValueChange = { newCategory = it },
                label = { Text("Yeni kateqoriya") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                viewModel.addCategory(newCategory)
                newCategory = ""
            }) { Text("+") }
        }

        HorizontalDivider()

        OutlinedTextField(
            value = title, onValueChange = { title = it },
            label = { Text("Başlıq") }, singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = content, onValueChange = { content = it },
            label = { Text("Mətn") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                viewModel.insertNote(title, content, selectedCategoryId)
                title = ""; content = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Əlavə et") }

        HorizontalDivider()

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(notes, key = { it.id }) { note ->
                NoteItem(
                    title = note.title,
                    content = note.content,
                    categoryName = categoryNames[note.categoryId],
                    deleteClick = { viewModel.deleteNote(note.id) }
                )
            }
        }
    }

    deleting?.let { from ->
        val others = categories.filter { it.id != from.id }
        AlertDialog(
            onDismissRequest = { deleting = null },
            title = { Text("\"${from.name}\" silinir") },
            text = {
                Column {
                    Text("Qeydlər hara köçsün?")
                    others.forEach { to ->
                        TextButton(onClick = {
                            viewModel.deleteCategory(from.id, to.id)
                            if (selectedCategoryId == from.id) selectedCategoryId = to.id
                            deleting = null
                        }) { Text(to.name) }
                    }
                    TextButton(onClick = {
                        viewModel.deleteCategory(from.id, null)
                        if (selectedCategoryId == from.id) selectedCategoryId = null
                        deleting = null
                    }) { Text("Kateqoriyasız qalsın") }
                }
            },
            confirmButton = {},
            dismissButton = { TextButton(onClick = { deleting = null }) { Text("Ləğv et") } }
        )
    }
}

@Composable
fun NoteItem(
    title: String,
    content: String,
    categoryName: String?,
    deleteClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card(
            modifier = Modifier.weight(1f).heightIn(min = 50.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
        ) {
            Column(Modifier.padding(10.dp)) {
                Row {
                    Text(title, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text(categoryName ?: "—", fontSize = 11.sp, color = Color(0xFF1565C0))
                }
                Spacer(Modifier.size(5.dp))
                Text(content, fontSize = 14.sp, color = Color.Gray)
            }
        }
        Spacer(Modifier.size(5.dp))
        IconButton(onClick = deleteClick, modifier = Modifier.size(50.dp)) {
            Icon(Icons.Default.Delete, tint = Color.Red, contentDescription = "Sil")
        }
    }
}
