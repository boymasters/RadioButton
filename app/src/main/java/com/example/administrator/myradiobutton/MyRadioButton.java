package com.example.administrator.myradiobutton;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by Mr Q on 2018/04/13.
 */

public class MyRadioButton extends android.support.v7.widget.AppCompatRadioButton {
    private TextView textView;
    /** 文字字体大小**/
    private int TextSize;
    /** 文字字体初始大小**/
    private int SaveTextSize;
    /** 文本内容**/
    private String Text;
    /** 文本初始内容**/
    private String SaveText;
    /** 文字字体颜色 **/
    private int TextColor;
    /** 文字字体初始颜色 **/
    private int SaveTextColor;
    /** 文字字体Paint **/
    private Paint TextPaint;
    /** 文字文本框 **/
    private Rect mBound;
    /** 下划线Paint **/
    private Paint LinePaint;
    /** 下划线颜色 **/
    private int LineColor;
    /** 下划初始线颜色 **/
    private int SaveLineColor;
    /** 下划高度 **/
    private int LineHeight;
    /** 下划初始高度 **/
    private int SaveLineHeight;
    /** 是否被选中 **/
    private boolean isCheck;
    /** 选中后的文字字体大小 **/
    private int isCheck_TextSize;
    /** 选中后的文本内容 **/
    private String isCheck_Text;
    /** 选中后的文字字体颜色 **/
    private int isCheck_TextColor;
    /** 选中后的下划线颜色 **/
    private int isCheck_LineColor;
    /** 选中后的下划线高度 **/
    private int isCheck_LineHeight;


    public MyRadioButton(Context context) {
        super(context);
        init(context,null);
    }

    public MyRadioButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MyRadioButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int measuredHeight, measuredWidth;
        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
        } else {
            measuredWidth = mBound.width()+getPaddingRight()+getPaddingLeft();
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        } else {
            measuredHeight = mBound.height()+getPaddingBottom()+getPaddingTop();
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.radiobutton);

        TextSize = typedArray.getDimensionPixelSize(R.styleable.radiobutton_TextSize,dp2px(16));
        Text = typedArray.getString(R.styleable.radiobutton_Text);
        TextColor = typedArray.getColor(R.styleable.radiobutton_TextColor,0xffffffff);
        LineColor = typedArray .getColor(R.styleable.radiobutton_LineColor,0xffffffff);
        LineHeight = typedArray.getDimensionPixelSize(R.styleable.radiobutton_LineHeight,dp2px(2));
        isCheck = typedArray.getBoolean(R.styleable.radiobutton_isCheck,false);
        isCheck_TextSize = typedArray.getDimensionPixelSize(R.styleable.radiobutton_isCheck_TextSize,dp2px(16));
        isCheck_Text = typedArray.getString(R.styleable.radiobutton_isCheck_Text);
        isCheck_TextColor = typedArray.getColor(R.styleable.radiobutton_isCheck_TextColor,0xffffffff);
        isCheck_LineColor = typedArray.getColor(R.styleable.radiobutton_isCheck_LineColor,0xffffffff);
        isCheck_LineHeight = typedArray.getDimensionPixelSize(R.styleable.radiobutton_isCheck_LineHeight,dp2px(2));
        typedArray.recycle();
        SaveData();
        if (isCheck)
            isCheck();
        TextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBound = new Rect();
        LinePaint = new Paint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        TextPaint.setTextSize(TextSize);
        TextPaint.getTextBounds(Text, 0, Text.length(), mBound);
        TextPaint.setColor(TextColor);
        LinePaint.setColor(LineColor);
        canvas.drawText(Text, getWidth() / 2 - mBound.width() / 2, getHeight()/2 + mBound.height()/4  , TextPaint);
        canvas.drawRect(0,getHeight()- LineHeight,getWidth(),getHeight(),LinePaint);
    }


    /**
     * 保存初始化数据
     */
    private void SaveData() {
        SaveTextColor = TextColor;
        SaveText = Text;
        SaveTextSize = TextSize ;
        SaveLineHeight = LineHeight;
        SaveLineColor = LineColor;
    }


    public void isChecked(boolean isCheck){
        if (isCheck)
            isCheck();
        else
             Save();
        invalidate();
    }
    public void isCheck(){
        TextSize = isCheck_TextSize;
        Text = isCheck_Text;
        TextColor = isCheck_TextColor;
        LineColor = isCheck_LineColor;
        LineHeight = isCheck_LineHeight;
    }

    public void Save(){
        TextSize = SaveTextSize;
        Text = SaveText;
        TextColor = SaveTextColor;
        LineColor = SaveLineColor;
        LineHeight = SaveLineHeight;
    }

    private  int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }
}
