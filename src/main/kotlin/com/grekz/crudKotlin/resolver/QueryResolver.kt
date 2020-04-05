package com.grekz.crudKotlin.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.grekz.crudKotlin.model.Task
import com.grekz.crudKotlin.service.TaskService
import org.springframework.stereotype.Component

@Component
class QueryResolver (private val taskService: TaskService) : GraphQLQueryResolver {
  fun getTasks(): List<Task> = taskService.getTasks()
  fun getTaskById(taskId: Long): Task = taskService.getTaskById(taskId).orElse(null)
}