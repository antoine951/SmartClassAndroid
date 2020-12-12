package be.henallux.smartclass.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.formater.Utils;
import be.henallux.smartclass.model.Test;

public class UnsignedTestAdapter extends RecyclerView.Adapter<UnsignedTestAdapter.ViewHolderTest> {

    private ArrayList<Test> tests;

    @NonNull
    @Override
    public ViewHolderTest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_test, parent, false);
        return new ViewHolderTest(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnsignedTestAdapter.ViewHolderTest holder, int position) {
        Test test = tests.get(position);
        holder.textViewSubject.setText(test.getSubjectName());
        holder.textViewTitle.setText(test.getTitle());
        String result = Utils.formattedOneDecimalWithoutZero(test.getValue()) + "/" + Utils.formattedOneDecimalWithoutZero(test.getMaxValue());
        holder.textViewResult.setText(result);
        holder.textViewNote.setText(test.getNote());
    }

    @Override
    public int getItemCount() {
        return (tests == null) ? 0 : tests.size();
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
        notifyDataSetChanged();
    }

    public class ViewHolderTest extends RecyclerView.ViewHolder {
        TextView textViewSubject;
        TextView textViewTitle;
        TextView textViewResult;
        TextView textViewNote;

        public ViewHolderTest(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewSubject = itemView.findViewById(R.id.subjectTest);
            textViewResult = itemView.findViewById(R.id.value);
            textViewNote = itemView.findViewById(R.id.note);
        }
    }
}
