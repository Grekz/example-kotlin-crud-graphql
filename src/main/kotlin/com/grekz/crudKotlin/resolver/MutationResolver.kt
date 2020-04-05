package com.grekz.crudKotlin.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.grekz.crudKotlin.model.Task
import com.grekz.crudKotlin.service.TaskService
import org.springframework.stereotype.Component

@Component
class MutationResolver (private val taskService: TaskService) : GraphQLMutationResolver {
  fun createTask(title: String, description: String, status: Int, priority: Int): Task =
    taskService.addTask(
      Task(
        title = title,
        description = description,
        status = status,
        priority = priority
      )
    )

  fun updateTask(taskId: Long, title: String, description: String, status: Int, priority: Int): Task =
    taskService.putTask(
      taskId,
      Task(
        id = taskId,
        title = title,
        description = description,
        status = status,
        priority = priority
      )
    ).orElse(null)

  fun deleteTask(taskId: Long): Boolean = taskService.deleteTask(taskId)
}