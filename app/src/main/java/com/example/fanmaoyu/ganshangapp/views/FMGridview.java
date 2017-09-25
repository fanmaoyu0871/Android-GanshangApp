package com.example.fanmaoyu.ganshangapp.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by fanmaoyu on 2017/9/21.
 */

public class FMGridview extends GridView {
    public FMGridview(Context context) {
        super(context);
    }

    public FMGridview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FMGridview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
