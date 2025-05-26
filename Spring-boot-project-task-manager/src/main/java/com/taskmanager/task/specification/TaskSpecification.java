package com.taskmanager.task.specification;

import com.taskmanager.task.entity.Task;
import com.taskmanager.task.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    public static Specification<Task>hasStatus(TaskStatus status){
        return  (root, query, criteriaBuilder) -> status==null?null: criteriaBuilder.equal(root.get("status"),status);
    }

    public static  Specification<Task> nameContains(String name){
        return (root, query, criteriaBuilder) -> (name==null || name.isBlank())? null: criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),"%"+name.toLowerCase()+ "%");
    }
}
