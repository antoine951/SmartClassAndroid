package be.henallux.smartclass.ui.addChild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import be.henallux.smartclass.R;
import be.henallux.smartclass.ui.pupilChoosing.PupilChoosingActivity;

public class AddChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        final Button addChildButton = findViewById(R.id.childAdd);
        addChildButton.setOnClickListener(this::backToChildMenu);
    }

    private void backToChildMenu(View view) {
        finish();
    }
}