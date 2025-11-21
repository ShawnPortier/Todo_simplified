package com.example.todo_simplified.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_simplified.data.Task
import com.example.todo_simplified.data.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    val allTasks: LiveData<List<Task>> = repository.allTasks

    fun addTask(title: String, priority: Int = 1) {
        viewModelScope.launch {
            val task = Task(
                title = title,
                priority = priority
            )
            repository.insertTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

    fun toggleTaskDone(task: Task) {
        viewModelScope.launch {
            val updated = task.copy(isDone = !task.isDone)
            repository.insertTask(updated)
        }
    }
}
