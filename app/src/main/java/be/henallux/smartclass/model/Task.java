package be.henallux.smartclass.model;

import java.util.Date;

public class Task {

    private String title;
    private String type;
    private Date date;


    public Task() {
    }

    public Task(String title, String type, Date date) {
        setTitle(title);
        setType(type);
        setDate(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
