package com.taskmanager.task.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum TaskStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED;

    @JsonCreator
    public static TaskStatus fromString(String value) {
        return parseStatus(value);
    }

    public static TaskStatus parseStatus(String statusStr) {
        if (statusStr == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        try {
            return TaskStatus.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Invalid status: '%s'. Must be one of: %s",
                            statusStr,
                            Arrays.toString(TaskStatus.values()))
            );
        }
    }
}