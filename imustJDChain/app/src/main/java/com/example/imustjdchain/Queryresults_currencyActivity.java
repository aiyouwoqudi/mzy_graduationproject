package com.example.imustjdchain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Author: MaZongyang
 * Date: 2021/5/14/0014 12:14
 * Description:
 */
public class Queryresults_currencyActivity extends Activity {
    private TextView tv_res_in;
    private TextView tv_qrc_in;
    private EditText ed_res_in;
    private String res_title_in;
    private String res_in;
    private int text_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querresult_currency);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        tv_qrc_in = (TextView) findViewById(R.id.tv_res);
        ed_res_in = (EditText) findViewById(R.id.ed_res_in);
        ed_res_in.setKeyListener(null);
        Intent intent = getIntent();
        res_title_in = intent.getStringExtra("res_title");
        res_in =intent.getStringExtra("res");
        text_size = 360/res_title_in.length();
//        if (res_title_in == "获取所有账本信息"){
//            text_size = 40;
//        }

        tv_qrc_in.setText(res_title_in);
        ed_res_in.setText(res_in);
        tv_qrc_in.setTextSize(text_size);


    }
}
