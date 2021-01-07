package be.henallux.smartclass.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.henallux.smartclass.R;
import be.henallux.smartclass.services.Utils;
import be.henallux.smartclass.model.Test;

public class UnsignedTestAdapter extends RecyclerView.Adapter<UnsignedTestAdapter.ViewHolderTest> {

    private ArrayList<Test> tests;
    private OnTestListener onTestListener;

    public UnsignedTestAdapter(OnTestListener onTestListener){
        this.onTestListener = onTestListener;
    }

    @NonNull
    @Override
    public ViewHolderTest onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_test, parent, false);
        return new ViewHolderTest(view, onTestListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UnsignedTestAdapter.ViewHolderTest holder, int position) {
        Test test = tests.get(position);
        holder.textViewSubject.setText(test.getSchoolSubject());
        holder.textViewTitle.setText(test.getTitle());
        String result = Utils.formattedOneDecimalWithoutZero(test.getResult()) + "/" + Utils.formattedOneDecimalWithoutZero(test.getMaxValue());
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

    public void setOnTestListener(OnTestListener onTestListener) {
        this.onTestListener = onTestListener;
    }

    public class ViewHolderTest extends RecyclerView.ViewHolder {
        TextView textViewSubject;
        TextView textViewTitle;
        TextView textViewResult;
        TextView textViewNote;
        Button signedButton;
        public ViewHolderTest(@NonNull View itemView, final OnTestListener onTestListener) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewSubject = itemView.findViewById(R.id.subjectTest);
            textViewResult = itemView.findViewById(R.id.value);
            textViewNote = itemView.findViewById(R.id.note);
            signedButton = itemView.findViewById(R.id.check);

            signedButton.setOnClickListener(view -> {
                if(onTestListener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        onTestListener.onTestClick(position);
                    }
                }
            });
        }
    }

    public interface OnTestListener{
        void onTestClick(int position);
    }
}
