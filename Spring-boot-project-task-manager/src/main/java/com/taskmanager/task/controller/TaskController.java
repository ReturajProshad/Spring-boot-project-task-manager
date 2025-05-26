package com.taskmanager.task.controller;

import com.taskmanager.task.dto.ApiResponse;
import com.taskmanager.task.dto.TaskDTO;
import com.taskmanager.task.entity.Task;
import com.taskmanager.task.enums.TaskStatus;
import com.taskmanager.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private  final TaskService taskService;


    public TaskController(TaskService tasksService) {
        this.taskService = tasksService;
    }

    @PostMapping("/addTask")
    public ResponseEntity<ApiResponse<Task>> addTask(@Valid @RequestBody TaskDTO task){

        Task savedTask=taskService.addTask(task);
        final ApiResponse<Task> response=new ApiResponse<Task>(
                true,
                "Task Successfully added",
                savedTask
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allTasks")
    public ResponseEntity<ApiResponse<Page<Task>>> getAll(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(required=false) String name,
            @RequestParam(required = false)TaskStatus status
    ){
        Pageable pageable= PageRequest.of(page,size);
        Page<Task> taskPageable=taskService.getTaskWithFilters(name,status,pageable);
        ApiResponse<Page<Task>> response=new ApiResponse<>(true,"Successfully Retrieve all tasks",taskPageable);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public  ResponseEntity<ApiResponse<Task>> getById(@PathVariable Long id){
        Task task=taskService.getById(id);
        final ApiResponse<Task> response=new ApiResponse<Task>(
                true,
                "Task Successfully retrieve",
                task
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable Long id,@Valid @RequestBody TaskDTO taskDTO){
        Task task=taskService.updateTaskDetails(id,taskDTO);
        final ApiResponse<Task> response=new ApiResponse<Task>(
                true,
                "Task Successfully Updated",
                task
        );
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/status/{id}")
    public  ResponseEntity<ApiResponse<Task>> updateStatus(@PathVariable Long id, @RequestBody TaskDTO status){
        Task task=taskService.updateTaskStatus(id,status);
        final ApiResponse<Task> response=new ApiResponse<Task>(
                true,
                "Task Successfully Updated",
                task
        );
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTask(@PathVariable Long id){
        Boolean isSucess=taskService.deleteTask(id);
        ApiResponse<String> response= new ApiResponse<>(
                isSucess,
                "Successfully deleted the task",
                null
        );
        return ResponseEntity.ok(response);
    }

}
