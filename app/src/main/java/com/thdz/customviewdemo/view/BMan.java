package com.thdz.customviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.thdz.customviewdemo.R;


public class BMan extends View {

    public static final String TAG = "BMan";

    public static final int MIN_LEN = 200;

    // private int size = 0;

    public BMan(Context context) {
        super(context);
    }

    public BMan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BerMan);

        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
//        size = a.getDimensionPixelSize(R.styleable.BMan_size, MIN_LEN);

        //最后记得将TypedArray对象回收
        a.recycle();
    }

    public BMan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BMan(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(widthMeasureSpec);
        int height = getMySize(heightMeasureSpec);
        Log.i(TAG, "onMeasure width = " + width + ", height = " + height);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

//        if (height > width) {
//            height = width;
//        } else {
//            height = width;
//        }

        setMeasuredDimension(width, height);
    }

    private int getMySize(int measureSpec) {
//        if (size < MIN_LEN) {
////            size = MIN_LEN;
////        }
////        int defaultSize = size;

        int defaultSize = MIN_LEN;
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: //如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;

            case MeasureSpec.AT_MOST: //如果测量模式是最大取值为size, // 我们将大小取最大值,你也可以取其他值
            case MeasureSpec.EXACTLY: //如果是固定的大小，那就不要去改变它
                mySize = size;
                break;

        }
        return mySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画头
        drawHead(canvas);
        // 画身体
        drawBody(canvas);

    }

    private void drawHead(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Log.i(TAG, "onDraw width = " + width + ", height = " + height);

        // 背景色
        Paint paint = new Paint();
//        canvas.drawColor(Color.parseColor("#d6d6d6"));

        paint.setColor(Color.parseColor("#eeeeee"));
        Log.i(TAG, "onDraw， getLeft = " + getLeft() + "，getTop() = " + getTop() + "， getRight() = " + getRight() + ",getBottom() = " + getBottom());
        canvas.drawRect(0,0,getRight(),getBottom(), paint);


        // 脸
        int head_r = width / 4 / 2;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
        int centerX = getLeft() + width / 2;// + width/20; // 加padding，padding：1/20
        int centerY = getTop() + head_r + width / 20;

        paint = new Paint();
        paint.setColor(Color.parseColor("#FFDEAD"));
        canvas.drawCircle(centerX, centerY, head_r, paint);

        // 左眼
        int eye_r = head_r / 10;
        int eye_centerX = centerX - (eye_r * 2);
        int eye_centerY = centerY;
        paint = new Paint();
        paint.setColor(Color.parseColor("#000080"));
        canvas.drawCircle(eye_centerX, eye_centerY, eye_r, paint);

        // 右眼
        int eye_centerX2 = centerX + (eye_r * 2);
        canvas.drawCircle(eye_centerX2, eye_centerY, eye_r, paint);

//        // 鼻
//        int nose_r = head_r / 20;
//        int nose_centerX = centerX;
//        int nose_centerY = centerY + eye_r * 3;
//        paint = new Paint();
//        paint.setColor(Color.parseColor("#444444"));
//        canvas.drawCircle(nose_centerX, nose_centerY, nose_r, paint);

        // 嘴
        paint = new Paint();
        paint.setColor(Color.parseColor("#FF3030"));
        RectF oval = new RectF(centerX - head_r / 2, centerY - head_r / 2, centerX + head_r / 2, centerY + head_r / 2);
        canvas.drawArc(oval, 60, 60, false, paint);

        // B字
        int w_text = width / 12;
        paint = new Paint();
        paint.setColor(Color.parseColor("#111111"));
        paint.setTextSize(w_text);
        canvas.drawText("B", centerX - w_text / 4, centerY - head_r / 4, paint);

    }


    private void drawBody(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        // 脖，一个rect
        int head_r = width / 4 / 2; // 脸半径
        int head_X = getLeft() + width / 2;// 脸圆中心 X
        int head_Y = getTop() + head_r + width / 20; //  脸圆中心 Y

        int nose_w = width / 28;
        int neck_left = head_X - nose_w / 2;
        int neck_top = head_Y + head_r;
        int neck_right = head_X + nose_w / 2;
        int neck_bottom = head_Y + head_r + nose_w;
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FFDEAD"));
        canvas.drawRect(neck_left, neck_top, neck_right, neck_bottom, paint);

        // 驱干
        int body_w = width / 3;
        int body_h = width / 4;
        int body_left = head_X - body_w / 2;
        int body_top = neck_bottom;
        int body_right = head_X + body_w / 2;
        int body_bottom = neck_bottom + body_h;
        paint = new Paint();
        paint.setColor(Color.parseColor("#FFDEAD"));
        canvas.drawRect(body_left, body_top, body_right, body_bottom, paint);

        // 胳膊 - 左
        int arm_left_x1 = body_left;
        int arm_left_x2 = body_left - width / 7;
        int arm_left_y1 = body_top;
        int arm_left_y2 = body_top + width / 7;

        int arm_left_x3 = body_left;
        int arm_left_x4 = body_left - width / 7;
        int arm_left_y3 = body_top + width / 12;
        int arm_left_y4 = body_top + width / 7 + width / 12;

        // 胳膊 - 右
        int arm_right_x1 = body_right;
        int arm_right_x2 = body_right + width / 7;
        int arm_right_y1 = body_top;
        int arm_right_y2 = body_top + width / 7;

        int arm_right_x3 = body_right;
        int arm_right_x4 = body_right + width / 7;
        int arm_right_y3 = body_top + width / 12;
        int arm_right_y4 = body_top + width / 7 + width / 12;

        // 胳膊——左
        paint = new Paint();
        paint.setColor(Color.parseColor("#FFDEAD"));
        paint.setStrokeWidth(1);//为画笔设置粗细
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();//绘制多边形的类
        path.moveTo(arm_left_x1, arm_left_y1);
        path.lineTo(arm_left_x2, arm_left_y2);
        path.lineTo(arm_left_x4, arm_left_y4);
        path.lineTo(arm_left_x3, arm_left_y3);
        path.close();//闭合图形
        canvas.drawPath(path, paint);//绘制多边形

        // 胳膊——右
        paint = new Paint();
        paint.setColor(Color.parseColor("#FFDEAD"));
        paint.setStrokeWidth(1);//为画笔设置粗细
        paint.setStyle(Paint.Style.FILL);
        Path path2 = new Path();//绘制多边形的类
        path2.moveTo(arm_right_x1, arm_right_y1);
        path2.lineTo(arm_right_x2, arm_right_y2);
        path2.lineTo(arm_right_x4, arm_right_y4);
        path2.lineTo(arm_right_x3, arm_right_y3);
        path2.close();//闭合图形
        canvas.drawPath(path2, paint);//绘制多边形

        // 手
        int hand_r = (arm_left_y3 - arm_left_y1) / 2;
        canvas.drawCircle(arm_left_x2, arm_left_y2 + hand_r, hand_r, paint);
        canvas.drawCircle(arm_right_x2, arm_right_y2 + hand_r, hand_r, paint);

        // 大腿
        int leg_w = width / 10;
        int leg_h = width / 6;
        int off_width = width / 17;

        canvas.drawRect(
                head_X - off_width / 2 - leg_w,
                body_bottom,
                head_X - off_width / 2,
                body_bottom + leg_h,
                paint);

        canvas.drawRect(
                head_X + off_width / 2,
                body_bottom,
                head_X + off_width / 2 + leg_w,
                body_bottom + leg_h,
                paint);

        // 脚
        int foot_r = width / 10;
        int foot_left_x = body_bottom + leg_h + foot_r;


    }


}
