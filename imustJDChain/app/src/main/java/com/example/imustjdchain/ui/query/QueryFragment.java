package com.example.imustjdchain.ui.query;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.fastjson.support.spring.JSONPResponseBodyAdvice;
//import com.example.imustjdchain.LoginActivity;
import com.example.imustjdchain.Querresult_diversityActivity;
import com.example.imustjdchain.Queryresults_currencyActivity;
import com.example.imustjdchain.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SplittableRandom;

import static android.content.ContentValues.TAG;
import static android.widget.Toast.LENGTH_SHORT;

public class QueryFragment extends Fragment {

/*
    private QueryViewModel queryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        queryViewModel =
                new ViewModelProvider(this).get(QueryViewModel.class);
        View que = inflater.inflate(R.layout.fragment_query, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
        final Button buttom = que.findViewById(R.id.bt_queryall);
//        queryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return que;
    }*/


    private View view, view2;
    private Button btn;
    //    private TextView tv_mes;
    private EditText query_ledHash;
    private EditText query_accHsah;
    private EditText query_accLID;
    private EditText query_valueAID;
    static String getmesurl;


    private List<String> data_list = new ArrayList<String>();//下拉列表展示的数据
    private ArrayAdapter<String> arr_adapter;//spanner的适配器

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        view = inflater.inflate(R.layout.fragment_query, null);
        btn = (Button) view.findViewById(R.id.bt_queryall);
//        tv_mes = (TextView) view.findViewById(R.id.allmes);
        query_ledHash = (EditText) view.findViewById(R.id.ed_query_ledHash);
        query_accHsah = (EditText) view.findViewById(R.id.ed_query_accHsah);
        query_accLID = (EditText) view.findViewById(R.id.ed_query_accLID);
        query_valueAID = (EditText) view.findViewById(R.id.ed_query_valueAID);
//        tv_mes.setMovementMethod(ScrollingMovementMethod.getInstance());
//        tv_mes.setScrollbarFadingEnabled(false);
        //return view2=inflater.inflate(R.layout.fragment1, null);//错误的写法
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        btn.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
//获取当前时间
                    Date date = new Date(System.currentTimeMillis());
//                    time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
                    Log.d("time", simpleDateFormat.format(date));//("Date获取当前日期时间"+simpleDateFormat.format(date));
                    // TODO Auto-generated method stub
//                    String GetMesUrl = "https://hzy.graduationproject.xyz/GetMessage?";
                    String queledhash = query_ledHash.getText().toString();
                    String queacchsah = query_accHsah.getText().toString();
                    String queaccLID = query_accLID.getText().toString();
                    String quevalAID = query_valueAID.getText().toString();

//                    String GetMesUrl = "http://10.144.12.60:35080/imust-gateway/GetMessage?ledgerHash=j5gX3EuYC8HyyMMpSJUh82ciMdP3nxmcUimazrJ5sJL1rA&accountLID=1";

                    String baseurl = "https://hzy.graduationproject.xyz/GetMessage";
                    String getdataaccinfo = "?ledgerHash=" + queledhash + "&accountLID=" + queaccLID;
                    String getallledgerinfo = baseurl;
                    String getkVofdataacc = "?ledgerHash=" + queledhash + "&accountHash=" + queacchsah + "&valueAID=" + quevalAID;


