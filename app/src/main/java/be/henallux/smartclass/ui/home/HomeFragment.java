package be.henallux.smartclass.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import be.henallux.smartclass.R;
import be.henallux.smartclass.ui.event.EventViewModel;
import be.henallux.smartclass.ui.reportCard.ReportCardViewModel;
import be.henallux.smartclass.ui.task.TaskViewModel;
import be.henallux.smartclass.ui.test.TestViewModel;

public class HomeFragment extends Fragment {

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TaskViewModel taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        TestViewModel testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        EventViewModel eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        ReportCardViewModel reportCardViewModel = ViewModelProviders.of(this).get(ReportCardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView numberTask = root.findViewById(R.id.numberTask);
        final TextView numberTest = root.findViewById(R.id.numberUnsignedTest);
        final TextView numberEvent = root.findViewById(R.id.numberEvent);

        final TextView frenchMean = root.findViewById(R.id.frenchMean);
        final TextView mathMean = root.findViewById(R.id.mathMean);
        final TextView sciencesMean = root.findViewById(R.id.sciencesMean);
        final TextView otherMean = root.findViewById(R.id.otherMean);

        taskViewModel.getTasksSecondDay().observe(getViewLifecycleOwner(), tasks-> numberTask.setText(Integer.toString(tasks.size())));
        testViewModel.getUnsignedTestList().observe(getViewLifecycleOwner(), tests-> numberTest.setText(Integer.toString(tests.size())));
        eventViewModel.getEventListWeek().observe(getViewLifecycleOwner(), events-> numberEvent.setText(Integer.toString(events.size())));


        reportCardViewModel.getFrenchMean().observe(getViewLifecycleOwner(), frenchMean::setText);
        reportCardViewModel.getMathMean().observe(getViewLifecycleOwner(), mathMean::setText);
        reportCardViewModel.getSciencesMean().observe(getViewLifecycleOwner(), sciencesMean::setText);
        reportCardViewModel.getOtherMean().observe(getViewLifecycleOwner(), otherMean::setText);

        return root;
    }
}