package be.henallux.smartclass.ui.reportCard;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import be.henallux.smartclass.R;
import be.henallux.smartclass.adapter.ReportCardAdapter;

public class ReportCardFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReportCardViewModel reportCardViewModel = ViewModelProviders.of(this).get(ReportCardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_report_card, container, false);

        final TextView frenchMean = root.findViewById(R.id.frenchMeanTxt);
        final TextView mathMean = root.findViewById(R.id.mathMeanTxt);
        final TextView sciencesMean = root.findViewById(R.id.sciencesMeanTxt);
        final TextView otherMean = root.findViewById(R.id.otherMeanTxt);

        final RecyclerView recyclerViewFrenchSubject = root.findViewById(R.id.frenchSubject);
        final RecyclerView recyclerViewMathSubject = root.findViewById(R.id.mathSubject);
        final RecyclerView recyclerViewSciencesSubject = root.findViewById(R.id.sciencesSubject);
        final RecyclerView recyclerViewOtherSubject = root.findViewById(R.id.otherSubject);

        ReportCardAdapter adapterFrench = new ReportCardAdapter();
        reportCardViewModel.getFrench().observe(getViewLifecycleOwner(), adapterFrench::setSubjectScores);
        recyclerViewFrenchSubject.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewFrenchSubject.setAdapter(adapterFrench);
        reportCardViewModel.getFrenchMean().observe(getViewLifecycleOwner(), frenchMean::setText);

        ReportCardAdapter adapterMath = new ReportCardAdapter();
        reportCardViewModel.getMath().observe(getViewLifecycleOwner(), adapterMath::setSubjectScores);
        recyclerViewMathSubject.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMathSubject.setAdapter(adapterMath);
        reportCardViewModel.getMathMean().observe(getViewLifecycleOwner(), mathMean::setText);

        ReportCardAdapter adapterSciences = new ReportCardAdapter();
        reportCardViewModel.getSciences().observe(getViewLifecycleOwner(), adapterSciences::setSubjectScores);
        recyclerViewSciencesSubject.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSciencesSubject.setAdapter(adapterSciences);
        reportCardViewModel.getSciencesMean().observe(getViewLifecycleOwner(), sciencesMean::setText);

        ReportCardAdapter adapterOther = new ReportCardAdapter();
        reportCardViewModel.getOther().observe(getViewLifecycleOwner(), adapterOther::setSubjectScores);
        recyclerViewOtherSubject.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewOtherSubject.setAdapter(adapterOther);
        reportCardViewModel.getOtherMean().observe(getViewLifecycleOwner(), otherMean::setText);

        return root;
    }

}