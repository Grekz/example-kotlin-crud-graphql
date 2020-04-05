package com.grekz.crudKotlin.resource

import com.grekz.crudKotlin.model.Task
import com.grekz.crudKotlin.service.TaskService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api/tasks")
class TaskResource(private val taskService: TaskService) {

  @GetMapping
  fun getTasks(): List<Task> =
    taskService.getTasks()

  @PostMapping
  fun addTask(@Valid @RequestBody task: Task): ResponseEntity<Task> =
    ResponseEntity.ok(taskService.addTask(task))

  @GetMapping("/{id}")
  fun getTaskById(@PathVariable(value = "id") taskId: Long): ResponseEntity<Task> =
    taskService.getTaskById(taskId).map { task ->
      ResponseEntity.ok(task)
    }.orElse(ResponseEntity.notFound().build())

  @PutMapping("/{id}")
  fun updateTaskById(
    @PathVariable(value = "id") taskId: Long,
    @Valid @RequestBody newTask: Task): ResponseEntity<Task> =
    taskService.putTask(taskId, newTask)
      .map { task -> ResponseEntity.ok().body(task) }
      .orElse(ResponseEntity.notFound().build())

  @DeleteMapping("/{id}")
  fun deleteTask(@PathVariable(value = "id") taskId: Long): ResponseEntity<Void> =
    if (taskService.deleteTask(taskId))
      ResponseEntity<Void>(HttpStatus.ACCEPTED)
    else
      ResponseEntity.notFound().build()
}