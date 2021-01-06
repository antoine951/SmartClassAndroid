package be.henallux.smartclass.ui.pupilChoosing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import be.henallux.smartclass.R;
import be.henallux.smartclass.ui.addChild.AddChildActivity;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;

public class PupilChoosingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pupil_choosing);

        final Button addChildButton = findViewById(R.id.addChild);
        addChildButton.setOnClickListener(this::navigateToAddChild);
    }


    private void navigateToAddChild(View view) {
        Intent intent = new Intent(getApplicationContext(), AddChildActivity.class);
        finish();

        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        SaveSharedPreference.setLoggedIn(getApplication(),false);
        finish();
    }

}