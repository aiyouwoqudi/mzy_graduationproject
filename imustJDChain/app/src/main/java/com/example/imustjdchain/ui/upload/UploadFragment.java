package com.example.imustjdchain.ui.upload;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

//import com.example.imustjdchain.LoginActivity;

import com.example.imustjdchain.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class UploadFragment extends Fragment {

    //    private UploadViewModel UploadViewModel;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        UploadViewModel =
//                new ViewModelProvider(this).get(UploadViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_upload, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        UploadViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
//    }
    private View view;
    private List<String> data_list = new ArrayList<String>();//下拉列表展示的数据
    private ArrayAdapter<String> arr_adapter;//spanner的适配器
    private EditText ledHash;
    private EditText cA_ledname;
    private EditText cA_accName;
    private EditText cA_v_type;
    private EditText cA_v_key;
    private EditText cA_v_value;
    private EditText acc_hash;
    private EditText aV_acc_type;
    private EditText aV_acc_key;
    private EditText aV_acc_value;
    private EditText uC_hash;
    private EditText uC_acc_type;
    private EditText uC_acc_key;
    private EditText uC_acc_value;
    private Button bt_upload;
    private Button bt_ck_data;
    private Button bt_ck_format;
    private Spinner sp_ledhash;
    private Spinner sp_ledname;
    static int checktem = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.fragment_upload, null);
//        tv_lh=(TextView) view.findViewById(R.id.tv_ledHash);

//        ledHash = (EditText) view.findViewById(R.id.ed_led_Hash);
//        cA_ledname = (EditText) view.findViewById(R.id.ed_cA_ledname);
        cA_accName = (EditText) view.findViewById(R.id.ed_cA_accName);
        cA_v_type = (EditText) view.findViewById(R.id.ed_cA_v_type);
        cA_v_key = (EditText) view.findViewById(R.id.ed_cA_v_key);
        cA_v_value = (EditText) view.findViewById(R.id.ed_cA_v_value);
        acc_hash = (EditText) view.findViewById(R.id.ed_ad_acc_hash);
        aV_acc_type = (EditText) view.findViewById(R.id.ed_aV_acc_type);
        aV_acc_key = (EditText) view.findViewById(R.id.ed_aV_acc_key);
        aV_acc_value = (EditText) view.findViewById(R.id.ed_aV_acc_value);
        uC_hash = (EditText) view.findViewById(R.id.ed_uC_hash);
        uC_acc_type = (EditText) view.findViewById(R.id.ed_uC_acc_type);
        uC_acc_key = (EditText) view.findViewById(R.id.ed_uC_acc_key);
        uC_acc_value = (EditText) view.findViewById(R.id.ed_uC_acc_value);
        bt_upload = (Button) view.findViewById(R.id.bt_Upload);
        bt_ck_data = (Button) view.findViewById(R.id.bt_check_data);
        bt_ck_format = (Button) view.findViewById(R.id.bt_check_format);


//        List<String> list_ledhash = new ArrayList<String>();
//        String [] sg1 = {"内蒙古科技大学"};
//        for (int i = 0; i < sg1.length; i++) {
//            list_ledhash.add(sg1[i]);
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list_ledhash);
//        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//        sp_ledname = (Spinner) view.findViewById(R.id.sp_ledhash);
//        sp_ledname.setAdapter(adapter);
//
//
//
//        List<String> list_ledname = new ArrayList<String>();
//        String [] sg = {"内蒙古科技大学"};
//        for (int i = 0; i < sg.length; i++) {
//            list_ledname.add(sg[i]);
//        }
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list_ledname);
//        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//        sp_ledname = (Spinner) view.findViewById(R.id.sp_ledname);
//        sp_ledname.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
//        showPopup("提示","cuowu","queding");

        bt_ck_format.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
//            new Thread() {
                    String ledHash_in = ledHash.getText().toString();
                    String cA_ledname_in = cA_ledname.getText().toString();
                    String cA_accName_in = cA_accName.getText().toString();
                    String cA_v_type_in = cA_v_type.getText().toString();
                    String cA_v_key_in = cA_v_key.getText().toString();
                    String cA_v_value_in = cA_v_value.getText().toString();
                    String aV_acc_hash_in = acc_hash.getText().toString();
                    String aV_acc_type_in = aV_acc_type.getText().toString();
                    String aV_aC_acc_key_in = aV_acc_key.getText().toString();
                    String aV_acc_value_in = aV_acc_value.getText().toString();
                    String uC_hash_in = uC_hash.getText().toString();
                    String uC_acc_type_in = uC_acc_type.getText().toString();
                    String uC_acc_key_in = uC_acc_key.getText().toString();
                    String uC_acc_value_in = uC_acc_value.getText().toString();
