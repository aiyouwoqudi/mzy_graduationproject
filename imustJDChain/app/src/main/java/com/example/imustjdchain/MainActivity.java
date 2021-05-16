package com.example.imustjdchain;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_out:
                Toast.makeText(getApplicationContext(),"正在输出数据", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_switch:
                Toast.makeText(getApplicationContext(),"准备切换用户",Toast.LENGTH_SHORT).show();
                System.out.println("跳转页面");
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                //关闭当前界面
                finish();
                break;
            case R.id.menu_Cancellation:
                Toast.makeText(getApplicationContext(),"注销登录",Toast.LENGTH_SHORT).show();
                //给取消按钮注册监听器，实现监听器接口，编写事件
                        finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}