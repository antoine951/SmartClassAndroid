package be.henallux.smartclass.services.mappers;

import be.henallux.smartclass.model.Task;
import be.henallux.smartclass.repositories.dto.TaskDto;

public class TaskMapper {
    private static TaskMapper instance = null;

    public static TaskMapper getInstance() {
        if (instance == null) {
            instance = new TaskMapper();
        }
        return instance;
    }

    public Task mapToTask(TaskDto dto) {
        if (dto == null) {
            return null;
        }

        return new Task(dto.getTitle(), dto.getType(), dto.getDate());
    }
}
