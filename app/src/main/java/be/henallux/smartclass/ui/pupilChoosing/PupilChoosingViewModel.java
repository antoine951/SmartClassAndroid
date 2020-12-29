package be.henallux.smartclass.ui.pupilChoosing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import be.henallux.smartclass.services.PupilBusiness;
import be.henallux.smartclass.model.Pupil;

public class PupilChoosingViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Pupil>> children;

    public PupilChoosingViewModel() {
        PupilBusiness pupilBusiness = new PupilBusiness();
        children = new MutableLiveData<>();
        children.setValue(pupilBusiness.getChilden());
    }

    public LiveData<ArrayList<Pupil>> getChildren() {
        return children;
    }
}
