package be.henallux.smartclass.model;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {

    private Integer id;
    private String eventName;
    private Date eventDate;
    private String eventDescription;

    public Event() {
    }

    public Event(String eventName, Date eventDate) {
        setEventName(eventName);
        setEventDate(eventDate);
    }

    public Event(Integer id, String eventName, Date eventDate, String eventDescription) {
        this(eventName, eventDate);
        this.id = id;
        setEventDescription(eventDescription);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventDateFormated() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return dateFormat.format(eventDate);
    }

}
