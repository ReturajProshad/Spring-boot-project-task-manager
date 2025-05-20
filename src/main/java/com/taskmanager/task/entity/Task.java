package com.taskmanager.task.entity;

import com.taskmanager.task.enums.TaskStatus;
import jakarta.persistence.*;

@Entity

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private String details;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task(Long id, String name, String details, TaskStatus status) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.status = status;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Task(Long id, String name, String details) {
        this.id = id;
        this.name = name;
        this.details = details;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
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


    public void setNameAndDetails(String name,String details) {
        this.name=name;
        this.details = details;
    }

}
