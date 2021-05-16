package com.example.imustjdchain;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import android.app.Activity;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

//第二次添加👇
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;
//import java.


public class LoginActivity extends Activity implements View.OnClickListener {
    private Button btnGetTitles = null;
    private ListView lvShow = null;
    private List<Object> titleList = null;
    private EditText et_username;
    private EditText et_password;
    private ImageView imgview;
    static String popup_title;
    static String popup_Message;
    static String popup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        int[] ck = new int[]{};
        ck = screenSize();
        imgview = (ImageView) findViewById(R.id.img_logo);
        setViewSize(imgview, ck);
//        imgview.setWidth(ck[0]);
//        imgview.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        et_username = (EditText) findViewById(R.id.in_user);
        et_password = (EditText) findViewById(R.id.in_pawd);
        initUI();

        btnGetTitles.setOnClickListener(this);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());


    }


    private void initUI() {
        btnGetTitles = (Button) findViewById(R.id.bt_login);
    }


    @Override
    public void onClick(View arg0) {


        new Thread(new GetTitlesThread()).start();
    }

    Handler getTitlesHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(
                        LoginActivity.this,
                        android.R.layout.simple_list_item_1,
                        titleList);
//                lvShow.setAdapter(adapter);
            }
        }
    };

    class GetTitlesThread implements Runnable {
        @Override
        public void run() {
//            new Thread() {
            String user = et_username.getText().toString();
            String pawd = et_password.getText().toString();
//            String url="https://hzy.graduationproject.xyz/imust-gateway/GetMessage";
            if (user.length() == 0 || pawd.length() == 0) {
                Looper.prepare();
                Toast.makeText(LoginActivity.this, "请输入有效信息！\n账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
            String login_url = "https://hzy.graduationproject.xyz/LoginCheck";
//            String login_url = "https://hzy.graduationproject.xyz/imust-gateway/LoginCheck";
            String json = "{\n" +
                    " \"user_id\":" + "\"" + user + "\"" + ",\n" +
                    " \"passwd\":" + "\"" + pawd + "\"" + "\n" +
                    "}";
//            get方法
            /*
            StringBuilder builder=new StringBuilder();
            HttpClient client=new DefaultHttpClient();
            HttpPost get=new HttpPost(url);
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("user_id", "hzy630@126.com"));
            params.add(new BasicNameValuePair("passwd", "admin123123"));
            HttpResponse httpResponse = null;
            try{
                HttpResponse response=client.execute(get);

                if(response.getStatusLine().getStatusCode()==200){
                    BufferedReader reader=new BufferedReader(
                            new InputStreamReader(response.getEntity().getContent()));
                    for(String s=reader.readLine();s!=null;s=reader.readLine()){
                        builder.append(s);
                    }
                    JSONArray json=new JSONArray(builder.toString());
                    int len=json.length();
                    String state="";
                    titleList=new ArrayList<Object>();
                    for(int i=0;i<len;i++){
                        JSONObject temp=(JSONObject)json.get(i);
                        state=temp.getString("state");
                        log_sta = state;
                        System.out.println("\n"+user_id+"\n"+pawd);
                        System.out.println("（"+state+"）");
                    }
                    getTitlesHandler.obtainMessage(100).sendToTarget();
                }
            }catch(Exception e){
                //
            }*/
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(login_url);
            StringEntity postingString = null;// json传递
            try {
                postingString = new StringEntity(json);
                post.setEntity(postingString);
                post.setHeader("Content-type", "application/json");
                HttpResponse response = httpClient.execute(post);
                String content = EntityUtils.toString(response.getEntity());
                System.out.println(response.getStatusLine().getStatusCode());
//                Log.i("test", content);
//                String result = content;
                JSONObject login_json = new JSONObject(content);
//                System.out.println(login_json);
//                System.out.println(getType(jsonObj));
                String name = login_json.getString("name");
                Looper.prepare();
//                showPopup(popup_title="提示", popup_Message="当前登录用户："+name, popup_button="确认");
                Toast.makeText(LoginActivity.this, "当前登录用户：" + name, Toast.LENGTH_SHORT).show();
//                try
//                {
//                    Thread.sleep(3000);//单位：毫秒
//                } catch (Exception e) {
//                }
                System.out.println("跳转页面");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                //关闭当前界面
                finish();
                Looper.loop();
//                System.out.println(name);
            } catch (
                    UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (
                    ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (
                    JSONException e) {
//                e.printStackTrace();
                Looper.prepare();
                showPopup(popup_title = "提示", popup_Message = "账号或密码错误！\n请检查账号或密码", popup_button = "返回");
                Toast.makeText(LoginActivity.this, "账号或密码错误！\n请检查账号或密码", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
//            }.start();
        }
    }

    private static String getType(Object a) {
        return a.getClass().toString();
    }

    /**
     * 设置弹窗
     *
     * @param show_title   标题
     * @param show_message 弹窗信息
     * @param show_button  返回格式
     */

    public void showPopup(String show_title, String show_message, String show_button) {
        new AlertDialog.Builder(this)
                .setTitle(show_title)
                .setMessage(show_message)
                .setPositiveButton(show_button, null)
                .show();
    }

    //获取屏幕尺寸
    public int[] screenSize() {
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        System.out.println(width + "\n" + height);
        int[] h_w = new int[]{height, width};
        return h_w;
    }


    /**
     * 设置控件大小
     *
     * @param view   控件
     * @param cksize 尺寸
     */
    public static void setViewSize(View view, int[] cksize) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        int set_size = (int) (cksize[1] * 0.6);
        params.width = set_size;
        params.height = set_size;
        view.setLayoutParams(params);
    }


}

/*原始登录验证(本地)
public class LoginActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private Button bt_log;
    private Button bt_quit;
    static String zt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //利用布局资源文件设置用户界面
        setContentView(R.layout.activity_login);

        //通过资源标识获得控件实例
        et_username = (EditText) findViewById(R.id.in_user);
        et_password = (EditText) findViewById(R.id.in_pawd);
        bt_log = (Button) findViewById(R.id.bt_login);
        bt_quit = (Button) findViewById(R.id.bt_esc);
        bt_log.setOnClickListener(new on1(this));
*/
/*
//按钮监听
        //给登录按钮注册监听器，实现监听器接口，编写事件
        bt_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的数据
                String strUsername = et_username.getText().toString();
                String strPassword = et_password.getText().toString();
                //判断用户名和密码是否正确（为可以进行测试，将用户名和密码都定义为admin）
                if(strUsername.equals("admin") && strPassword.equals("admin")){
                    Toast.makeText(LoginActivity.this,"账号或密码正确！",Toast.LENGTH_SHORT).show();
                    System.out.println("跳转页面");
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    //关闭当前界面
                    finish();


                }else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误！\n请检查账号或密码",Toast.LENGTH_SHORT).show();
                    showExitDialog01();
                }

            }
        });
        //给取消按钮注册监听器，实现监听器接口，编写事件
        bt_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }
        );
    }
    */
/*


        // 简单消息提示框
//        public void showExitDialog01 () {
//            new AlertDialog.Builder(this)
//                    .setTitle("提示")
//                    .setMessage("账号或密码错误！\n请检查账号或密码")
//                    .setPositiveButton("返回", null)
//                    .show();
//        }
    }



    public class on1 implements View.OnClickListener {

        private Context tex;

        public on1(Context h) {
            this.tex = h;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            new Thread() {
                public void run() {
                    String user_id = et_username.getText().toString();
                    String pawd = et_password.getText().toString();
                    String initialUrl = "https://hzy.graduationproject.xyz/imust-gateway?";
                    String finUrl = initialUrl + "user_id=" + user_id + "&passwd=" + pawd;
                    try {
                        URL url = new URL(finUrl);
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        // 提交模式
                        connection.setRequestMethod("POST");
                        //读取超时 单位毫秒
                        connection.setReadTimeout(5000);
                        //连接超时 单位毫秒
                        connection.setConnectTimeout(5000);
                        //获取
                        int responseCode = connection.getResponseCode();
                        System.out.println(responseCode);
                        if (responseCode == 200) {
                            InputStream inputStream = connection.getInputStream();
                            String string = streamToString(inputStream, "utf-8");
                            String json = string.substring(string.indexOf("{"));
                            try {
                                JSONObject jsonObject = new JSONObject(json);
                                String code = jsonObject.getString("state");
                                zt = code;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Log.d("string", json);
                        }
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                ;
            }.start();
            showExitDialog01();
        }



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

    }


    // 简单消息提示框
        public void showExitDialog01 () {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("账号或密码错误！\n请检查账号或密码")
                    .setPositiveButton("返回", null)
                    .show();
        }



}*/
