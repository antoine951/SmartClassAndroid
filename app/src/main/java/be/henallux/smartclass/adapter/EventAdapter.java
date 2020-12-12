package be.henallux.smartclass.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.Event;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolderEvent> {

    private ArrayList<Event> events;

    @NonNull
    @Override
    public EventAdapter.ViewHolderEvent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, parent, false);
        return new ViewHolderEvent(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEvent holder, int position) {
        Event event = events.get(position);
        holder.textViewName.setText(event.getEventName());
        holder.textViewDate.setText(event.getEventDateFormated());
        holder.textViewDescription.setText(event.getEventDescription());
    }

    @Override
    public int getItemCount() {
        return (events == null) ? 0 : events.size();
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public class ViewHolderEvent extends RecyclerView.ViewHolder {
        TextView textViewDate;
        TextView textViewName;
        TextView textViewDescription;

        public ViewHolderEvent(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.dateEvent);
            textViewName = itemView.findViewById(R.id.nameEvent);
            textViewDescription = itemView.findViewById(R.id.descriptionEvent);
        }
    }


}
