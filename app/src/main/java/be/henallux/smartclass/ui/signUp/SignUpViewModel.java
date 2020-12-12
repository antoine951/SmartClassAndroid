package be.henallux.smartclass.ui.signUp;

import android.util.Patterns;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import be.henallux.smartclass.R;



public class SignUpViewModel extends ViewModel {

    private MutableLiveData<SignUpFormState> signUpFormState = new MutableLiveData<>();

    public SignUpViewModel() { }

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
}
