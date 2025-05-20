package com.taskmanager.task.service;

import com.taskmanager.task.dto.TaskDTO;
import com.taskmanager.task.entity.Task;
import com.taskmanager.task.enums.TaskStatus;
import com.taskmanager.task.repository.TaskRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository tasksRepository) {
        this.taskRepository = tasksRepository;
    }

    public Task addTask(TaskDTO taskDTO){


        if(taskDTO.getDetails().isBlank() || taskDTO.getName().isBlank())
        {
            throw new IllegalArgumentException("All Fields Are Required");
        }

        if(taskRepository.existsByName(taskDTO.getName())){
            throw new IllegalArgumentException("name already exist");
        }


        Task task=new Task();
        task.setNameAndDetails(taskDTO.getName(),taskDTO.getDetails());
        task.setStatus(TaskStatus.NOT_STARTED);
        return taskRepository.save(task);
    }
}
