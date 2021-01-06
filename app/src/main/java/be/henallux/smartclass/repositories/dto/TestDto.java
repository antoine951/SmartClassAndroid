package be.henallux.smartclass.repositories.dto;

public class TestDto {

    private Integer idtest;
    private String title;
    private Double result;
    private String note;
    private Double maxvalue;
    private String category;
    private String schoolsubject;

    public TestDto(Integer idtest, String title, Double result, String note, Double maxvalue, String category, String schoolsubject) {
        this.idtest = idtest;
        this.title = title;
        this.result = result;
        this.note = note;
        this.maxvalue = maxvalue;
        this.category = category;
        this.schoolsubject = schoolsubject;
    }

    public Integer getIdtest() {
        return idtest;
    }

    public void setIdtest(Integer idtest) {
        this.idtest = idtest;
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
