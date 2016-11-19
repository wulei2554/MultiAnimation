package com.wulei2554.multianimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wulei2554.multianimation.animation.HeadParallax;
import com.wulei2554.multianimation.animation.QuickIndexBar;
import com.wulei2554.multianimation.animation.SlidingDelete;
import com.wulei2554.multianimation.animation.Stick;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }
    @OnClick({R.id.quickIndexBar, R.id.headParallax, R.id.slidingDelete, R.id.stick, R.id.activity_main})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quickIndexBar:
                startActivity(new Intent(MainActivity.this,QuickIndexBar.class));
                break;
            case R.id.headParallax:
                startActivity(new Intent(MainActivity.this,HeadParallax.class));
                break;
            case R.id.slidingDelete:
                startActivity(new Intent(MainActivity.this,SlidingDelete.class));
                break;
            case R.id.stick:
                startActivity(new Intent(MainActivity.this,Stick.class));
                break;
        }
    }
}
