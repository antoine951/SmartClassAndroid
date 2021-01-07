package be.henallux.smartclass.repositories.dto;

public class ResultDto {

    private String category;
    private String schoolsubject;
    private String average;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSchoolsubject() {
        return schoolsubject;
    }

    public void setSchoolsubject(String schoolsubject) {
        this.schoolsubject = schoolsubject;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
