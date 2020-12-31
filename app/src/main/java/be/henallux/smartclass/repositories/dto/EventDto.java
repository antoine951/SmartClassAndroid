package be.henallux.smartclass.repositories.dto;

public class EventDto {

    private Integer id;
    private String name;
    private String date;
    private String description;

    public Integer getId() {
        return id;
    }
    public String getEventName() {
        return name;
    }

    public void setEventName(String eventName) {
        this.name = eventName;
    }

    public String getEventDate() {
        return date;
    }

    public void setEventDate(String eventDate) {
        this.date = eventDate;
    }

    public String getEventDescription() {
        return description;
    }

    public void setEventDescription(String eventDescription) {
        this.description = eventDescription;
    }
}
