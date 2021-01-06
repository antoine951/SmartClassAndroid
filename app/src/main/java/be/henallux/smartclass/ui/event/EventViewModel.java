package be.henallux.smartclass.ui.event;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import be.henallux.smartclass.model.Event;
import be.henallux.smartclass.repositories.RetrofitConfigurationService;
import be.henallux.smartclass.repositories.SmartClassWebService;
import be.henallux.smartclass.repositories.dto.EventDto;
import be.henallux.smartclass.services.EventBusiness;
import be.henallux.smartclass.services.mappers.EventMapper;
import be.henallux.smartclass.utils.errors.NoConnectivityException;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Event>> _eventListWeek = new MutableLiveData<>();
    private LiveData<ArrayList<Event>> eventListWeek = _eventListWeek;

    private MutableLiveData<ArrayList<Event>> _eventListMonth = new MutableLiveData<>();
    private LiveData<ArrayList<Event>> eventListMonth = _eventListMonth;

    private MutableLiveData<ArrayList<Event>> _eventListComing = new MutableLiveData<>();
    private LiveData<ArrayList<Event>> eventListComing = _eventListComing;

    private MutableLiveData<String> _message = new MutableLiveData<>();
    private LiveData<String> message = _message;

    private EventMapper eventMapper;

    public EventViewModel(@NonNull Application application) {
        super(application);
        SmartClassWebService smartClassWebService = RetrofitConfigurationService.getInstance(application).smartClassService();
        this.eventMapper = EventMapper.getInstance();
        smartClassWebService.getEvents(("Bearer " + SaveSharedPreference.getCurrentChild(getApplication())).replace("\"", "")).enqueue(new Callback<ArrayList<EventDto>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<EventDto>> call, @NotNull Response<ArrayList<EventDto>> response) {
                if (response.isSuccessful()) {

                    ArrayList<EventDto> eventsDto = response.body();
                    ArrayList<Event> events = new ArrayList<>();
                    assert eventsDto != null;
                    for (EventDto e : eventsDto) {
                        events.add(eventMapper.mapToEvent(e));
                    }

                    EventBusiness eventBusiness = new EventBusiness(events);
                    _eventListWeek.setValue(eventBusiness.getEventThisWeek());
                    _eventListMonth.setValue(eventBusiness.getEventThisMonth());
                    _eventListComing.setValue(eventBusiness.getEventComing());

                    _message.setValue(null);
                } else {
                    _message.setValue("Une erreur est survenue lors de la requête");
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<EventDto>> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _message.setValue("Vérifiez votre connexion internet!");
                } else {
                    _message.setValue("Une erreur inconnue est survenue, veuillez réessayer!");
                }
            }
        });
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

    public LiveData<String> getMessage() {
        return message;
    }
}