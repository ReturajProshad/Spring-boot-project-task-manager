package com.taskmanager.task.mapper;

import com.taskmanager.task.dto.TaskDTO;
import com.taskmanager.task.entity.Task;

public class TaskMapper {

    public static Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setNameAndDetails(dto.getName(), dto.getDetails());
        return task;
    }

    public static TaskDTO toDto(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setName(task.getName());
        dto.setDetails(task.getDetails());
        dto.setStatus(task.getStatus());
        return dto;
    }

    public static void updateDetailsFromDto(TaskDTO dto, Task task) {
        task.setNameAndDetails(dto.getName(), dto.getDetails());
    }

    public static void updateStatusFromDto(TaskDTO dto, Task task) {
        task.setStatus(dto.getStatus());
    }
}