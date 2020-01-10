package me.ibrokhim.lab2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.Px;

public class Lab2ViewsContainer extends LinearLayout {
    private int minViewsCount;
    private int viewsCount;

    public Lab2ViewsContainer(Context context) {
        this(context, null);
    }

    public Lab2ViewsContainer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Lab2ViewsContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Lab2ViewsContainer, defStyleAttr, 0);

        minViewsCount = a.getInt(R.styleable.Lab2ViewsContainer_lab2_minViewsCount, 0);

        if (minViewsCount < 0) {
            throw new IllegalArgumentException("minViewsCount can't be less than 0");
        }

        a.recycle();

        setViewsCount(minViewsCount);
    }

    public void incrementViews() {
//        TextView textView = new TextView(getContext());
//        textView.setPadding(dpTpPx(8), dpTpPx(8), dpTpPx(8), dpTpPx(8));
//        textView.setTextSize(16);
//        textView.setText(String.valueOf(viewsCount++));

        MyView myView = new MyView(getContext(),null);
        myView.setPadding(dpTpPx(8), dpTpPx(8), dpTpPx(8), dpTpPx(8));

        addView(myView);

        TextView titleView1 = (TextView) ((RelativeLayout) myView.getChildAt(0)).getChildAt(1);
        titleView1.setText("BoolTitle" + viewsCount);

        TextView subtitleView1 = (TextView) ((RelativeLayout) myView.getChildAt(0)).getChildAt(2);
        subtitleView1.setText("BoolSubtitle" + viewsCount++);
    }

    public void setViewsCount(int count) {
        if (viewsCount == count) {
            return;
        }

        viewsCount = count < minViewsCount ? minViewsCount : count;

        removeAllViews();

        viewsCount = 0;

        for (int i = 0; i < count; i++) {
            incrementViews();
        }
    }

    public int getViewsCount() {
        return viewsCount;
    }

    @Px
    public int dpTpPx(float dp) {
        if (dp == 0) return 0;

        float density = getResources().getDisplayMetrics().density;

        return (int) Math.ceil(density * dp);
    }
}
