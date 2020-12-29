package be.henallux.smartclass.services;

import java.util.ArrayList;

import be.henallux.smartclass.model.Test;

public class TestBusiness {

    public ArrayList<Test> getUnsignedTest() {
        ArrayList<Test> tests = new ArrayList<>();
        tests.add(new Test("Les verbes en -ER", "Conjugaison", null, 20.0, 18.0, "Très bon travail"));
        tests.add(new Test("La table de 7", "Calcul", null, 10.0, 7.0, "Il est vrai que c'est la plus difficile"));
        tests.add(new Test("Bilan Moyen-âge", "Histoire", null, 40.0, 21.0, "Matière à revoir"));
        tests.add(new Test("La règle de 3", "Problèmes", null, 15.0, 10.0, null));
        tests.add(new Test("Les partages inégaux", "Problème", null, 20.0, 14.0, "Continue sur ta lançée"));
        return tests;
    }
}
