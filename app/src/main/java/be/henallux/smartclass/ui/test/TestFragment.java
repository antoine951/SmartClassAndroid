package be.henallux.smartclass.ui.test;

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
import be.henallux.smartclass.adapter.UnsignedTestAdapter;


public class TestFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TestViewModel testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        View root = inflater.inflate(R.layout.fragment_test, container, false);

        final RecyclerView recyclerViewTest = root.findViewById(R.id.unsigned_test_list);

        UnsignedTestAdapter unsignedTestAdapter = new UnsignedTestAdapter();
        testViewModel.getUnsignedTestList().observe(getViewLifecycleOwner(), unsignedTestAdapter::setTests);
        recyclerViewTest.setAdapter(unsignedTestAdapter);
        recyclerViewTest.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

}