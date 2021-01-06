package be.henallux.smartclass.model;


public class Test {

    private String title;
    private Double result;
    private String note;
    private Double maxValue;
    private String category;
    private String schoolSubject;

    public Test() { }

    public Test(String title, Double result, String note, Double maxValue, String category, String schoolSubject) {
        this.title = title;
        this.result = result;
        this.note = note;
        this.maxValue = maxValue;
        this.category = category;
        this.schoolSubject = schoolSubject;
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

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
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
}


