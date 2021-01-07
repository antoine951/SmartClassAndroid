package be.henallux.smartclass.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import be.henallux.smartclass.R;
import be.henallux.smartclass.ui.pupilChoosing.PupilChoosingActivity;
import be.henallux.smartclass.ui.signUp.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final Button signUpButton = findViewById(R.id.signUp);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);


        loginViewModel.getIsLog().observe(this, isLog -> {
            if (isLog) {
                loadingProgressBar.setVisibility(View.GONE);
                loginButton.setEnabled(true);
                signUpButton.setEnabled(true);
                Intent intent = new Intent(getApplicationContext(), PupilChoosingActivity.class);
                startActivity(intent);
            }
        });

        loginViewModel.getMessage().observe(this, message -> {
            if (message != null) {
                loadingProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
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
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
            return false;
        });

        signUpButton.setOnClickListener(this::navigateToSignUp);
        loginButton.setOnClickListener(v -> {
            loginButton.setEnabled(false);
            signUpButton.setEnabled(false);
            loadingProgressBar.setVisibility(View.VISIBLE);
            loginViewModel.login(usernameEditText.getText().toString(), passwordEditText.getText().toString());
        });
    }


    private void navigateToSignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        finish();
        startActivity(intent);
    }
}