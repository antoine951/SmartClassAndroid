package be.henallux.smartclass.ui.pupilChoosing;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import be.henallux.smartclass.repositories.RetrofitConfigurationService;
import be.henallux.smartclass.repositories.SmartClassWebService;
import be.henallux.smartclass.repositories.dto.PupilDto;
import be.henallux.smartclass.model.Pupil;
import be.henallux.smartclass.services.mappers.PupilMapper;
import be.henallux.smartclass.utils.errors.NoConnectivityException;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PupilChoosingViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Pupil>> _children = new MutableLiveData<>();
    private LiveData<ArrayList<Pupil>> children = _children;

    private MutableLiveData<String> _message = new MutableLiveData<>();
    private LiveData<String> message = _message;

    private PupilMapper pupilMapper;

    public PupilChoosingViewModel(@NonNull Application application) {
        super(application);
        SmartClassWebService smartClassWebService = RetrofitConfigurationService.getInstance(application).smartClassService();
        this.pupilMapper = PupilMapper.getInstance();
        smartClassWebService.getChildren(("Bearer " + SaveSharedPreference.getLoggedInUser(getApplication())).replace("\"","")).enqueue(new Callback<ArrayList<PupilDto>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<PupilDto>> call, @NotNull Response<ArrayList<PupilDto>> response) {
                if (response.isSuccessful()) {
                    ArrayList<PupilDto> pupilsDto = response.body();
                    ArrayList<Pupil> pupils = new ArrayList<>();
                    if (pupilsDto != null) {
                        for (PupilDto p : pupilsDto) {
                            pupils.add(pupilMapper.mapToPupil(p));
                        }
                    }
                    _children.setValue(pupils);
                    _message.setValue(null);
                } else {
                    _message.setValue("Une erreur est survenue lors de la requête");
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<PupilDto>> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _message.setValue("Vérifiez votre connexion internet!");
                } else {
                    _message.setValue("Une erreur inconnue est survenue, veuillez réessayer!");
                }
            }
        });
    }

    public LiveData<ArrayList<Pupil>> getChildren() {
        return children;
    }

    public LiveData<String> getMessage() {
        return message;
    }
}
