package com.example.administrator.myradiobutton;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private MyRadioButton mRadioButton1;
    private MyRadioButton mRadioButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.qeqwe);
        mRadioButton1 = (MyRadioButton) findViewById(R.id.r1);
        mRadioButton2 = (MyRadioButton) findViewById(R.id.r2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.r1:
                        mRadioButton1.isChecked(true);
                        mRadioButton2.isChecked(false);
                        break;
                    case R.id.r2:
                        mRadioButton1.isChecked(false);
                        mRadioButton2.isChecked(true);
                        break;
                }
            }
        });

    }

}
