package com.wulei2554.multianimation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class QuickIndexBar extends View {

    private String[] indexArr = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};

    private Paint paint;
    private int width;
    private float clleHeight;
    private int lastIndex = -1;
    private IOnIndexTouchListen iOnIndexTouchListen;

    public QuickIndexBar(Context context) {
        super(context);
        init();
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //设置抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置文本的绘制从文本底边的中心开始
        paint.setTextAlign(Paint.Align.CENTER);
        //设置文本大小
        paint.setTextSize(16);
        //设置文本颜色
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        //一个文本的高度
        clleHeight = getMeasuredHeight() * 1f / indexArr.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < indexArr.length; i++) {
            /**
             * 文本绘制的起始位置
             */
            //选中的字母变为黑色
            paint.setColor(lastIndex == i ? Color.BLACK:Color.WHITE);
            float x = width / 2;
            float y = clleHeight / 2 + getTextHeight(indexArr[i]) / 2 + clleHeight * i;
            canvas.drawText(indexArr[i], x, y, paint);
        }
    }

    /**
     * 计算触摸点对应的字母
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                //对应字母所在的索引
                int index = (int) (y / clleHeight);
                if ((lastIndex != index) && (index >= 0) && (index < 26)) {
                   if (iOnIndexTouchListen != null){
                       iOnIndexTouchListen.selectIndex(indexArr[index]);
                   }
                }
                //记录当前的字母
                lastIndex = index;
                break;
            case MotionEvent.ACTION_UP:
                //重置
                lastIndex = -1;
                break;
        }
        //点击字母变色，重绘
        postInvalidate();
        return true;
    }

    public void setIOnIndexTouchListen(IOnIndexTouchListen iOnIndexTouchListen) {
        this.iOnIndexTouchListen = iOnIndexTouchListen;
    }

    /**
     * 将当前的选中的字母暴露出去
     */
    public interface IOnIndexTouchListen {
        void selectIndex(String index);
    }

    private float getTextHeight(String s) {
        //获取文本高度
        Rect bounds = new Rect();
        paint.getTextBounds(s, 0, s.length(), bounds);
        return bounds.height();
    }
}
