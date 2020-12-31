package be.henallux.smartclass.ui.pupilChoosing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.adapter.PupilAdapter;
import be.henallux.smartclass.model.Pupil;
import be.henallux.smartclass.ui.MainActivity;
import be.henallux.smartclass.utils.sharedPreferences.SaveSharedPreference;


public class ChildFragment extends Fragment implements PupilAdapter.OnItemSelectedListener {

    private ArrayList<Pupil> pupils=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        PupilChoosingViewModel pupilChoosingViewModel = ViewModelProviders.of(this).get(PupilChoosingViewModel.class);

        View root = inflater.inflate(R.layout.fragment_child, container, false);

        final RecyclerView recyclerViewChild = root.findViewById(R.id.child_list);

        pupilChoosingViewModel.getMessage().observe(getViewLifecycleOwner(), message->{
            if(message!=null){
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        PupilAdapter adapter = new PupilAdapter(this);
        pupilChoosingViewModel.getChildren().observe(getViewLifecycleOwner(), children->{
            adapter.setChildren(children);
            pupils=children;
        });
        recyclerViewChild.setAdapter(adapter);
        recyclerViewChild.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onItemSelected(int position) {
        SaveSharedPreference.setCurrentChild(getContext(),pupils.get(position).getToken());
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }
}