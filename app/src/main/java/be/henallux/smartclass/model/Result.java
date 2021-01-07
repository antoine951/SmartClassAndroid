package be.henallux.smartclass.model;

public class Result {

    private String category;
    private String schoolSubject;
    private String average;


    public Result(String category, String schoolSubject, String average) {
        this.schoolSubject = schoolSubject;
        this.average = average;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSchoolSubject() {
        return schoolSubject;
    }

    public void setSchoolSubject(String schoolSubject) {
        this.schoolSubject = schoolSubject;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
