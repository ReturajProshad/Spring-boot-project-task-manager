package com.taskmanager.task.dto;

import com.taskmanager.task.enums.TaskStatus;

public class TaskDTO {
    private String name;
    private String details;
    private TaskStatus status;

    public TaskDTO(String name, TaskStatus status, String details) {
        this.name = name;
        this.status = status;
        this.details = details;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskDTO() {
    }

    public TaskDTO(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
