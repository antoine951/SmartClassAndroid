package be.henallux.smartclass.ui.signUp;

import android.app.Application;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.Tutor;
import be.henallux.smartclass.repositories.RetrofitConfigurationService;
import be.henallux.smartclass.repositories.SmartClassWebService;
import be.henallux.smartclass.utils.errors.NoConnectivityException;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpViewModel extends AndroidViewModel {

    private MutableLiveData<SignUpFormState> signUpFormState = new MutableLiveData<>();

    private MutableLiveData<String> _message = new MutableLiveData<>();
    private LiveData<String> message = _message;

    private SmartClassWebService smartClassWebService;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        this.smartClassWebService = RetrofitConfigurationService.getInstance(application).smartClassService();
    }

    public void signUp(Tutor tutor){
        _message.setValue(null);
        smartClassWebService.signUp(tutor).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {

                switch(response.code()) {
                    case 200:
                        _message.setValue(getApplication().getString(R.string.userAdd));
                        break;
                    case 401:
                        _message.setValue(getApplication().getString(R.string.loginError));
                        break;
                    case 409:
                        _message.setValue(getApplication().getString(R.string.signUpError));
                        break;
                    default:
                        _message.setValue(getApplication().getString(R.string.generalError));
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

    LiveData<SignUpFormState> getSignUpFormState() {
        return signUpFormState;
    }

    public void signUpDataChanged(String username, String password,String confirmPassword, String firstname, String lastname, String phone) {
        if (!isUserNameValid(username)) {
            signUpFormState.setValue(new SignUpFormState(R.string.invalid_username,null,null,null,null,null));
        } else if (!isPasswordValid(password)) {
            signUpFormState.setValue(new SignUpFormState(null, R.string.invalid_password,null,null,null,null));
        } else if (!isConfirmPasswordValid(password,confirmPassword)) {
            signUpFormState.setValue(new SignUpFormState(null, null,R.string.invalid_confirm_password,null,null,null));
        }else if (isNameValid(firstname)) {
            signUpFormState.setValue(new SignUpFormState(null, null, null,R.string.invalid_name,null,null));
        } else if (isNameValid(lastname)) {
            signUpFormState.setValue(new SignUpFormState(null, null,null,null,R.string.invalid_name,null));
        } else if (!isPhoneValid(phone)) {
            signUpFormState.setValue(new SignUpFormState(null, null,null,null,null,R.string.invalid_phone));
        }else {
            signUpFormState.setValue(new SignUpFormState(true));
        }
    }

    // email check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return username.contains("@")&&Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    // password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 7;
    }

    //confirm password
    private boolean isConfirmPasswordValid(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

    // name validation check
    private boolean isNameValid(String name) {
        return name.length() <= 0;
    }

    // phone validation check
    private boolean isPhoneValid(String phone) {
        return phone != null && phone.matches("[0-9]{9,10}");
    }

    public LiveData<String> getMessage() {
        return message;
    }
}
