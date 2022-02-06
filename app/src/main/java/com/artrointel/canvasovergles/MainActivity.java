package com.artrointel.canvasovergles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.artrointel.canvasovergles.glview.COGSurfaceView;

public class MainActivity extends AppCompatActivity {

    LinearLayout mSampleView;
    COGSurfaceView mCOGView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSampleView = (LinearLayout) findViewById(R.id.sampleView);
        mCOGView = findViewById(R.id.cogview);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.sample_layout, mSampleView);
        final View imageView = layout.getChildAt(0);


        layout.setOnTouchListener((v, e) -> {

            if(e.getActionMasked() == MotionEvent.ACTION_MOVE) {
                int width = imageView.getWidth();
                int height = imageView.getHeight();
                int newX = (int)e.getX() - width/2;
                int newY = (int)e.getY() - height/2;
                imageView.layout(newX, newY, newX + width, newY + height);
            }

            return true;
        }

        );

        mCOGView.setCanvasDrawingListener(canvas -> {
            layout.draw(canvas);
        });

    }
}
