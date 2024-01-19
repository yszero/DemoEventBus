package net.yszero.demoeventbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.activity_second_tv_post_msg).setOnClickListener(view -> {
            //发送成功消息
            EventBus.getDefault().post(new BeanSuccessEvent("成功消息"));
        });

        findViewById(R.id.activity_second_tv_post_msg2).setOnClickListener(view -> {
            //发送失败消息
            EventBus.getDefault().post(new BeanFailureEvent("失败消息"));
        });

        findViewById(R.id.activity_second_tv_back_page).setOnClickListener(view -> {
            finish();
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
        Toast.makeText(this, "二页：" + event.message, Toast.LENGTH_SHORT).show();
    }

    // 声明事件处理方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailureEvent(BeanFailureEvent event) {
        // 在主线程中处理事件
        Toast.makeText(this, "二页：" + event.message, Toast.LENGTH_SHORT).show();
    }

}