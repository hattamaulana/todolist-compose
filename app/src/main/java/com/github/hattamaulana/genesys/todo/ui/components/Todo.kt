package com.github.hattamaulana.genesys.todo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.github.hattamaulana.genesys.todo.data.models.Todo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItem(
    todo: Todo,
    onToggleComplete: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    val containerColor = if (todo.completed) {
        MaterialTheme.colorScheme.surfaceVariant
    } else {
        MaterialTheme.colorScheme.surface
    }

    val textDecoration = if (todo.completed) {
        TextDecoration.LineThrough
    } else {
        TextDecoration.None
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = { onToggleComplete(todo.id) }
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = todo.title,
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = textDecoration,
                modifier = Modifier.weight(1f) // Text takes up available space
            )

            Spacer(modifier = Modifier.width(16.dp))

            IconButton(onClick = { onDelete(todo.id) }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Delete Todo",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
