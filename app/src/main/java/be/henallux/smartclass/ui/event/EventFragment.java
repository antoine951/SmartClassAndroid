package be.henallux.smartclass.ui.event;

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
import be.henallux.smartclass.adapter.EventAdapter;


public class EventFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        EventViewModel eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);

        View root = inflater.inflate(R.layout.fragment_event, container, false);

        final RecyclerView recyclerViewWeek = root.findViewById(R.id.event_list_week);
        final RecyclerView recyclerViewMonth = root.findViewById(R.id.event_list_month);
        final RecyclerView recyclerViewComing = root.findViewById(R.id.event_list_coming);

        EventAdapter adapterWeek = new EventAdapter();
        eventViewModel.getEventListWeek().observe(getViewLifecycleOwner(), adapterWeek::setEvents);
        recyclerViewWeek.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerViewWeek.setAdapter(adapterWeek);

        EventAdapter adapterMonth = new EventAdapter();
        eventViewModel.getEventListMonth().observe(getViewLifecycleOwner(), adapterMonth::setEvents);
        recyclerViewMonth.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerViewMonth.setAdapter(adapterMonth);

        EventAdapter adapterComing = new EventAdapter();
        eventViewModel.getEventListComing().observe(getViewLifecycleOwner(), adapterComing::setEvents);
        recyclerViewComing.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerViewComing.setAdapter(adapterComing);

        return root;
    }
}