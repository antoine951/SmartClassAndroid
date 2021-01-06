package be.henallux.smartclass.repositories.dto;

public class TestDto {

    private String title;
    private Double result;
    private String note;
    private Double maxvalue;
    private String category;
    private String schoolsubject;

    public TestDto(String title, Double result, String note, Double maxvalue, String category, String schoolsubject) {
        this.title = title;
        this.result = result;
        this.note = note;
        this.maxvalue = maxvalue;
        this.category = category;
        this.schoolsubject = schoolsubject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(Double maxvalue) {
        this.maxvalue = maxvalue;
    }

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
}
