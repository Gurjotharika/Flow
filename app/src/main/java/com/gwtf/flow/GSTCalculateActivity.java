package com.gwtf.flow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GSTCalculateActivity extends AppCompatActivity {

    private Button _btnOne;
    private Button _btnTwo;
    private Button _btnThree;
    private Button _btnFour;
    private Button _btnFive;
    private Button _btnSix;
    private Button _btnSeven;
    private Button _btnEight;
    private Button _btnNine;
    private Button _btnZero;
    private Button _btnPoint;
    private Button _btnClear;
    private TextView _finalPrice;
    private TextView _beforeTaxPrice;

    private double _finalValue;
    private TextView _tvFinalPrice;
    private TextView _tvBeforeTax;
    private static int _count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_gstcalculate);


    }
}