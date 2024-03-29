package be.henallux.smartclass.ui.test;

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
import be.henallux.smartclass.adapter.UnsignedTestAdapter;
import be.henallux.smartclass.model.Test;


public class TestFragment extends Fragment implements UnsignedTestAdapter.OnTestListener {
    private ArrayList<Test> testsWithButton;
    private TestViewModel testViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        View root = inflater.inflate(R.layout.fragment_test, container, false);

        final RecyclerView recyclerViewTest = root.findViewById(R.id.unsigned_test_list);

        testViewModel.getMessage().observe(getViewLifecycleOwner(), message->{
            if(message!=null){
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        UnsignedTestAdapter unsignedTestAdapter = new UnsignedTestAdapter(this);
        testViewModel.getUnsignedTestList().observe(getViewLifecycleOwner(), tests->{
            unsignedTestAdapter.setTests(tests);
            testsWithButton=tests;
        });
        recyclerViewTest.setAdapter(unsignedTestAdapter);
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onTestClick(int position) {
        testViewModel.sign(testsWithButton.get(position).getIdTest());
    }

}