package be.henallux.smartclass.ui.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import be.henallux.smartclass.R;
import be.henallux.smartclass.adapter.TaskAdapter;

public class TaskFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TaskViewModel taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        View root = inflater.inflate(R.layout.fragment_task, container, false);

        final TextView dateFirst = root.findViewById(R.id.firstDateTxt);
        final TextView dateSecond = root.findViewById(R.id.secondDateTxt);
        final TextView dateThird = root.findViewById(R.id.thirdDateTxt);
        final TextView dateForth = root.findViewById(R.id.forthDateTxt);
        final TextView dateFifth = root.findViewById(R.id.fifthDateTxt);
        final TextView dateSixth = root.findViewById(R.id.sixthDateTxt);
        final TextView dateSeventh = root.findViewById(R.id.seventhDateTxt);

        final RecyclerView recyclerViewTaskFirst = root.findViewById(R.id.list_task_first);
        final RecyclerView recyclerViewTaskSecond = root.findViewById(R.id.list_task_second);
        final RecyclerView recyclerViewTaskThird = root.findViewById(R.id.list_task_third);
        final RecyclerView recyclerViewTaskForth = root.findViewById(R.id.list_task_forth);
        final RecyclerView recyclerViewTaskFifth = root.findViewById(R.id.list_task_fifth);
        final RecyclerView recyclerViewTaskSixth = root.findViewById(R.id.list_task_sixth);
        final RecyclerView recyclerViewTaskSeventh = root.findViewById(R.id.list_task_seventh);


        taskViewModel.getFirstDay().observe(getViewLifecycleOwner(), dateFirst::setText);
        TaskAdapter taskAdapter1 = new TaskAdapter(this.getContext());
        taskViewModel.getTasksFirstDay().observe(getViewLifecycleOwner(), taskAdapter1::setTasks);
        recyclerViewTaskFirst.setAdapter(taskAdapter1);
        recyclerViewTaskFirst.setLayoutManager(new LinearLayoutManager(getContext()));


        taskViewModel.getSecondDay().observe(getViewLifecycleOwner(), dateSecond::setText);
        TaskAdapter taskAdapter2 = new TaskAdapter(this.getContext());
        taskViewModel.getTasksSecondDay().observe(getViewLifecycleOwner(), taskAdapter2::setTasks);
        recyclerViewTaskSecond.setAdapter(taskAdapter2);
        recyclerViewTaskSecond.setLayoutManager(new LinearLayoutManager(getContext()));

        taskViewModel.getThirdDay().observe(getViewLifecycleOwner(), dateThird::setText);
        TaskAdapter taskAdapter3 = new TaskAdapter(getContext());
        taskViewModel.getTasksThirdDay().observe(getViewLifecycleOwner(), taskAdapter3::setTasks);
        recyclerViewTaskThird.setAdapter(taskAdapter3);
        recyclerViewTaskThird.setLayoutManager(new LinearLayoutManager(getContext()));

        taskViewModel.getForthDay().observe(getViewLifecycleOwner(), dateForth::setText);
        TaskAdapter taskAdapter4 = new TaskAdapter(getContext());
        taskViewModel.getTasksForthDay().observe(getViewLifecycleOwner(), taskAdapter4::setTasks);
        recyclerViewTaskForth.setAdapter(taskAdapter4);
        recyclerViewTaskForth.setLayoutManager(new LinearLayoutManager(getContext()));

        taskViewModel.getFifthDay().observe(getViewLifecycleOwner(), dateFifth::setText);
        TaskAdapter taskAdapter5 = new TaskAdapter(getContext());
        taskViewModel.getTasksFifthDay().observe(getViewLifecycleOwner(), taskAdapter5::setTasks);
        recyclerViewTaskFifth.setAdapter(taskAdapter5);
        recyclerViewTaskFifth.setLayoutManager(new LinearLayoutManager(getContext()));

        taskViewModel.getSixthDay().observe(getViewLifecycleOwner(), dateSixth::setText);
        TaskAdapter taskAdapter6 = new TaskAdapter(getContext());
        taskViewModel.getTasksSixthDay().observe(getViewLifecycleOwner(), taskAdapter6::setTasks);
        recyclerViewTaskSixth.setAdapter(taskAdapter6);
        recyclerViewTaskSixth.setLayoutManager(new LinearLayoutManager(getContext()));

        taskViewModel.getSeventhDay().observe(getViewLifecycleOwner(), dateSeventh::setText);
        TaskAdapter taskAdapter7 = new TaskAdapter(getContext());
        taskViewModel.getTasksSeventhDay().observe(getViewLifecycleOwner(), taskAdapter7::setTasks);
        recyclerViewTaskSeventh.setAdapter(taskAdapter7);
        recyclerViewTaskSeventh.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

}