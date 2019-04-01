package com.example.mvp.base;

public interface BasePresenter <T extends BaseView> {

    void onAttach(T view);

    void onDetach();
}
