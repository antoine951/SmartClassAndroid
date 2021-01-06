package be.henallux.smartclass.services.mappers;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

import be.henallux.smartclass.model.Event;
import be.henallux.smartclass.repositories.dto.EventDto;

public class EventMapper {

    private static EventMapper instance = null;

    public static EventMapper getInstance() {
        if(instance == null){
            instance = new EventMapper();
        }
        return instance;
    }

    @SuppressLint("SimpleDateFormat")
    public Event mapToEvent(EventDto dto){
        if (dto == null) {
            return null;
        }
        String sDate = dto.getEventDate();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        }catch (Exception e){
            System.out.println("erreur formattage date");
        }
        return new Event(dto.getId(), dto.getEventName(), date, dto.getEventDescription());
    }
}
