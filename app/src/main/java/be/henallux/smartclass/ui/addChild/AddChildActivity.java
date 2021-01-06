package be.henallux.smartclass.ui.addChild;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import be.henallux.smartclass.R;
import be.henallux.smartclass.ui.pupilChoosing.PupilChoosingActivity;

public class AddChildActivity extends AppCompatActivity {

    private AddChildViewModel addChildViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        addChildViewModel = new ViewModelProvider(this).get(AddChildViewModel.class);

        final EditText usernameEditText = findViewById(R.id.childLogin);
        final EditText passwordEditText = findViewById(R.id.childPassword);
        final Button addChildButton = findViewById(R.id.childAdd);

        addChildViewModel.getMessage().observe(this, message -> {
            if(message!=null) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                if (message.equals(getApplication().getString(R.string.childAdd))) {
                    Intent i = new Intent(getApplicationContext(), PupilChoosingActivity.class);
                    finish();
                    startActivity(i);
                }
            }
        });

        addChildButton.setOnClickListener(v->{
            String childLogin = usernameEditText.getText().toString();
            String childPassword = passwordEditText.getText().toString();
            if(childLogin.length()>0 && childPassword.length()>0) {
                addChildViewModel.addChild(childLogin, childPassword);
            }else {
                Toast.makeText(getApplicationContext(), getApplication().getString(R.string.emptyFieldError), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), PupilChoosingActivity.class);
        finish();
        startActivity(i);
    }
}