package be.henallux.smartclass.model;

public class SubjectScore {

    private String Subject;
    private double result;

    public SubjectScore(String subject, Double result) {
        setSubject(subject);
        setResult(result);
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
