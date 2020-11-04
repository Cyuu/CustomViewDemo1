package com.thdz.customviewdemo.view;

import android.content.Context;
import android.content.res.ColorStateList;
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


public class BerMan extends View {

    public static final String TAG = "BerMan";

    public static final int MIN_LEN = 200;

    private int padding = 40;

    // 皮肤颜色
    private int bgColor = Color.parseColor("#eeeeee");

    // 皮肤颜色
    private int skinColor = Color.parseColor("#FFDEAD");
    // 眼睛颜色
    private int eyeColor = Color.parseColor("#000080");
    // 嘴颜色
    private int mouthColor = Color.parseColor("#FF3030");
    // 文字颜色
    private int textColor = Color.parseColor("#111111");
    // 手颜色
    private int handColor = Color.parseColor("#fcd397");
    // 脚颜色
    private int footColor = Color.parseColor("#f8d9aa");

    private ColorStateList skin;

    // 胸前一个大字
    private String textChest = "Ber";

    public String getTextChest() {
        return textChest;
    }

    public void setTextChest(String textChest) {
        this.textChest = textChest;
    }

    public BerMan(Context context) {
        super(context);
    }

    public BerMan(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BerMan);

        bgColor = a.getColor(R.styleable.BerMan_bgColor, Color.parseColor("#eeeeee"));
        skinColor = a.getColor(R.styleable.BerMan_skinColor, Color.parseColor("#FFDEAD"));
        eyeColor = a.getColor(R.styleable.BerMan_eyeColor, Color.parseColor("#000080"));
        mouthColor = a.getColor(R.styleable.BerMan_mouthColor, Color.parseColor("#FF3030"));
        textColor = a.getColor(R.styleable.BerMan_textColor, Color.parseColor("#111111"));
        handColor = a.getColor(R.styleable.BerMan_handColor, Color.parseColor("#fcd397"));
        footColor = a.getColor(R.styleable.BerMan_footColor, Color.parseColor("#f8d9aa"));

        ColorStateList skin = a.getColorStateList(R.styleable.BerMan_skinColor);

        textChest = a.getString(R.styleable.BerMan_text);

