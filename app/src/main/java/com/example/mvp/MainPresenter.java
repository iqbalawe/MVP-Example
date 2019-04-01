package com.example.mvp;

import com.example.mvp.base.BasePresenter;
import com.example.mvp.main.MainView;
import com.example.mvp.model.ResultModel;

public class MainPresenter implements BasePresenter<MainView> {

    MainView mainView;

    @Override
    public void onAttach(MainView view) {
        mainView = view;
    }

    // create method / function for calculating
    public void calculate(String inputanA, String inputanB) {
        if (inputanA.isEmpty() || inputanB.isEmpty()) {
            mainView.Error();

        } else {

            double valueA = Double.parseDouble(inputanA);
            double valueB = Double.parseDouble(inputanB);
            double result = valueA * valueB;
            ResultModel dataModel = new ResultModel(String.valueOf(result));
            mainView.Success(dataModel);
        }
    }

    @Override
    public void onDetach() {
        mainView = null;
    }
}
