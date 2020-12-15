package be.henallux.smartclass.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.Pupil;

public class PupilAdapter extends RecyclerView.Adapter<PupilAdapter.ViewHolderPupil> {

    private ArrayList<Pupil> children;
    private OnItemSelectedListener onItemSelectedListener;

    public PupilAdapter(OnItemSelectedListener onItemSelectedListener){
        this.onItemSelectedListener = onItemSelectedListener;
    }

    @NonNull
    @Override
    public PupilAdapter.ViewHolderPupil onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_child, parent, false);
        return new ViewHolderPupil(view,onItemSelectedListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PupilAdapter.ViewHolderPupil holder, int position) {
        Pupil child = children.get(position);
        holder.textViewName.setText(child.getFirstname()+" "+child.getLastname());
    }

    @Override
    public int getItemCount() {
        return (children == null) ? 0 : children.size();
    }

    public void setChildren(ArrayList<Pupil> children) {
        this.children = children;
        notifyDataSetChanged();
    }

    public class ViewHolderPupil extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView textViewName;
        OnItemSelectedListener onItemSelectedListener;
        public ViewHolderPupil(@NonNull View itemView, OnItemSelectedListener onItemSelectedListener) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.fullname);
            this.onItemSelectedListener=onItemSelectedListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemSelectedListener.onItemSelected(getAdapterPosition());
        }
    }

    public interface OnItemSelectedListener{
        void onItemSelected(int position);
    }
}
