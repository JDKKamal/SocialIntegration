package com.jdkgroup.splashscreen;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jdkgroup.socialintegration.R;

import java.util.Timer;
import java.util.TimerTask;

public class MySplashScreen extends LinearLayout {
    AppCompatImageView imageView;
    TimeExecuteListener timeExecuteListener;

    public MySplashScreen(Context context) {
        super(context);
        initialize(context);
    }

    public MySplashScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public void setTimeExecuteListener(final TimeExecuteListener timeExecuteListener, long time) {
        this.timeExecuteListener = timeExecuteListener;
        if (time > 0) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    // this code will be executed after time millisecond
                    timeExecuteListener.onExecute();
                }
            }, time);
        }
    }

    private void initialize(Context context) {
        View view = inflate(context, R.layout.splash_screen, this);
        imageView = (AppCompatImageView) view.findViewById(R.id.splashImage);
    }

    public void setImageInImageView(int img_id) {
        imageView.setImageResource(img_id);
    }
}
