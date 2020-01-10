package me.ibrokhim.lab2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MyView extends RelativeLayout {
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.my_view, this);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) ((RelativeLayout) ((RelativeLayout) v).getChildAt(0)).getChildAt(3);
                checkBox.setChecked(checkBox.isChecked() ^ true);
            }
        });
    }
}
