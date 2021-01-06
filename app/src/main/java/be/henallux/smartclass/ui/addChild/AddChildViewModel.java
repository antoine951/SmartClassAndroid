package be.henallux.smartclass.ui.addChild;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import be.henallux.smartclass.model.requestLogin;
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
        smartClassWebService.addchild(("Bearer " + SaveSharedPreference.getLoggedInUser(getApplication())).replace("\"", ""),new requestLogin(username,password)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                if (response.isSuccessful()) {
                    _message.setValue("Enfant ajouté");
                } else {
                    _message.setValue("La combinaison nom/mot de passe est invalide!");
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _message.setValue("Vérifiez votre connexion internet!");
                } else {
                    _message.setValue("Une erreur inconnue est survenue, veuillez réessayer!");
                }

            }
        });
    }

    public LiveData<String> getMessage() {
        return message;
    }

}