//            String url="https://hzy.graduationproject.xyz/imust-gateway/GetMessage";
//            String login_url = "https://hzy.graduationproject.xyz/imust-gateway/LoginCheck";
                    String upload_url = "https://hzy.graduationproject.xyz/Upload";

                    if (
                            (ledHash_in.length() != 0) && (
                                    (cA_ledname_in.length() != 0 && cA_accName_in.length() != 0 && cA_v_type_in.length() != 0 && cA_v_key_in.length() != 0 && cA_v_value_in.length() != 0) ||
                                            (aV_acc_hash_in.length() != 0 && aV_aC_acc_key_in.length() != 0 && aV_acc_value_in.length() != 0) ||
                                            (uC_hash_in.length() != 0 && uC_acc_type_in.length() != 0 && uC_acc_key_in.length() != 0 && uC_acc_value_in.length() != 0))
                    ) {
                        Looper.prepare();
                        showPopup("提示", "格式正确！请继续上传", "确认");
                        Toast.makeText(getActivity(), "格式正确！请继续上传", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                        checktem += 1;
                    } else {
                        Looper.prepare();
                        showPopup("警告", "格式正确！请返回确认输入", "确认");
                        Toast.makeText(getActivity(), "格式正确！请返回确认输入", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            }).start();
        });


        bt_ck_data.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
//            new Thread() {
                    String ledHash_in = ledHash.getText().toString();
                    String cA_ledname_in = cA_ledname.getText().toString();
                    String cA_accName_in = cA_accName.getText().toString();
                    String cA_v_type_in = cA_v_type.getText().toString();
                    String cA_v_key_in = cA_v_key.getText().toString();
                    String cA_v_value_in = cA_v_value.getText().toString();
                    String aV_acc_hash_in = acc_hash.getText().toString();
                    String aV_acc_type_in = aV_acc_type.getText().toString();
                    String aV_aC_acc_key_in = aV_acc_key.getText().toString();
                    String aV_acc_value_in = aV_acc_value.getText().toString();
                    String uC_hash_in = uC_hash.getText().toString();
                    String uC_acc_type_in = uC_acc_type.getText().toString();
                    String uC_acc_key_in = uC_acc_key.getText().toString();
                    String uC_acc_value_in = uC_acc_value.getText().toString();

                    Looper.prepare();
                    showPopup("提示", "ledgerHash：" + ledHash_in + "\n" +
                            "createAccount：\n" +
                            "\tledger_name：" + cA_ledname_in + "\n" +
                            "\taccount_name：" + cA_accName_in + "\n" +
                            "\tvalue：\n" +
                            "\t\ttype：" + cA_v_type_in + "\n" +
                            "\t\tkey：" + cA_v_key_in + "\n" +
                            "\t\tvalue：" + cA_v_value_in + "\n" +
                            "createAccount：\n" +
                            "\taccount_hash：" + aV_acc_hash_in + "\n" +
                            "\ttype：" + aV_acc_type_in + "\n" +
                            "\tkey：" + aV_aC_acc_key_in + "\n" +
                            "\tvalue：" + aV_acc_value_in + "\n" +
                            "uploadContract: uploadContract\n" +
                            "updateValue：\n" +
                            "\taccount_hash：" + uC_hash_in + "\n" +
                            "\ttype：" + uC_acc_type_in + "\n" +
                            "\tkey：" + uC_acc_key_in + "\n" +
                            "\tvalue：" + uC_acc_value_in, "确认");
                    Toast.makeText(getActivity(), "格式正确！请返回确认输入", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    checktem += 1;
                }
            }).start();
        });


        bt_upload.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
