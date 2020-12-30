package be.henallux.smartclass.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.app.Application;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.Tutor;
import be.henallux.smartclass.repositories.RetrofitConfigurationService;
import be.henallux.smartclass.repositories.SmartClassWebService;
import be.henallux.smartclass.repositories.dto.TutorDto;
import be.henallux.smartclass.services.mappers.TutorMapper;
import be.henallux.smartclass.ui.pupilChoosing.PupilChoosingActivity;
import be.henallux.smartclass.utils.errors.NoConnectivityException;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();

    private MutableLiveData<String> _tutorToken = new MutableLiveData<>();
    private LiveData<String> tutorToken = _tutorToken;

    private MutableLiveData<String> _message = new MutableLiveData<>();
    private LiveData<String> error = _message;

    private MutableLiveData<Boolean> _isLog = new MutableLiveData<>();
    private LiveData<Boolean> isLog = _isLog;

    private SmartClassWebService smartClassWebService;

    private Application app;

    public LoginViewModel(@NonNull Application application){
        super(application);
        this.smartClassWebService = RetrofitConfigurationService.getInstance(application).smartClassService();
        this.app =application;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    public void login(String username, String password){
        _message.setValue(null);
        smartClassWebService.login(new Tutor(username,password)).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                if (response.isSuccessful()) {
                    _tutorToken.setValue(response.body());
                    _message.setValue(null);
                    _isLog.setValue(true);
                    SaveSharedPreference.setLoggedIn(app,true);
                } else {
                    _isLog.setValue(false);
                    _message.setValue("La combinaison email/mot de passe est invalide!");
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _isLog.setValue(false);
                    _message.setValue("Vérifiez votre connexion internet!");
                } else {
                    _isLog.setValue(false);
                    _message.setValue("Une erreur inconnue est survenue, veuillez réessayer!");
                }

            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("@")&&Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 7;
    }

    public LiveData<String> getTutor() {
        return tutorToken;
    }

    public LiveData<String> getMessage() {
        return error;
    }

    public LiveData<Boolean> getIsLog() {
        return isLog;
    }
}