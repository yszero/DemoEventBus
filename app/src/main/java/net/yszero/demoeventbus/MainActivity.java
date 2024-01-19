package net.yszero.demoeventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.activity_main_tv_to_page).setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        // 注册订阅者
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册订阅者
        EventBus.getDefault().unregister(this);
    }


    // 声明事件处理方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessEvent(BeanSuccessEvent event) {
        // 在主线程中处理事件
        Toast.makeText(this, "主页：" + event.message, Toast.LENGTH_SHORT).show();
    }

    // 声明事件处理方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailureEvent(BeanFailureEvent event) {
        // 在主线程中处理事件
        Toast.makeText(this, "主页：" + event.message, Toast.LENGTH_SHORT).show();
    }

}