        a.recycle();
    }

    public BerMan(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BerMan(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(widthMeasureSpec);
        int height = getMySize(heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }

    private int getMySize(int measureSpec) {

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
        paint.setColor(bgColor);
        canvas.drawRect(0, 0, getRight(), getBottom(), paint);

        // 脸
        int head_r = width / 6;//也可以是getMeasuredHeight()/2,本例中我们已经将宽高设置相等了
        int centerX = width / 2;// + width/20; // 加padding，padding：1/20
        int centerY = head_r + padding;

        paint = new Paint();
        paint.setColor(skinColor); // skin skinColor
        canvas.drawCircle(centerX, centerY, head_r, paint);

        // 左眼
        int eye_r = head_r / 12;
        int eye_centerX = centerX - (eye_r * 4);
        int eye_centerY = centerY;
        paint = new Paint();
        paint.setColor(eyeColor);
        canvas.drawCircle(eye_centerX, eye_centerY, eye_r, paint);

        // 右眼
        int eye_centerX2 = centerX + (eye_r * 4);
        canvas.drawCircle(eye_centerX2, eye_centerY, eye_r, paint);

        // 绘制左眼外廓
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(eyeColor);

        RectF rect_eye_l = new RectF(eye_centerX - eye_r * 3, eye_centerY - eye_r, eye_centerX + eye_r * 3, eye_centerY + eye_r * 9);
        canvas.drawArc(rect_eye_l, 230, 80, false, paint);

        RectF rect_eye_2 = new RectF(eye_centerX - eye_r * 3, eye_centerY - eye_r * 9, eye_centerX + eye_r * 3, eye_centerY + eye_r);
        canvas.drawArc(rect_eye_2, 50, 80, false, paint);

        // 绘制左眼外廓
        RectF rect_eye_3 = new RectF(eye_centerX2 - eye_r * 3, eye_centerY - eye_r, eye_centerX2 + eye_r * 3, eye_centerY + eye_r * 9);
        canvas.drawArc(rect_eye_3, 230, 80, false, paint);

        RectF rect_eye_4 = new RectF(eye_centerX2 - eye_r * 3, eye_centerY - eye_r * 9, eye_centerX2 + eye_r * 3, eye_centerY + eye_r);
        canvas.drawArc(rect_eye_4, 50, 80, false, paint);

        // 眉毛 - 左
        canvas.drawLine(eye_centerX - eye_r * 2, eye_centerY - eye_r * 3, eye_centerX + eye_r * 2, eye_centerY - eye_r * 5 / 2, paint);

        // 眉毛 - 右
        canvas.drawLine(eye_centerX2 - eye_r * 2, eye_centerY - eye_r * 5 / 2, eye_centerX2 + eye_r * 2, eye_centerY - eye_r * 3, paint);

//        // 鼻
//        int nose_r = head_r / 20;
//        int nose_centerX = centerX;
//        int nose_centerY = centerY + eye_r * 3;
//        paint = new Paint();
//        paint.setColor(Color.parseColor("#444444"));
//        canvas.drawCircle(nose_centerX, nose_centerY, nose_r, paint);

        // 嘴
        paint = new Paint();
        paint.setColor(mouthColor);
        RectF oval = new RectF(centerX - head_r / 2, centerY - head_r / 2, centerX + head_r / 2, centerY + head_r / 2);
        canvas.drawArc(oval, 60, 60, false, paint);

        // 脑门上画个B
//        int w_text = width / 12;
//        paint = new Paint();
//        paint.setColor(Color.parseColor("#111111"));
//        paint.setTextSize(w_text);
//        canvas.drawText("B", centerX - w_text / 4, centerY - head_r / 4, paint);

    }


    private void drawBody(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        // 头部参数
        int head_r = width / 6; // 脸半径
        int head_X = width / 2;// 脸圆中心 X
        int head_Y = head_r + padding; //  脸圆中心 Y

        Paint paint = new Paint();
        paint.setColor(skinColor);

        // 脖子
        int nose_w = width / 20;
        int neck_left = head_X - nose_w / 2;
        int neck_top = head_Y + head_r;
        int neck_right = head_X + nose_w / 2;
        int neck_bottom = head_Y + head_r + nose_w / 6;
        canvas.drawRect(neck_left, neck_top, neck_right, neck_bottom, paint);

        // 驱干
        int body_w = width / 3;
        int body_h = width / 7 * 2;
        int body_left = head_X - body_w / 2;
        int body_top = neck_bottom;
        int body_right = head_X + body_w / 2;
        int body_bottom = neck_bottom + body_h;
        paint = new Paint();
        paint.setColor(skinColor);
        canvas.drawRect(body_left, body_top, body_right, body_bottom, paint);

        // 肚子上画个B
        int textSize = width / 10;
        paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        int text_width = (int) paint.measureText(textChest, 0, textChest.length());
        int text_height = body_top + body_h / 2 + body_h / 5;
        canvas.drawText(textChest, head_X - text_width / 2, text_height, paint);

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
        paint.setColor(skinColor);
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
        paint.setColor(skinColor);
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
        paint.setColor(handColor);
        canvas.drawCircle(arm_left_x2, arm_left_y2 + hand_r, hand_r, paint);
        canvas.drawCircle(arm_right_x2, arm_right_y2 + hand_r, hand_r, paint);

        // 大腿
        int leg_w = width / 10;
        int leg_h = width / 8;
        int off_width = width / 17;
        paint.setColor(skinColor);
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
        int foot_r = width / 10 / 2 * 4 / 3;
        int foot_left_x = head_X - off_width / 2 - leg_w / 2;
        int foot_left_y = body_bottom + leg_h + foot_r;
        paint.setColor(footColor);
        canvas.drawCircle(foot_left_x, foot_left_y, foot_r, paint);
        int foot_right_x = head_X + off_width / 2 + leg_w / 2;
        canvas.drawCircle(foot_right_x, foot_left_y, foot_r, paint);

    }


}
