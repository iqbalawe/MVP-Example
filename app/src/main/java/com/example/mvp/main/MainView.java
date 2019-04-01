package com.example.mvp.main;

import com.example.mvp.base.BaseView;
import com.example.mvp.model.ResultModel;

public interface MainView extends BaseView {

    void Error();

    void Success(ResultModel dataModel);

}
