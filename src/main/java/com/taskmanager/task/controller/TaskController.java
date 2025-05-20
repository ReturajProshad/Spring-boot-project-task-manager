package com.taskmanager.task.controller;

import com.taskmanager.task.dto.ApiResponse;
import com.taskmanager.task.dto.TaskDTO;
import com.taskmanager.task.entity.Task;
import com.taskmanager.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    private  final TaskService taskService;


    public TaskController(TaskService tasksService) {
        this.taskService = tasksService;
    }
    @PostMapping("/addTask")
    public ResponseEntity<ApiResponse<Task>> addTask(@RequestBody TaskDTO task){

        Task savedTask=taskService.addTask(task);
        final ApiResponse<Task> response=new ApiResponse<Task>(
                true,
                "Task Successfully added",
                savedTask
        );
        return ResponseEntity.ok(response);
    }
}
