package be.henallux.smartclass.ui.addChild;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.RequestLogin;
import be.henallux.smartclass.repositories.RetrofitConfigurationService;
import be.henallux.smartclass.repositories.SmartClassWebService;
import be.henallux.smartclass.utils.errors.NoConnectivityException;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddChildViewModel extends AndroidViewModel {

    private MutableLiveData<String> _message = new MutableLiveData<>();
    private LiveData<String> message = _message;

    private SmartClassWebService smartClassWebService;

    public AddChildViewModel(@NonNull Application application){
        super(application);
        this.smartClassWebService = RetrofitConfigurationService.getInstance(application).smartClassService();
    }

    public void addChild(String username, String password){
        _message.setValue(null);
        smartClassWebService.addChild(("Bearer " + SaveSharedPreference.getLoggedInUser(getApplication())).replace("\"", ""),new RequestLogin(username,password)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                switch(response.code()){
                    case 200:
                        _message.setValue(getApplication().getString(R.string.childAdd));
                        break;
                    case 401:
                        _message.setValue(getApplication().getString(R.string.loginError));
                        break;
                    case 404:
                        _message.setValue(getApplication().getString(R.string.userNotFoundError));
                        break;
                    case 409:
                        _message.setValue(getApplication().getString(R.string.childAlreadyLinkedError));
                        break;
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

    public LiveData<String> getMessage() {
        return message;
    }

}
