package com.artrointel.canvasovergles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.artrointel.canvasovergles.R;
import com.artrointel.canvasovergles.glview.COGSurfaceRenderer;
import com.artrointel.canvasovergles.glview.COGSurfaceView;

public class MainActivity extends AppCompatActivity {

    COGSurfaceView mCOGView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCOGView = findViewById(R.id.cogview);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.image, null);
        view.layout(0, 0, 1000, 1000);

        mCOGView.setCanvasDrawingListener(new COGSurfaceRenderer.CanvasDrawingListener() {
            @Override
            public void onDraw(Canvas canvas) {
                view.draw(canvas);
            }
        });

    }
}
