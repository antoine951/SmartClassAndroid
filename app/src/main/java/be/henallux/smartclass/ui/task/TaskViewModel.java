package be.henallux.smartclass.ui.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import be.henallux.smartclass.model.Event;
import be.henallux.smartclass.repositories.RetrofitConfigurationService;
import be.henallux.smartclass.repositories.SmartClassWebService;
import be.henallux.smartclass.repositories.dto.EventDto;
import be.henallux.smartclass.services.EventBusiness;
import be.henallux.smartclass.services.TaskBusiness;
import be.henallux.smartclass.formater.Utils;
import be.henallux.smartclass.model.Task;
import be.henallux.smartclass.services.mappers.EventMapper;
import be.henallux.smartclass.utils.errors.NoConnectivityException;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Task>> firstDayTasks = new MutableLiveData<>();
    private MutableLiveData<String> firstDay = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Task>> secondDayTasks = new MutableLiveData<>();
    private MutableLiveData<String> secondDay = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Task>> thirdDayTasks = new MutableLiveData<>();
    private MutableLiveData<String> thirdDay = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Task>> forthDayTasks = new MutableLiveData<>();
    private MutableLiveData<String> forthDay = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Task>> fifthDayTasks = new MutableLiveData<>();
    private MutableLiveData<String> fifthDay = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Task>> sixthDayTasks = new MutableLiveData<>();
    private MutableLiveData<String> sixthDay = new MutableLiveData<>();

    private MutableLiveData<ArrayList<Task>> seventhDayTasks = new MutableLiveData<>();
    private MutableLiveData<String> seventhDay = new MutableLiveData<>();


    public TaskViewModel(/*@NonNull Application application*/) {
        /*super(application);
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
        });*/
        TaskBusiness taskBusiness = new TaskBusiness();

        init(taskBusiness, firstDay, firstDayTasks, 0);
        init(taskBusiness, secondDay, secondDayTasks, 1);
        init(taskBusiness, thirdDay, thirdDayTasks, 2);
        init(taskBusiness, forthDay, forthDayTasks, 3);
        init(taskBusiness, fifthDay, fifthDayTasks, 4);
        init(taskBusiness, sixthDay, sixthDayTasks, 5);
        init(taskBusiness, seventhDay, seventhDayTasks, 6);
    }

    public LiveData<ArrayList<Task>> getTasksFirstDay() {
        return firstDayTasks;
    }

    public LiveData<String> getFirstDay() {
        return firstDay;
    }

    public LiveData<ArrayList<Task>> getTasksSecondDay() {
        return secondDayTasks;
    }

    public LiveData<String> getSecondDay() {
        return secondDay;
    }

    public LiveData<ArrayList<Task>> getTasksThirdDay() {
        return thirdDayTasks;
    }

    public LiveData<String> getThirdDay() {
        return thirdDay;
    }

    public LiveData<ArrayList<Task>> getTasksForthDay() {
        return forthDayTasks;
    }

    public LiveData<String> getForthDay() {
        return forthDay;
    }

    public LiveData<ArrayList<Task>> getTasksFifthDay() {
        return fifthDayTasks;
    }

    public LiveData<String> getFifthDay() {
        return fifthDay;
    }

    public LiveData<ArrayList<Task>> getTasksSixthDay() {
        return sixthDayTasks;
    }

    public LiveData<String> getSixthDay() {
        return sixthDay;
    }

    public LiveData<ArrayList<Task>> getTasksSeventhDay() {
        return seventhDayTasks;
    }

    public LiveData<String> getSeventhDay() {
        return seventhDay;
    }

    private void init(TaskBusiness taskBusiness, MutableLiveData<String> day, MutableLiveData<ArrayList<Task>> dayTasks, int nday) {
        dayTasks.setValue(taskBusiness.getTasks().get(nday));
        day.setValue(Utils.formattedDate(taskBusiness.getDates().get(nday)));
    }

}