//            new Thread() {
                    String ledHash_in = ledHash.getText().toString();
                    String cA_ledname_in = cA_ledname.getText().toString();
                    String cA_accName_in = cA_accName.getText().toString();
                    String cA_v_type_in = cA_v_type.getText().toString();
                    String cA_v_key_in = cA_v_key.getText().toString();
                    String cA_v_value_in = cA_v_value.getText().toString();
                    String aV_acc_hash_in = acc_hash.getText().toString();
                    String aV_acc_type_in = aV_acc_type.getText().toString();
                    String aV_aC_acc_key_in = aV_acc_key.getText().toString();
                    String aV_acc_value_in = aV_acc_value.getText().toString();
                    String uC_hash_in = uC_hash.getText().toString();
                    String uC_acc_type_in = uC_acc_type.getText().toString();
                    String uC_acc_key_in = uC_acc_key.getText().toString();
                    String uC_acc_value_in = uC_acc_value.getText().toString();
//            String url="https://hzy.graduationproject.xyz/imust-gateway/GetMessage";
//            String login_url = "https://hzy.graduationproject.xyz/imust-gateway/LoginCheck";
                    String upload_url = "http://10.144.12.60:35080/imust-gateway/Upload";
                    if (
                            (ledHash_in.length() != 0) && (
                                    (cA_ledname_in.length() != 0 && cA_accName_in.length() != 0 && cA_v_type_in.length() != 0 && cA_v_key_in.length() != 0 && cA_v_value_in.length() != 0) ||
                                            (aV_acc_hash_in.length() != 0 && aV_aC_acc_key_in.length() != 0 && aV_acc_value_in.length() != 0) ||
                                            (uC_hash_in.length() != 0 && uC_acc_type_in.length() != 0 && uC_acc_key_in.length() != 0 && uC_acc_value_in.length() != 0))
                    ) {
                        String json = "{\n" +
                                "\"ledgerHash\":" + "\"" + ledHash_in + "\"" + ",\n" +
                                "\"createAccount\": [\n" +
                                "{\n" +
                                "\"ledger_name\":" + "\"" + cA_ledname_in + "\"" + ",\n" +
                                "\"account_name\":" + "\"" + cA_accName_in + "\"" + ",\n" +
                                "\"value\": [\n" +
                                "{\n" +
                                "\"type\":" + "\"" + cA_v_type_in + "\"" + ",\n" +
                                "\"key\":" + "\"" + cA_v_key_in + "\"" + ",\n" +
                                "\"value\":" + "\"" + cA_v_value_in + "\"" + "\n" +
                                "}\n" +
                                "]\n" +
                                "}\n" +
                                "],\n" +
                                "\"addValue\": [\n" +
                                "{\n" +
                                "\"account_hash\":" + "\"" + aV_acc_hash_in + "\"" + ",\n" +
                                "\"type\":" + "\"" + aV_acc_type_in + "\"" + ",\n" +
                                "\"key\":" + "\"" + aV_aC_acc_key_in + "\"" + ",\n" +
                                "\"value\":" + "\"" + aV_acc_value_in + "\"" + "\n" +
                                "}\n" +
                                "],\n" +
                                "\"uploadContract\":\"uploadContract\",\n" +
                                "\"updateValue\": [\n" +
                                "{\n" +
                                "\"account_hash\":" + "\"" + uC_hash_in + "\"" + ",\n" +
                                "\"type\":" + "\"" + uC_acc_type_in + "\"" + ",\n" +
                                "\"key\":" + "\"" + uC_acc_key_in + "\"" + ",\n" +
                                "\"value\":" + "\"" + uC_acc_value_in + "\"" + "\n" +
                                "}\n" +
                                "]\n" +
                                "}";

//                        System.out.println(json);
                        Log.d("msg", json);
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost post = new HttpPost(upload_url);
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
                            String name = login_json.getString("err");
                            Log.d(TAG, content);
//                            System.out.println(name);
                            Looper.prepare();
                        } catch (
                                UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (
                                ClientProtocolException e) {
                            e.printStackTrace();
                        } catch (
                                IOException e) {
                            e.printStackTrace();
                        } catch (
                                JSONException e) {
                        }

                    } else {
                        Looper.prepare();
                        showPopup("警告", "请遵循上传规则：\n填写必填项，不允许上传空值", "了解");
                        Looper.loop();

                    }
                }
            }).start();
        });


    }


    /**
     * 设置弹窗
     * @param show_title 标题
     * @param show_message 弹窗信息
     * @param show_button 返回格式
     */
    public void showPopup(String show_title, String show_message, String show_button) {
        new AlertDialog.Builder(getActivity())
                .setTitle(show_title)
                .setMessage(show_message)
                .setPositiveButton(show_button, null)
                .show();
    }


}