package be.henallux.smartclass.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.model.Result;

public class ReportCardAdapter extends RecyclerView.Adapter<ReportCardAdapter.ViewHolderReportCard> {

    private ArrayList<Result> Results;

    @NonNull
    @Override
    public ReportCardAdapter.ViewHolderReportCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_report, parent, false);
        return new ReportCardAdapter.ViewHolderReportCard(view);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ReportCardAdapter.ViewHolderReportCard holder, int position) {
        Result Result = Results.get(position);
        holder.textViewSubject.setText(Result.getCategory());
        holder.textViewScore.setText(Result.getAverage());
    }

    @Override
    public int getItemCount() {
        return (Results == null) ? 0 : Results.size();
    }

    public void setSubjectScores(ArrayList<Result> Results) {
        this.Results = Results;
        notifyDataSetChanged();
    }

    public class ViewHolderReportCard extends RecyclerView.ViewHolder {
        TextView textViewSubject;
        TextView textViewScore;

        public ViewHolderReportCard(@NonNull View itemView) {
            super(itemView);
            textViewSubject = itemView.findViewById(R.id.subject);
            textViewScore = itemView.findViewById(R.id.mean);
        }
    }
}
