package be.henallux.smartclass.ui.test;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import be.henallux.smartclass.services.TestBusiness;
import be.henallux.smartclass.model.Test;

public class TestViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Test>> testList;

    public TestViewModel() {
        TestBusiness testBusiness = new TestBusiness();
        testList = new MutableLiveData<>();
        testList.setValue(testBusiness.getUnsignedTest());
    }

    public LiveData<ArrayList<Test>> getUnsignedTestList() {
        return testList;
    }
}