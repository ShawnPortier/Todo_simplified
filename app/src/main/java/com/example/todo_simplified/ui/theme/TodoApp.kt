package com.example.todo_simplified.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todo_simplified.data.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(viewModel: TaskViewModel) {
    val tasks by viewModel.allTasks.observeAsState(emptyList())
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    var priority by remember { mutableStateOf(1) }   // 1 = Low, 2 = Medium, 3 = High
    val priorityOptions = listOf("Low", "Medium", "High")

    Scaffold(
        topBar = { TopAppBar(title = { Text("Simple MVVM Todo") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            // ✦ ROW: New Task + Add button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = textState,
                    onValueChange = { textState = it },
                    label = { Text("New Task") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (textState.text.isNotEmpty()) {
                            viewModel.addTask(textState.text, priority)
                            textState = TextFieldValue("")
                            priority = 1
                        }
                    }
                ) {
                    Text("Add")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ✦ Priority Level Label
            Text(
                text = "Priority Level",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            // ✦ Priority Selection Buttons
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                priorityOptions.forEachIndexed { index, label ->
                    Button(
                        onClick = { priority = index + 1 },
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (priority == index + 1)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(label)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ✦ Task List
            LazyColumn {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onDelete = { viewModel.deleteTask(task) },
                        onToggleDone = { viewModel.toggleTaskDone(task) }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onDelete: () -> Unit,
    onToggleDone: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onToggleDone() }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = if (task.isDone)
                        MaterialTheme.typography.bodyLarge.copy(
                            textDecoration = TextDecoration.LineThrough
                        )
                    else
                        MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Priority: ${priorityLabel(task.priority)}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}

private fun priorityLabel(priority: Int): String =
    when (priority) {
        1 -> "Low"
        2 -> "Medium"
        3 -> "High"
        else -> "Low"
    }
