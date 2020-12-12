package be.henallux.smartclass.ui.pupilChoosing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import be.henallux.smartclass.R;
import be.henallux.smartclass.adapter.PupilAdapter;


public class ChildFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        PupilChoosingViewModel pupilChoosingViewModel = ViewModelProviders.of(this).get(PupilChoosingViewModel.class);

        View root = inflater.inflate(R.layout.fragment_child, container, false);

        final RecyclerView recyclerViewChild = root.findViewById(R.id.child_list);

        PupilAdapter adapter = new PupilAdapter();
        pupilChoosingViewModel.getChildren().observe(getViewLifecycleOwner(), adapter::setChildren);
        recyclerViewChild.setAdapter(adapter);
        recyclerViewChild.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

}