package com.futrue.following;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.futrue.R;

public class ScreamLinerLayout extends LinearLayout {

    private String[] screams;



    /**
     * 函数：Rouge()
     * 功能：构造函数, 使用xml
     * 参数：@context, @attrs
     */
    public ScreamLinerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    /**
     * 函数：onDraw()
     * 功能：实现主渲染
     */
    @Override
    protected void onDraw(Canvas canvas){
        if (screams.length != 0){
            addScream(screams);
        }
    }


    private void addScream(String[] screams){
        int width = getWidth();
        float length = 0;
        View temp = this;
        LinearLayout linear = (LinearLayout) temp;
        TextView textView = (TextView)linear.getChildAt(0);

        boolean isFull = false;
        Log.d("调试ScreamLinerLayout:","开始addScream！");
        String[] give = screams[0].split("@");
        if (give[0].equals("NULL")) {
            for (int i = 0; i < give.length; i++) {
                textView.setText("赠送" + screams[i]);
                length += textView.getWidth();
                if (length > width) {
                    isFull = true;
                    break;
                }
                textView.setVisibility(VISIBLE);
                linear.addView(textView, 0);
            }
        }

        if (!isFull && !give[0].equals("0")) {
            textView.setText("新客" + String.valueOf(Integer.valueOf(screams[1])*10)+"折");
            length += textView.getWidth();
            if (length > width) {
                textView.setVisibility(VISIBLE);
                linear.addView(textView, 0);
            }
        }

        String[] reduces = screams[2].split("@");
        if (!isFull && give[0].equals("NULL")) {
            for (int i=0; i<reduces.length; i+=2) {
                textView.setText(reduces[i]+"减"+reduces[i+1]);
                length += textView.getWidth();
                if (length > width) {
                    break;
                }
                textView.setVisibility(VISIBLE);
                linear.addView(textView, 0);
            }
        }
    }



    /**
     * 函数: setScream()
     * 功能: 加载图片
     */
    public void setScream(String scream){
        this.setTag(scream);
        this.screams = scream.split("#");
        invalidate();
    }


}
