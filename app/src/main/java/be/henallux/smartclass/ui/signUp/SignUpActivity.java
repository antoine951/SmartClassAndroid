package be.henallux.smartclass.ui.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.Tutor;
import be.henallux.smartclass.ui.login.LoginActivity;

public class SignUpActivity extends AppCompatActivity {

    private SignUpViewModel signUpViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        final EditText emailEditText = findViewById(R.id.emailPrompt);
        final EditText passwordEditText = findViewById(R.id.passwordPrompt);
        final EditText confirmPasswordEditText = findViewById(R.id.confirmPasswordPrompt);
        final EditText firstNameEditText = findViewById(R.id.firstnamePrompt);
        final EditText lastNameEditText = findViewById(R.id.lastNamePrompt);
        final EditText phoneEditText = findViewById(R.id.phonePrompt);
        final Button registerButton = findViewById(R.id.register);

        signUpViewModel.getMessage().observe(this, message -> {
            if(message!=null) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                if (message.equals(getApplication().getString(R.string.userAdd))) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    finish();
                    startActivity(i);
                }
            }
        });

        registerButton.setOnClickListener(v->{
            Tutor tutor = new Tutor(emailEditText.getText().toString(),passwordEditText.getText().toString(),firstNameEditText.getText().toString(),lastNameEditText.getText().toString(),phoneEditText.getText().toString());
            signUpViewModel.signUp(tutor);
        });

        signUpViewModel.getSignUpFormState().observe(this, signUpFormState -> {
            if (signUpFormState == null) {
                return;
            }
            registerButton.setEnabled(signUpFormState.isDataValid());
            if (signUpFormState.getUsernameError() != null) {
                emailEditText.setError(getString(signUpFormState.getUsernameError()));
            }
            if (signUpFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(signUpFormState.getPasswordError()));
            }
            if (signUpFormState.getConfirmPasswordError() != null) {
                confirmPasswordEditText.setError(getString(signUpFormState.getConfirmPasswordError()));
            }
            if (signUpFormState.getFirstnameError() != null) {
                firstNameEditText.setError(getString(signUpFormState.getFirstnameError()));
            }
            if (signUpFormState.getLastnameError() != null) {
                lastNameEditText.setError(getString(signUpFormState.getLastnameError()));
            }
            if (signUpFormState.getPhoneError() != null) {
                phoneEditText.setError(getString(signUpFormState.getPhoneError()));
            }

        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                signUpViewModel.signUpDataChanged(emailEditText.getText().toString(),
                        passwordEditText.getText().toString(),confirmPasswordEditText.getText().toString(),firstNameEditText.getText().toString(),
                        lastNameEditText.getText().toString(),phoneEditText.getText().toString());
            }
        };
        emailEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        confirmPasswordEditText.addTextChangedListener(afterTextChangedListener);
        firstNameEditText.addTextChangedListener(afterTextChangedListener);
        lastNameEditText.addTextChangedListener(afterTextChangedListener);
        phoneEditText.addTextChangedListener(afterTextChangedListener);

    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        finish();
        startActivity(i);
    }

}