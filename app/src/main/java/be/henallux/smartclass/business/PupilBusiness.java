package be.henallux.smartclass.business;

import java.util.ArrayList;

import be.henallux.smartclass.model.Pupil;

public class PupilBusiness {

    public ArrayList<Pupil> getChilden(){
        ArrayList<Pupil> children = new ArrayList<>();
        children.add(new Pupil("login","password","Luc", "Skywalker"));
        children.add(new Pupil("login","password","Maître", "Yoda"));
        children.add(new Pupil("login","password","Général", "Grivious"));
        children.add(new Pupil("login","password","Chew", "Bacca"));
        return children;
    }
}
