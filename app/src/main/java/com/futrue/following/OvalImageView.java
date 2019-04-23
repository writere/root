package com.futrue.following;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.futrue.R;

public class OvalImageView extends View {

    private float radius;
    private int color;
    private Bitmap bitmap;

    public int rotation;

    Matrix matrix;
    Paint paint;
    BitmapShader bitmapShader;
    TypedArray array;

    /**
     * 函数：Rouge()
     * 功能：构造函数, 使用xml
     * 参数：@context, @attrs
     */
    public OvalImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        dataRecycle();
        array = context.obtainStyledAttributes(attrs, R.styleable.OvalImageView);
        if (array != null) {
            int count = array.getIndexCount();
            for (int i = 0; i < count; i++) {
                int attr = array.getIndex(i);
                switch (attr){
                    case R.styleable.OvalImageView_radius:
                        radius = array.getDimension(attr,0);
                        break;
                }
            }
            array.recycle();
            array = null;
        }
    }


    /**
     * 函数：dataRecycle()
     * 功能：回收Rouge运行中占用的内存, 通知GC清理垃圾
     * 后置：Rouge()
     */
    private void dataRecycle(){
        radius = 0;
        color = 0;
        matrix = null;
        paint = null;
        array = null;
        bitmapShader = null;
        bitmap = null;
    }


    /**
     * 函数：onDraw()
     * 功能：实现主渲染
     */
    @Override
    protected void onDraw(Canvas canvas){
        if (bitmap != null){
            bitmapPaint(canvas, bitmapTrim(bitmap));
        } else {
            paintBackground(canvas);
        }
    }


    /**
     * 函数：bitmapTrim()
     * 功能：自适应View的尺寸比例，裁剪得到Bitmap中心
     */
    private Bitmap bitmapTrim(Bitmap bitmap){
        int x = 0;
        int y = 0;
        int h = bitmap.getHeight();
        int w = bitmap.getWidth();
        Log.i("h",String.valueOf(h));
        Log.i("w",String.valueOf(w));
        float scale = (float)getHeight()/(float)getWidth();
        Log.i("height",String.valueOf(getHeight()));
        Log.i("width",String.valueOf(getWidth()));
        float ratio = scale/((float)h/(float)w);
        if(ratio != 1){
            if (ratio > 1){
                x = (int) ((w - (h/scale))/2);
                w = (int) (h/scale);
            }else if (ratio < 1){
                y = (int) ((h - (w*scale))/2);
                h = (int) (w*scale);
            }
            Log.i("x",String.valueOf(x));
            Log.i("y",String.valueOf(y));
            Log.i("w",String.valueOf(w));
            Log.i("h",String.valueOf(h));
            bitmap = Bitmap.createBitmap(bitmap, x, y, w, h);
        }
        return  bitmap;
    }

    /**
     * 函数：bitmapPaint()
     * 功能：绘制bitmap到画布
     * 参数：@canvas
     */
    public void bitmapPaint(Canvas canvas, Bitmap bitmap){
        float h = getHeight();
        float w = getWidth();
        Log.i("h",String.valueOf(h));
        Log.i("w",String.valueOf(w));
        float height = bitmap.getHeight();
        matrix = new Matrix();
        if (bitmap.getHeight() != h) {
            float zoom = h / height;
            matrix.postScale(zoom, zoom);
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        if (radius == 0) {
            canvas.drawBitmap(bitmap, matrix, paint);
        } else {
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            bitmapShader.setLocalMatrix(matrix);
            paint.setShader(bitmapShader);
            paint.setAntiAlias(true);
            if (radius >= h / 2 || radius >= h / 2) {
                canvas.drawCircle(w / (float) 2, h / (float) 2, h/(float) 2, paint);
            } else {
                canvas.drawRoundRect(new RectF(0, 0, h, w),
                        radius, radius, paint);
            }
        }
    }


    /**
     * 函数: setBitmap()
     * 功能: 加载图片
     */
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
        invalidate();
    }

    /**
     * 函数: setBitmap()
     * 功能: 加载图片
     */
    public void setRadius(float radius){
        this.radius = radius;
        invalidate();
    }


    /**
     * 函数: paintBackground()
     * 功能: 绘制背景
     */
    public void paintBackground(Canvas canvas){
        float h = getHeight();
        float w = getWidth();
        paint = new Paint();
        color = getResources().getColor(R.color.homeGrey);
        paint.setColor(color);
        paint.setAntiAlias(true);
        if (radius >= h / 2 || radius >= h / 2) {
            canvas.drawCircle(w / (float) 2, h / (float) 2, h / (float) 2, paint);
        } else {
            canvas.drawRoundRect(new RectF(0, 0, h, w),
                    radius, radius, paint);
        }
    }

}
