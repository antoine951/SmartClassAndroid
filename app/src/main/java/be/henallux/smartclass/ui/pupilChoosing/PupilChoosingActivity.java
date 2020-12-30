package be.henallux.smartclass.ui.pupilChoosing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import be.henallux.smartclass.R;
import be.henallux.smartclass.adapter.PupilAdapter;
import be.henallux.smartclass.ui.MainActivity;
import be.henallux.smartclass.ui.addChild.AddChildActivity;
import be.henallux.smartclass.ui.test.TestViewModel;
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
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        SaveSharedPreference.setLoggedIn(getApplication(),false);
        finish();
    }

}