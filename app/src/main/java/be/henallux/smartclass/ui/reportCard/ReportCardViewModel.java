package be.henallux.smartclass.ui.reportCard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import be.henallux.smartclass.business.ReportCardBusiness;
import be.henallux.smartclass.formater.Utils;
import be.henallux.smartclass.model.SubjectScore;

public class ReportCardViewModel extends ViewModel {

    private MutableLiveData<String> mFrenchMean;
    private MutableLiveData<String> mMathMean;
    private MutableLiveData<String> mSciencesMean;
    private MutableLiveData<String> mOtherMean;

    private MutableLiveData<ArrayList<SubjectScore>> mFrench;
    private MutableLiveData<ArrayList<SubjectScore>> mMath;
    private MutableLiveData<ArrayList<SubjectScore>> mSciences;
    private MutableLiveData<ArrayList<SubjectScore>> mOther;

    public ReportCardViewModel() {
        ReportCardBusiness reportCardBusiness = new ReportCardBusiness();

        mFrenchMean = new MutableLiveData<>();
        mFrenchMean.setValue((reportCardBusiness.getFrenchMean() > -1) ? Utils.formattedPercent(reportCardBusiness.getFrenchMean()) : "");

        mMathMean = new MutableLiveData<>();
        mMathMean.setValue((reportCardBusiness.getMathMean() > -1) ? Utils.formattedPercent(reportCardBusiness.getMathMean()) : "");

        mSciencesMean = new MutableLiveData<>();
        mSciencesMean.setValue((reportCardBusiness.getSciencesMean() > -1) ? Utils.formattedPercent(reportCardBusiness.getSciencesMean()) : "");

        mOtherMean = new MutableLiveData<>();
        mOtherMean.setValue((reportCardBusiness.getOtherMean() > -1) ? Utils.formattedPercent(reportCardBusiness.getOtherMean()) : "");


        mFrench = new MutableLiveData<>();
        mFrench.setValue(reportCardBusiness.getFrench());

        mMath = new MutableLiveData<>();
        mMath.setValue(reportCardBusiness.getMath());

        mSciences = new MutableLiveData<>();
        mSciences.setValue(reportCardBusiness.getSciences());

        mOther = new MutableLiveData<>();
        mOther.setValue(reportCardBusiness.getOther());


    }

    public LiveData<String> getFrenchMean() {
        return mFrenchMean;
    }

    public LiveData<String> getMathMean() {
        return mMathMean;
    }

    public LiveData<String> getSciencesMean() {
        return mSciencesMean;
    }

    public LiveData<String> getOtherMean() {
        return mOtherMean;
    }

    public LiveData<ArrayList<SubjectScore>> getFrench() {
        return mFrench;
    }

    public LiveData<ArrayList<SubjectScore>> getMath() {
        return mMath;
    }

    public LiveData<ArrayList<SubjectScore>> getSciences() {
        return mSciences;
    }

    public LiveData<ArrayList<SubjectScore>> getOther() {
        return mOther;
    }
}