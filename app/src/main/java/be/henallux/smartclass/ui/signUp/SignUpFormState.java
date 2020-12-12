package be.henallux.smartclass.ui.signUp;

import androidx.annotation.Nullable;

public class SignUpFormState {

    @Nullable
    private Integer usernameError;


    @Nullable
    private Integer passwordError;

    @Nullable
    private Integer confirmPasswordError;

    @Nullable
    private Integer firstnameError;

    @Nullable
    private Integer lastnameError;

    @Nullable
    private Integer phoneError;

    private boolean isDataValid;

    SignUpFormState(@Nullable Integer usernameError, @Nullable Integer passwordError, @Nullable Integer confirmPasswordError,@Nullable Integer firstnameError,@Nullable Integer lastnameError,@Nullable Integer phoneError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.confirmPasswordError = confirmPasswordError;
        this.lastnameError = lastnameError;
        this.firstnameError = firstnameError;
        this.phoneError = phoneError;
        this.isDataValid = false;
    }

    SignUpFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.confirmPasswordError=null;
        this.lastnameError = null;
        this.firstnameError = null;
        this.phoneError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    public Integer getConfirmPasswordError() {
        return confirmPasswordError;
    }

    @Nullable
    public Integer getFirstnameError() {
        return firstnameError;
    }

    @Nullable
    public Integer getLastnameError() {
        return lastnameError;
    }

    @Nullable
    public Integer getPhoneError() {
        return phoneError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
