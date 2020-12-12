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
import be.henallux.smartclass.formater.Utils;
import be.henallux.smartclass.model.SubjectScore;

public class ReportCardAdapter extends RecyclerView.Adapter<ReportCardAdapter.ViewHolderReportCard> {

    private ArrayList<SubjectScore> subjectScores;

    @NonNull
    @Override
    public ReportCardAdapter.ViewHolderReportCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_report, parent, false);
        return new ReportCardAdapter.ViewHolderReportCard(view);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ReportCardAdapter.ViewHolderReportCard holder, int position) {
        SubjectScore subjectScore = subjectScores.get(position);
        holder.textViewSubject.setText(subjectScore.getSubject());
        holder.textViewScore.setText(Utils.formattedPercent(subjectScore.getResult()));
    }

    @Override
    public int getItemCount() {
        return (subjectScores == null) ? 0 : subjectScores.size();
    }

    public void setSubjectScores(ArrayList<SubjectScore> subjectScores) {
        this.subjectScores = subjectScores;
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
