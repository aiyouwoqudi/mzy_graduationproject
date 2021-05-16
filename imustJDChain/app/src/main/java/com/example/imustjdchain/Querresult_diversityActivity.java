package com.example.imustjdchain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.imustjdchain.ui.query.QueryFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * Author: MaZongyang
 * Date: 2021/5/14/0014 14:59
 * Description:
 */
public class Querresult_diversityActivity extends Activity {
    private TextView tv_res_title_in;
    private EditText ed_res_ins;
    private String res_in_all;
    private String res_in_weight;
    private String res_in_height;
    private String res_in_time_stamp;
    private Button bt_sg_show;
    private Button bt_tz_show;
    private Button bt_sjc_show;
    private int text_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querresult_diversity);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //标题展示框
        tv_res_title_in = (TextView) findViewById(R.id.tv_ress);
        //结果输出框
        ed_res_ins = (EditText) findViewById(R.id.ed_res_ins);
        // 三个按钮
        bt_sg_show = findViewById(R.id.bt_sg);
        bt_tz_show = findViewById(R.id.bt_tz);
        bt_sjc_show = findViewById(R.id.bt_sjc);
        //结果输出框禁止键盘
        ed_res_ins.setKeyListener(null);
        Intent intent = getIntent();
        res_in_all = intent.getStringExtra("res_all");
        res_in_weight = intent.getStringExtra("res_weight");
        res_in_height = intent.getStringExtra("res_height");
        res_in_time_stamp = intent.getStringExtra("res_time_stamp");
        ed_res_ins.setText(res_in_all);
//        /**
//         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
//         */
//        public static int px2dip (Context context,float pxValue){
//            final float scale = context.getResources().getDisplayMetrics().density;
//            return (int) (pxValue / scale + 0.5f);
//        }
        bt_sg_show.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //方法

                Intent intent1 = getIntent();
                String res_in_height1 = intent1.getStringExtra("res_height");
                ed_res_ins.setText(res_in_weight);
                tv_res_title_in.setText("获取该数据账户的kv-身高");
                text_size = 360 / tv_res_title_in.length();
                tv_res_title_in.setTextSize(text_size);
            }
        });
        bt_tz_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_res_ins.setText(res_in_time_stamp);
                tv_res_title_in.setText("获取该数据账户的kv-体重");
                text_size = 360 / tv_res_title_in.length();
                tv_res_title_in.setTextSize(text_size);
            }
        });
        bt_sjc_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_res_ins.setText(res_in_height);
                tv_res_title_in.setText("获取该数据账户的kv-create_time");
                text_size = 360 / tv_res_title_in.length();
                tv_res_title_in.setTextSize(text_size);
            }
        });

    }

}
