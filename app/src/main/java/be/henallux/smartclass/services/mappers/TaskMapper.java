package be.henallux.smartclass.services.mappers;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @SuppressLint("SimpleDateFormat")
    public Task mapToTask(TaskDto dto) {
        if (dto == null) {
            return null;
        }
        String sDate = dto.getDate();
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(sDate);
        }catch (Exception e){
            System.out.println("erreur formattage date");
        }
        return new Task(dto.getTitle(), dto.getType(), date);
    }
}
