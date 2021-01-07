package be.henallux.smartclass.ui.task;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.services.Utils;
import be.henallux.smartclass.model.Task;
import be.henallux.smartclass.repositories.RetrofitConfigurationService;
import be.henallux.smartclass.repositories.SmartClassWebService;
import be.henallux.smartclass.repositories.dto.TaskDto;
import be.henallux.smartclass.services.TaskBusiness;
import be.henallux.smartclass.services.mappers.TaskMapper;
import be.henallux.smartclass.utils.errors.NoConnectivityException;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Task>> _firstDayTasks = new MutableLiveData<>();
    private LiveData<ArrayList<Task>> firstDayTasks=_firstDayTasks;
    private MutableLiveData<String> _firstDay = new MutableLiveData<>();
    private LiveData<String> firstDay=_firstDay;

    private MutableLiveData<ArrayList<Task>> _secondDayTasks = new MutableLiveData<>();
    private LiveData<ArrayList<Task>> secondDayTasks=_secondDayTasks;
    private MutableLiveData<String> _secondDay = new MutableLiveData<>();
    private LiveData<String> secondDay =_secondDay;

    private MutableLiveData<ArrayList<Task>> _thirdDayTasks = new MutableLiveData<>();
    private LiveData<ArrayList<Task>> thirdDayTasks = _thirdDayTasks;
    private MutableLiveData<String> _thirdDay = new MutableLiveData<>();
    private LiveData<String> thirdDay = _thirdDay;

    private MutableLiveData<ArrayList<Task>> _forthDayTasks = new MutableLiveData<>();
    private LiveData<ArrayList<Task>> forthDayTasks = _forthDayTasks;
    private MutableLiveData<String> _forthDay = new MutableLiveData<>();
    private LiveData<String>forthDay = _forthDay;

    private MutableLiveData<ArrayList<Task>> _fifthDayTasks = new MutableLiveData<>();
    private LiveData<ArrayList<Task>> fifthDayTasks = _fifthDayTasks;
    private MutableLiveData<String> _fifthDay = new MutableLiveData<>();
    private LiveData<String> fifthDay=_fifthDay;

    private MutableLiveData<ArrayList<Task>> _sixthDayTasks = new MutableLiveData<>();
    private LiveData<ArrayList<Task>> sixthDayTasks=_sixthDayTasks;
    private MutableLiveData<String> _sixthDay = new MutableLiveData<>();
    private LiveData<String> sixthDay = _sixthDay;

    private MutableLiveData<ArrayList<Task>> _seventhDayTasks = new MutableLiveData<>();
    private LiveData<ArrayList<Task>> seventhDayTasks = _seventhDayTasks;
    private MutableLiveData<String> _seventhDay = new MutableLiveData<>();
    private  LiveData<String> seventhDay = _seventhDay;

    private MutableLiveData<String> _message = new MutableLiveData<>();
    private LiveData<String> message = _message;

    private TaskMapper taskMapper;


    public TaskViewModel(@NonNull Application application) {
        super(application);
        SmartClassWebService smartClassWebService = RetrofitConfigurationService.getInstance(application).smartClassService();
        this.taskMapper = TaskMapper.getInstance();
        smartClassWebService.getTasks(("Bearer " + SaveSharedPreference.getCurrentChild(getApplication())).replace("\"", "")).enqueue(new Callback<ArrayList<TaskDto>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<TaskDto>> call, @NotNull Response<ArrayList<TaskDto>> response) {
                if (response.isSuccessful()) {
                    ArrayList<TaskDto> tasksDto = response.body();
                    ArrayList<Task> tasks = new ArrayList<>();
                    assert tasksDto != null;
                    for (TaskDto t : tasksDto) {
                        tasks.add(taskMapper.mapToTask(t));
                    }
                    TaskBusiness taskBusiness = new TaskBusiness(tasks);
                    init(taskBusiness, _firstDay, _firstDayTasks, 0);
                    init(taskBusiness, _secondDay, _secondDayTasks, 1);
                    init(taskBusiness, _thirdDay, _thirdDayTasks, 2);
                    init(taskBusiness, _forthDay, _forthDayTasks, 3);
                    init(taskBusiness, _fifthDay, _fifthDayTasks, 4);
                    init(taskBusiness, _sixthDay, _sixthDayTasks, 5);
                    init(taskBusiness, _seventhDay, _seventhDayTasks, 6);

                    _message.setValue(null);
                } else {
                    _message.setValue(getApplication().getString(R.string.queryError));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<TaskDto>> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _message.setValue(getApplication().getString(R.string.internetError));
                } else {
                    _message.setValue(getApplication().getString(R.string.generalError));
                }
            }
        });
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

    public LiveData<String> getMessage() {
        return message;
    }

    private void init(TaskBusiness taskBusiness, MutableLiveData<String> day, MutableLiveData<ArrayList<Task>> dayTasks, int nday) {
        dayTasks.setValue(taskBusiness.getTasks().get(nday));
        day.setValue(Utils.formattedDate(taskBusiness.getDates().get(nday)));
    }

}