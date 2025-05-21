package com.taskmanager.task.service;


import com.taskmanager.task.dto.TaskDTO;
import com.taskmanager.task.entity.Task;
import com.taskmanager.task.enums.TaskStatus;
import com.taskmanager.task.repository.TaskRepository;
import com.taskmanager.task.specification.TaskSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<Task> getTasks(){
        return taskRepository.findAll();
    }


    public Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task Not Found"));
    }


    public Task updateTaskDetails(Long id,TaskDTO taskDTO){
        Task existingTask= getById(id);
        if(taskDTO.getDetails().isBlank() || taskDTO.getName().isBlank())
        {
            throw new IllegalArgumentException("All Fields Are Required");
        }
        existingTask.setNameAndDetails(taskDTO.getName(),taskDTO.getDetails());
        return taskRepository.save(existingTask);
    }


    public Task updateTaskStatus(Long id,TaskDTO status){
        Task task= getById(id);
       task.setStatus(status.getStatus());
        return taskRepository.save(task);
    }
    public Boolean deleteTask(Long id){
        Task task=getById(id);
        taskRepository.delete(task);
        return true;
    }

    public Page<Task> getTaskWithFilters(String name, TaskStatus status, Pageable pageable)
    {
        Specification<Task> spec=Specification.where(TaskSpecification.hasStatus(status)).and(TaskSpecification.nameContains(name));
        return taskRepository.findAll(spec,pageable);
    }

}
