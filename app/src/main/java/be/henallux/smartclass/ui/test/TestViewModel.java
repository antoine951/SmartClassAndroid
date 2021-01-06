package be.henallux.smartclass.ui.test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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

    private TestMapper testMapper;

    public TestViewModel(@NonNull Application application) {
        super(application);
        SmartClassWebService smartClassWebService = RetrofitConfigurationService.getInstance(application).smartClassService();
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
                    _message.setValue("Une erreur est survenue lors de la requête");
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<TestDto>> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _message.setValue("Vérifiez votre connexion internet!");
                } else {
                    _message.setValue("Une erreur inconnue est survenue, veuillez réessayer!");
                }
            }
        });
        /*TestBusiness testBusiness = new TestBusiness();
        testList = new MutableLiveData<>();
        testList.setValue(testBusiness.getUnsignedTest());*/
    }

    public LiveData<ArrayList<Test>> getUnsignedTestList() {
        return tests;
    }

    public LiveData<String> getMessage() {
        return message;
    }
}