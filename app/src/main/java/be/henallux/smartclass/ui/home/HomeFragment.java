package be.henallux.smartclass.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import be.henallux.smartclass.R;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView numberTask = root.findViewById(R.id.numberTask);
        final TextView numberTest = root.findViewById(R.id.numberUnsignedTest);
        final TextView numberEvent = root.findViewById(R.id.numberEvent);

        final TextView frenchMean = root.findViewById(R.id.frenchMean);
        final TextView mathMean = root.findViewById(R.id.mathMean);
        final TextView sciencesMean = root.findViewById(R.id.sciencesMean);
        final TextView otherMean = root.findViewById(R.id.otherMean);

        homeViewModel.getTextTask().observe(getViewLifecycleOwner(), numberTask::setText);
        homeViewModel.getTextTest().observe(getViewLifecycleOwner(), numberTest::setText);
        homeViewModel.getTextEvent().observe(getViewLifecycleOwner(), numberEvent::setText);

        homeViewModel.getFrenchMean().observe(getViewLifecycleOwner(), frenchMean::setText);
        homeViewModel.getMathMean().observe(getViewLifecycleOwner(), mathMean::setText);
        homeViewModel.getSciencesMean().observe(getViewLifecycleOwner(), sciencesMean::setText);
        homeViewModel.getOtherMean().observe(getViewLifecycleOwner(), otherMean::setText);

        return root;
    }
}