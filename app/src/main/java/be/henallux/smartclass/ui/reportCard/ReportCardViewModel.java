package be.henallux.smartclass.ui.reportCard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.Result;
import be.henallux.smartclass.repositories.RetrofitConfigurationService;
import be.henallux.smartclass.repositories.SmartClassWebService;
import be.henallux.smartclass.repositories.dto.ResultDto;
import be.henallux.smartclass.services.ReportCardBusiness;
import be.henallux.smartclass.services.mappers.ResultMapper;
import be.henallux.smartclass.utils.errors.NoConnectivityException;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportCardViewModel extends AndroidViewModel {

    private MutableLiveData<String> _frenchMean = new MutableLiveData<>();
    private LiveData<String> frenchMean =_frenchMean;
    private MutableLiveData<String> _mathMean = new MutableLiveData<>();
    private LiveData<String> mathMean = _mathMean;
    private MutableLiveData<String> _sciencesMean = new MutableLiveData<>();
    private LiveData<String> sciencesMean = _sciencesMean;
    private MutableLiveData<String> _otherMean = new MutableLiveData<>();
    private LiveData<String> otherMean = _otherMean;

    private MutableLiveData<ArrayList<Result>> _french = new MutableLiveData<>();
    private LiveData<ArrayList<Result>> french = _french;
    private MutableLiveData<ArrayList<Result>> _math = new MutableLiveData<>();
    private LiveData<ArrayList<Result>> math = _math;
    private MutableLiveData<ArrayList<Result>> _sciences = new MutableLiveData<>();
    private LiveData<ArrayList<Result>> sciences = _sciences;
    private MutableLiveData<ArrayList<Result>> _other = new MutableLiveData<>();
    private LiveData<ArrayList<Result>> other = _other;

    private MutableLiveData<String> _message = new MutableLiveData<>();
    private LiveData<String> message = _message;


    public ReportCardViewModel(@NonNull Application application) {
        super(application);

        SmartClassWebService smartClassWebService = RetrofitConfigurationService.getInstance(application).smartClassService();
        ResultMapper resultMapper = ResultMapper.getInstance();

        smartClassWebService.getDetailResult(("Bearer " + SaveSharedPreference.getCurrentChild(getApplication())).replace("\"", "")).enqueue(new Callback<ArrayList<ResultDto>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<ResultDto>> call, @NotNull Response<ArrayList<ResultDto>> response) {
                if (response.isSuccessful()) {
                    ArrayList<ResultDto> ResultDto = response.body();
                    ArrayList<Result> Results = new ArrayList<>();
                    assert ResultDto != null;
                    for (ResultDto res : ResultDto) {
                        Results.add(resultMapper.mapToResult(res));
                    }
                    ReportCardBusiness reportCardBusiness = new ReportCardBusiness(Results);
                    _french.setValue(reportCardBusiness.getFrench());
                    _math.setValue(reportCardBusiness.getMath());
                    _sciences.setValue(reportCardBusiness.getSciences());
                    _other.setValue(reportCardBusiness.getOther());
                    _message.setValue(null);
                } else {
                    _message.setValue(getApplication().getString(R.string.queryError));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<ResultDto>> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _message.setValue(getApplication().getString(R.string.internetError));
                } else {
                    _message.setValue(getApplication().getString(R.string.generalError));
                }
            }
        });

        smartClassWebService.getGlobalResult(("Bearer " + SaveSharedPreference.getCurrentChild(getApplication())).replace("\"", "")).enqueue(new Callback<ArrayList<ResultDto>>() {
            @Override
            public void onResponse(@NotNull Call<ArrayList<ResultDto>> call, @NotNull Response<ArrayList<ResultDto>> response) {
                if (response.isSuccessful()) {
                    ArrayList<ResultDto> ResultDto = response.body();
                    ArrayList<Result> Results = new ArrayList<>();
                    assert ResultDto != null;
                    for (ResultDto res : ResultDto) {
                        Results.add(resultMapper.mapToResult(res));
                    }
                    ReportCardBusiness reportCardBusiness = new ReportCardBusiness(Results);
                    _frenchMean.setValue((reportCardBusiness.getFrench().size()>0)?reportCardBusiness.getFrench().get(0).getAverage():" -");
                    _mathMean.setValue((reportCardBusiness.getMath().size()>0)?reportCardBusiness.getMath().get(0).getAverage():" -");
                    _sciencesMean.setValue((reportCardBusiness.getSciences().size()>0)?reportCardBusiness.getSciences().get(0).getAverage():" -");
                    _otherMean.setValue((reportCardBusiness.getOther().size()>0)?reportCardBusiness.getOther().get(0).getAverage():" -");
                    _message.setValue(null);
                } else {
                    _message.setValue(getApplication().getString(R.string.queryError));
                }
            }

            @Override
            public void onFailure(@NotNull Call<ArrayList<ResultDto>> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _message.setValue(getApplication().getString(R.string.internetError));
                } else {
                    _message.setValue(getApplication().getString(R.string.generalError));
                }
            }
        });
    }

    public LiveData<String> getFrenchMean() {
        return frenchMean;
    }

    public LiveData<String> getMathMean() {
        return mathMean;
    }

    public LiveData<String> getSciencesMean() {
        return sciencesMean;
    }

    public LiveData<String> getOtherMean() {
        return otherMean;
    }

    public LiveData<ArrayList<Result>> getFrench() {
        return french;
    }

    public LiveData<ArrayList<Result>> getMath() {
        return math;
    }

    public LiveData<ArrayList<Result>> getSciences() {
        return sciences;
    }

    public LiveData<ArrayList<Result>> getOther() {
        return other;
    }

    public LiveData<String> getMessage() {
        return message;
    }
}