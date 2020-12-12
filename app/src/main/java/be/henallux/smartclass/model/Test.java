package be.henallux.smartclass.model;


public class Test {

    private String title;
    private String subjectName;
    private String category;
    private Double maxValue;
    private Double value;
    private String note;

    public Test(String title, String subjectName, String category, Double maxValue, Double value, String note) {
        setTitle(title);
        setSubjectName(subjectName);
        setCategory(category);
        setMaxValue(maxValue);
        setValue(value);
        setNote(note);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}


