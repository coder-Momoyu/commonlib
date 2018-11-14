package com.momoyu.lytest.UDP;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.EmbossMaskFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.momoyu.lytest.R;

/**
 * Created by ${momoyu} on 2018/11/7.
 */

public class CustomView extends View {

    Paint paint = new Paint();

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(200, BlurMaskFilter.Blur.INNER);
        paint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10));
        //paint.setMaskFilter(blurMaskFilter);
        paint.setAntiAlias(true);
        int save = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        canvas.drawCircle(190,190,190,paint);
    }
}
