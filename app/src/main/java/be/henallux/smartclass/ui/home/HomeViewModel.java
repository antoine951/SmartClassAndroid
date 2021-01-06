package be.henallux.smartclass.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import be.henallux.smartclass.formater.Utils;
import be.henallux.smartclass.model.Event;
import be.henallux.smartclass.model.Task;
import be.henallux.smartclass.services.EventBusiness;
import be.henallux.smartclass.services.ReportCardBusiness;
import be.henallux.smartclass.services.TaskBusiness;

public class HomeViewModel extends ViewModel {

    /*private MutableLiveData<String> mTextTask = new MutableLiveData<>();
    private MutableLiveData<String> mTextTest;
    private MutableLiveData<String> mTextEvent;*/

    private MutableLiveData<String> mFrenchMean;
    private MutableLiveData<String> mMathMean;
    private MutableLiveData<String> mSciencesMean;
    private MutableLiveData<String> mOtherMean;

    public HomeViewModel() {
        ArrayList<Task> tasks = new ArrayList<>();
        TaskBusiness taskBusiness = new TaskBusiness(tasks);

        ReportCardBusiness reportCardBusiness = new ReportCardBusiness();

        ArrayList<Event> events = new ArrayList<>();
        EventBusiness eventBusiness = new EventBusiness(events);

        /*mTextTask = new MutableLiveData<>();
        mTextTask.setValue(Integer.toString(taskBusiness.getTasks().get(1).size()));

        mTextTest= new MutableLiveData<>();
        mTextTest.setValue("0");

        mTextEvent= new MutableLiveData<>();
        mTextEvent.setValue(Integer.toString(eventBusiness.getEventThisWeek().size()+ eventBusiness.getEventThisMonth().size()));*/


        //reportCard

        mFrenchMean = new MutableLiveData<>();
        mFrenchMean.setValue((reportCardBusiness.getFrenchMean()>-1)? Utils.formattedPercent(reportCardBusiness.getFrenchMean()):"");

        mMathMean = new MutableLiveData<>();
        mMathMean.setValue((reportCardBusiness.getMathMean()>-1)?Utils.formattedPercent(reportCardBusiness.getMathMean()):"");

        mSciencesMean = new MutableLiveData<>();
        mSciencesMean.setValue((reportCardBusiness.getSciencesMean()>-1)?Utils.formattedPercent(reportCardBusiness.getSciencesMean()):"");

        mOtherMean = new MutableLiveData<>();
        mOtherMean.setValue((reportCardBusiness.getOtherMean()>-1)?Utils.formattedPercent(reportCardBusiness.getOtherMean()):"");

    }


    /*public LiveData<String> getTextTask() {
        return mTextTask;
    }

    public LiveData<String> getTextTest() {
        return mTextTest;
    }

    public LiveData<String> getTextEvent() {
        return mTextEvent;
    }*/

    public LiveData<String> getFrenchMean() {
        return mFrenchMean;
    }

    public LiveData<String> getMathMean() {
        return mMathMean;
    }

    public LiveData<String> getSciencesMean() {
        return mSciencesMean;
    }

    public LiveData<String> getOtherMean() { return mOtherMean; }

}