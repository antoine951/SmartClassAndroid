package be.henallux.smartclass.business;

import java.util.ArrayList;

import be.henallux.smartclass.model.SubjectScore;
import be.henallux.smartclass.model.Test;

public class ReportCardBusiness {
    private ArrayList<SubjectScore> french;
    private double frenchMean = -1;
    private ArrayList<SubjectScore> math;
    private double mathMean = -1;
    private ArrayList<SubjectScore> sciences;
    private double sciencesMean = -1;
    private ArrayList<SubjectScore> other;
    private double otherMean = -1;
    private double generalMean = -1;

    /*
    * same importance for each category: general mean = mean of each category/nb category
     */
    public ReportCardBusiness() {
        double countGeneralMean = 0;
        this.french=new ArrayList<>();
        ArrayList<Test> french = getTests("Français", 1);
        if (french.size() > 0) {
            this.frenchMean = makeReportCard(french, this.french);
            this.generalMean += this.frenchMean;
            countGeneralMean += 1;
        }

        this.math=new ArrayList<>();
        ArrayList<Test> math = getTests("Math", 2);
        if (math.size() > 0) {
            this.mathMean = makeReportCard(math, this.math);
            this.generalMean += this.mathMean;
            countGeneralMean += 1;
        }

        this.sciences=new ArrayList<>();
        ArrayList<Test> sciences = getTests("Eveil", 3);
        if (sciences.size() > 0) {
            this.sciencesMean = makeReportCard(sciences, this.sciences);
            this.generalMean += this.sciencesMean;
            countGeneralMean += 1;
        }

        this.other=new ArrayList<>();
        ArrayList<Test> other = getTests(null, 4);
        if (other.size() > 0) {
            this.otherMean = makeReportCard(other, this.other);
            this.generalMean += this.otherMean;
            countGeneralMean += 1;
        }
        this.generalMean /= countGeneralMean;
    }

    //restriction taille categories
    private ArrayList<Test> getTests(String category, int count) {
        ArrayList<Test> tests = new ArrayList<>();
        if (count == 1) {
            tests.add(new Test("Les verbes en -ER", "Conjugaison", "Francçais", 20.0, 18.0, "Très bon travail"));
            tests.add(new Test("Les verbes en -ER", "Grammaire", "Francçais", 20.0, 16.5, "Très bon travail"));
            tests.add(new Test("Les verbes en -ER", "Orthographe", "Francçais", 20.0, 18.5, "Très bon travail"));
            tests.add(new Test("Les verbes en -ER", "Dictée", "Francçais", 20.0, 18.0, "Très bon travail"));
            tests.add(new Test("Les verbes en -ER", "Expression orale", "Francçais", 20.0, 13.0, "Très bon travail"));
            tests.add(new Test("Les verbes en -ER", "Expression écrite", "Francçais", 20.0, 12.0, "Très bon travail"));
            tests.add(new Test("Les verbes en -ER", "Lecture", "Francçais", 20.0, 17.0, "Très bon travail"));
            tests.add(new Test("Les verbes en -ER", "Audition", "Francçais", 20.0, 12.5, "Très bon travail"));
        } else if (count == 2) {
            tests.add(new Test("La table de 7", "Calcul", "Math", 10.0, 7.0, "Il est vrai que c'est la plus difficile"));
            tests.add(new Test("La règle de 3", "Problèmes", "Math", 15.0, 10.0, null));
            tests.add(new Test("Les partages inégaux", "Problèmes", "Math", 20.0, 14.0, "Continue sur ta lançée"));
        } else if (count == 3) {
            tests.add(new Test("Bilan Moyen-âge", "Histoire", "Eveil", 40.0, 21.0, "Matière à revoir"));
        } else {
            tests.add(new Test("gym", "Gym", null, 10.0, 9.0, null));
        }
        return tests;
    }

    private double makeReportCard(ArrayList<Test> tests, ArrayList<SubjectScore> category) {
        double cumulCategory = 0;
        double cumulMaxVal = 0;
        double cumulValue = 0;
        String previous = tests.get(0).getSubjectName();
        while (tests.size()>0) {
            if (tests.get(0).getSubjectName().equals(previous)) {
                cumulMaxVal += tests.get(0).getMaxValue();
                cumulValue += tests.get(0).getValue();
            } else {
                cumulCategory += (cumulValue / cumulMaxVal) * 100;
                category.add(new SubjectScore(previous, (cumulValue / cumulMaxVal) * 100));
                cumulMaxVal = tests.get(0).getMaxValue();
                cumulValue = tests.get(0).getValue();
                previous = tests.get(0).getSubjectName();
            }
            tests.remove(0);
        }
        cumulCategory += (cumulValue / cumulMaxVal) * 100;
        category.add(new SubjectScore(previous, (cumulValue / cumulMaxVal) * 100));
        return cumulCategory / (double) category.size();
    }


    public ArrayList<SubjectScore> getFrench() {
        return french;
    }

    public ArrayList<SubjectScore> getMath() {
        return math;
    }


    public ArrayList<SubjectScore> getOther() {
        return other;
    }

    public double getFrenchMean() {
        return frenchMean;
    }

    public double getMathMean() {
        return mathMean;
    }

    public ArrayList<SubjectScore> getSciences() {
        return sciences;
    }

    public double getSciencesMean() {
        return sciencesMean;
    }

    public double getOtherMean() {
        return otherMean;
    }

    public double getGeneralMean() {
        return generalMean;
    }
}
