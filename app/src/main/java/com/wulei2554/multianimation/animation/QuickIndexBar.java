package com.wulei2554.multianimation.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wulei2554.multianimation.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class QuickIndexBar extends AppCompatActivity {

    @InjectView(R.id.quickIndex)
    com.wulei2554.multianimation.view.QuickIndexBar quickIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_index_bar);
        ButterKnife.inject(this);

        quickIndex.setIOnIndexTouchListen(new com.wulei2554.multianimation.view.QuickIndexBar.IOnIndexTouchListen() {
            @Override
            public void selectIndex(String index) {
                Log.e("wulei",index);
            }
        });
    }
}
