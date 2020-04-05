package com.grekz.crudKotlin.service

import com.grekz.crudKotlin.model.Task
import com.grekz.crudKotlin.repository.TaskRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TaskService(private val taskRepository: TaskRepository) {

  fun getTasks(): List<Task> =
    taskRepository.findAll()

  fun addTask(task: Task): Task = taskRepository.save(task)

  fun getTaskById(taskId: Long): Optional<Task> =
    taskRepository.findById(taskId)

  fun putTask(taskId: Long, newTask: Task): Optional<Task> =
    taskRepository.findById(taskId).map { currentTask ->
      val updatedTask: Task =
        currentTask
          .copy(
            title = newTask.title,
            description = newTask.description,
            status = newTask.status,
            startDate = newTask.startDate,
            priority = newTask.priority,
            dueDate = newTask.dueDate
          )
      taskRepository.save(updatedTask)
    }

  fun deleteTask(taskId: Long): Boolean =
    taskRepository.findById(taskId).map { task ->
      taskRepository.delete(task)
      true
    }.orElse(false)
}