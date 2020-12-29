package be.henallux.smartclass.ui.event;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import be.henallux.smartclass.services.EventBusiness;
import be.henallux.smartclass.model.Event;

public class EventViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Event>> eventListWeek;
    private MutableLiveData<ArrayList<Event>> eventListMonth;
    private MutableLiveData<ArrayList<Event>> eventListComing;

    public EventViewModel() {
        EventBusiness eventBusiness = new EventBusiness();
        eventListWeek = new MutableLiveData<>();
        eventListWeek.setValue(eventBusiness.getEventThisWeek());

        eventListMonth = new MutableLiveData<>();
        eventListMonth.setValue(eventBusiness.getEventThisMonth());

        eventListComing = new MutableLiveData<>();
        eventListComing.setValue(eventBusiness.getEventComing());
    }

    public LiveData<ArrayList<Event>> getEventListWeek() {
        return eventListWeek;
    }

    public LiveData<ArrayList<Event>> getEventListMonth() {
        return eventListMonth;
    }

    public LiveData<ArrayList<Event>> getEventListComing() {
        return eventListComing;
    }
}