                    if (queledhash.length() == 0 && queacchsah.length() == 0 && queaccLID.length() == 0 && quevalAID.length() == 0) {
                        getmesurl = getallledgerinfo;
                    }
                    if (queledhash.length() != 0 && queacchsah.length() == 0 && queaccLID.length() != 0 && quevalAID.length() == 0) {
                        getmesurl = baseurl + getdataaccinfo;
                    }
                    if (queledhash.length() != 0 && queacchsah.length() != 0 && queaccLID.length() == 0 && quevalAID.length() != 0) {
                        getmesurl = baseurl + getkVofdataacc;
                    }

//                    System.out.println(getmesurl);
                    // 生成请求对象
            /*HttpGet httpGet = new HttpGet(GetMesUrl);
            HttpClient httpClient = new DefaultHttpClient();

            // 发送请求
            try
            {

                HttpResponse response = httpClient.execute(httpGet);

                // 显示响应
                showResponseResult(response);// 一个私有方法，将响应结果显示出来

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }*/
                    try {
                        URL url = new URL(getmesurl);
//                        System.out.println(getmesurl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();//开启连接
                        connection.connect();
                        //连接服务器
/*                        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                System.out.println("选中的颜色是："+color[position]);
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        */
/*                        data_list.add("请选择功能");
                        data_list.add("GetMessage");
                        data_list.add("MessageCheck");

//适配器
                        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1,data_list);
//设置样式
                        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//加载适配器
                        sp.setAdapter(arr_adapter);


                        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            //有选择值时，要进行的操作
                            public void onItemSelected(AdapterView<?> parent, View view,
                                                       int position, long id) {
                                //获取选中的数据
                                String str = data_list.get(position);

                                //传递到前端TextView中显示
                                System.out.println(str);
                            }

                            @Override
                            //没有选择值时，要进行的操作
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });*/
                        if (connection.getResponseCode() == 200) {
                            //使用字符流形式进行回复
                            InputStream is = connection.getInputStream();
                            //读取信息BufferReader
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                            StringBuffer buffer = new StringBuffer();
                            String readLine = "";
                            while ((readLine = reader.readLine()) != null) {
                                buffer.append(readLine);
                            }
                            is.close();
                            reader.close();
                            connection.disconnect();
//                            Log.d("Msg", buffer.toString());
                            String result_json = buffer.toString();
                            String res_tem = "";
                            String res_out = "";
                            try {
                                // 整个最大的JSON数组
                                JSONArray jsonArray = new JSONArray(result_json);
//                                Log.d(TAG, "jsonArray:" + jsonArray);
                                // [{"name":"君君","age":89,"sex":"男"},{"name":"小君","age":99,"sex":"女"},{"name":"大君","age":88,"sex":"男"}]
                                // JSON数组里面的具体-JSON对象
                                if (queledhash.length() == 0 && queacchsah.length() == 0 && queaccLID.length() == 0 && quevalAID.length() == 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String ledger_name = jsonObject.optString("ledger_name", null);
                                        String ledger_hash = jsonObject.optString("ledger_hash", null);
                                        String id = jsonObject.optString("id", null);
                                        String state = jsonObject.optString("state", null);
                                        // 日志打印结果：
//                                  Log.d(TAG, "解析的结果：\n创建时间：" + account_create_time + "\n账户哈希：" + account_hash + "\n区块高度：" + block_height + "\n账户名称： " + account_name + "\n账户ID："+id + "\n当前状态:"+state);
                                        res_tem = "第" + (i + 1) + "条数据：\n" + "账本名称：" + ledger_name + "\n账本哈希：" + ledger_hash + "\n账本ID：" + id + "\n账户状态：" + state + "\n\n-------可爱分割线---------\n\n";
                                        res_out = res_out + res_tem;
                                    }

                                    Intent intent = new Intent(getActivity(), Queryresults_currencyActivity.class);//从SpendingActivity页面跳转至ExpenseProcesActivity页面
                                    intent.putExtra("res_title", "获取所有账本信息");//参数：name、value
                                    int text_size = 40;
                                    //参数：name、value
                                    intent.putExtra("res", res_out);//参数：name、value
//                            String name = "马宗阳";
                                    QueryFragment.this.startActivity(intent);
                                }
                                if (queledhash.length() != 0 && queacchsah.length() == 0 && queaccLID.length() != 0 && quevalAID.length() == 0) {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String create_time = jsonObject.optString("account_create_time", null);
                                        String account_hash = jsonObject.optString("account_hash", null);
                                        String block_height = jsonObject.optString("block_height", null);
                                        String account_name = jsonObject.optString("account_name", null);
                                        String id = jsonObject.optString("id", null);
                                        String state = jsonObject.optString("state", null);
                                        long time1 = Long.valueOf(create_time).longValue();
                                        String account_create_time;
                                        account_create_time = stampToDate(time1);
//                                  日志打印结果：
//                                      Log.d(TAG, "解析的结果：\n创建时间：" + account_create_time + "\n账户哈希：" + account_hash + "\n区块高度：" + block_height + "\n账户名称：" + account_name + "\n账户ID：" + id + "\n当前状态:" + state);
                                        res_tem = "第" + (i + 1) + "条数据：\n" + "创建时间：" + account_create_time + "\n账户哈希：" + account_hash + "\n区块高度：" + block_height + "\n账户名称：" + account_name + "\n账户ID：" + id + "\n当前状态:" + state + "\n\n-------可爱分割线---------\n\n";
                                        res_out = res_out + res_tem;
//                                        System.out.println(res_tem);
//                                        System.out.println(res_out);
                                    }

                                    Intent intent = new Intent(getActivity(), Queryresults_currencyActivity.class);//从SpendingActivity页面跳转至ExpenseProcesActivity页面
                                    intent.putExtra("res_title", "获取该账本的数据账户信息");//参数：name、value
//                                    String res_title = "获取所有账本信息";
                                    intent.putExtra("res", res_out);//参数：name、value
//                            String name = "马宗阳";
                                    QueryFragment.this.startActivity(intent);
                                }


                                String weight = "";
                                String height = "";
                                String time_stamp = "";
                                int count_x = 0;
                                int count_y = 0;
                                int count_z = 0;

                                if (queledhash.length() != 0 && queacchsah.length() != 0 && queaccLID.length() == 0 && quevalAID.length() != 0) {
                                    String time_stamp_temp = "";
                                    String weight_temp = "";
                                    String height_temp = "";
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                                        for (int j = 0; j < jsonArray1.length(); j++) {
                                            JSONObject jsonObject = jsonArray1.getJSONObject(j);
                                            String create_time = jsonObject.optString("create_time", null);
                                            String block_height = jsonObject.optString("block_height", null);
                                            String type = jsonObject.optString("type", null);
                                            String version = jsonObject.optString("version", null);
                                            String value_key = jsonObject.optString("value_key", null);
                                            String id = jsonObject.optString("id", null);
                                            String state = jsonObject.optString("state", null);
                                            String value = jsonObject.optString("value", null);
                                            long time1 = Long.valueOf(create_time).longValue();
                                            String account_create_time;
                                            account_create_time = stampToDate(time1);
//                                  日志打印结果：
                                            if (i == 2) {
                                                count_x++;
                                                weight_temp = "第" + (count_x) + "条数据：\n" + "创建时间：" + account_create_time + "\n区块高度：" + block_height + "\n数据类型：" + type + "\n版本：" + version + "\n特征名称：" + value_key + "\n特征ID" + "\n当前状态:" + state + "\n特征值：" + value + "\n\n-------可爱分割线---------\n\n";
                                            }

                                            if (i == 1) {
                                                count_y++;
                                                height_temp = "第" + (count_y) + "条数据：\n" + "创建时间：" + account_create_time + "\n区块高度：" + block_height + "\n数据类型：" + type + "\n版本：" + version + "\n特征名称：" + value_key + "\n特征ID" + "\n当前状态:" + state + "\n特征值：" + value + "\n\n-------可爱分割线---------\n\n";
                                            }
                                            if (i == 0) {
                                                count_z++;
                                                time_stamp_temp = "第" + (count_z) + "条数据：\n" + "创建时间：" + account_create_time + "\n区块高度：" + block_height + "\n数据类型：" + type + "\n版本：" + version + "\n特征名称：" + value_key + "\n特征ID" + "\n当前状态:" + state + "\n特征值：" + value + "\n\n-------可爱分割线---------\n\n";
                                            }
//                                            Log.d(TAG, "解析的结果：\n创建时间：" + account_create_time + "\n区块高度：" + block_height + "\n数据类型：" + type + "\n版本：" + version + "\n特征名称：" + value_key + "\n特征ID" + id + "\n当前状态:" + state + "\n特征值：" + value);
                                            res_tem = "第" + (j + 1) + "条数据：\n" + "创建时间：" + account_create_time + "\n区块高度：" + block_height + "\n数据类型：" + type + "\n版本：" + version + "\n特征名称：" + value_key + "\n特征ID：" + id + "\n当前状态:" + state + "\n特征值：" + value + "\n\n-------可爱分割线---------\n\n";
                                            res_out = res_out + res_tem;
                                            weight = weight + weight_temp;
                                            height = height + height_temp;
                                            time_stamp = time_stamp + time_stamp_temp;
                                        }
                                    }
                                    Intent intent = new Intent(getActivity(), Querresult_diversityActivity.class);//从SpendingActivity页面跳转至ExpenseProcesActivity页面
                                    intent.putExtra("res_all", res_out);//参数：name、value
                                    intent.putExtra("res_weight", weight);//参数：name、value
                                    intent.putExtra("res_height", height);//参数：name、value
                                    intent.putExtra("res_time_stamp", time_stamp);//参数：name、value
                                    QueryFragment.this.startActivity(intent);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Looper.prepare();
                            Toast.makeText(getActivity(), "loading...", LENGTH_SHORT).show();
//                            tv_mes.setText("查询时间：" + simpleDateFormat.format(date) + "\n---------------\n" + res_out);
                            Toast.makeText(getActivity(), "success", LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Log.d("TAG", "ERROR CONNECTED");
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    Toast.makeText(getActivity(), "success2", LENGTH_SHORT).show();
                }

                public String stampToDate(long timeMillis) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(timeMillis);
                    return simpleDateFormat.format(date);
                }
            }).start();
        });
    }


    private static String getType(Object a) {
        return a.getClass().toString();

    }

    /*
    private String streamToString(InputStream inputStream, String charset) {
        try {
            //输入流
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
            //得到缓冲流
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String s = null;
            StringBuilder builder = new StringBuilder();
            while ((s = reader.readLine()) != null) {
                builder.append(s);
            }
            reader.close();
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
*/
}