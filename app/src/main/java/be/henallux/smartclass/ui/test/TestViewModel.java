package be.henallux.smartclass.ui.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.Test;
import be.henallux.smartclass.repositories.RetrofitConfigurationService;
import be.henallux.smartclass.repositories.SmartClassWebService;
import be.henallux.smartclass.repositories.dto.TestDto;
import be.henallux.smartclass.services.mappers.TestMapper;
import be.henallux.smartclass.utils.errors.NoConnectivityException;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Test>> _tests = new MutableLiveData<>();
    private LiveData<ArrayList<Test>> tests = _tests;

    private MutableLiveData<String> _message = new MutableLiveData<>();
    private LiveData<String> message = _message;

    private SmartClassWebService smartClassWebService;
    private TestMapper testMapper;

    public TestViewModel(@NonNull Application application) {
        super(application);
        smartClassWebService = RetrofitConfigurationService.getInstance(application).smartClassService();
        this.testMapper = TestMapper.getInstance();

        smartClassWebService.getUnsignedTests(("Bearer " + SaveSharedPreference.getCurrentChild(getApplication())).replace("\"", "")).enqueue(new Callback<ArrayList<TestDto>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<TestDto>> call, @NotNull Response<ArrayList<TestDto>> response) {
                if (response.isSuccessful()) {

                    ArrayList<TestDto> testDto = response.body();
                    ArrayList<Test> tests = new ArrayList<>();
                    assert testDto != null;
                    for (TestDto t : testDto) {
                        tests.add(testMapper.mapToTest(t));
                    }
                    _tests.setValue(tests);
                    _message.setValue(null);
                } else {
                    _message.setValue(getApplication().getString(R.string.queryError));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<TestDto>> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _message.setValue(getApplication().getString(R.string.internetError));
                } else {
                    _message.setValue(getApplication().getString(R.string.generalError));
                }
            }
        });
    }

    public void sign(Integer idTest){
        smartClassWebService.sign(("Bearer " + SaveSharedPreference.getLoggedInUser(getApplication())).replace("\"", ""),idTest).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.isSuccessful()) {
                    _message.setValue(getApplication().getString(R.string.signTest));
                } else {
                    _message.setValue(getApplication().getString(R.string.queryError));
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _message.setValue(getApplication().getString(R.string.internetError));
                } else {
                    _message.setValue(getApplication().getString(R.string.generalError));
                }
            }
        });
    }
    public LiveData<ArrayList<Test>> getUnsignedTestList() {
        return tests;
    }

    public LiveData<String> getMessage() {
        return message;
    }
}