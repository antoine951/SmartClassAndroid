package be.henallux.smartclass.services.mappers;

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

    public Event mapToEvent(EventDto dto){
        if (dto == null) {
            return null;
        }

        return new Event(dto.getEventName(), dto.getEventDate(),dto.getEventDescription());
    }
}
