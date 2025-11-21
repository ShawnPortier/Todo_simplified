package com.example.todo_simplified

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.todo_simplified.data.TaskDatabase
import com.example.todo_simplified.data.TaskRepository
import com.example.todo_simplified.ui.theme.TaskViewModel
import com.example.todo_simplified.ui.theme.TaskViewModelFactory
import com.example.todo_simplified.ui.theme.TodoApp
import com.example.todo_simplified.ui.theme.Todo_simplifiedTheme

class MainActivity : ComponentActivity() {

    private val viewModel: TaskViewModel by viewModels {
        val dao = TaskDatabase.getInstance(applicationContext).taskDao()
        val repository = TaskRepository(dao)
        TaskViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Todo_simplifiedTheme {
                TodoApp(viewModel)
            }
        }
    }
}
