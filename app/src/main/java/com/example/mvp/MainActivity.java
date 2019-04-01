package com.example.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp.main.MainView;
import com.example.mvp.model.ResultModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    // variabel untuk menjaga datanya dari perubahan orientasi
    public static final String STATE = "STATE";

    @BindView(R.id.inputan_a)
    EditText inputanA;
    @BindView(R.id.inputan_b)
    EditText inputanB;
    @BindView(R.id.btn_calculate)
    Button btnCalculate;
    @BindView(R.id.tv_result)
    TextView tvResult;

    //Deklarasi presenter
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //inisialisasi
        initPresenter();

        // Kondisi datanya supaya tidak hilang saat orientasinya berubah
        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE);
            tvResult.setText(result);
        }
    }

    // inisialisasi MainPresenter
    private void initPresenter() {
        presenter = new MainPresenter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE, tvResult.getText().toString().trim());
    }

    @OnClick(R.id.btn_calculate)
    public void onViewClicked() {
        String penampungA = inputanA.getText().toString().trim();
        String penampungB = inputanB.getText().toString().trim();
        presenter.calculate(penampungA, penampungB);
    }

    @Override
    public void Error() {
        Toast.makeText(this, "Tidak boleh kosong!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Success(ResultModel dataModel) {
        tvResult.setText(dataModel.getResult());
    }

    @Override
    protected void onStart() {
        onAttachView();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        onDetachView();
        super.onDestroy();
    }

    @Override
    public void onAttachView() {
        presenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }
}
