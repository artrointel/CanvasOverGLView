package com.artrointel.canvasovergles;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.os.Bundle;
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

        final TextView textView = new TextView(getApplicationContext());
        textView.layout(0, 0, 200, 200);
        textView.setText("Canvas Over GLES");
        mCOGView.setCanvasDrawingListener(new COGSurfaceRenderer.CanvasDrawingListener() {
            @Override
            public void onDraw(Canvas canvas) {
                textView.draw(canvas);
            }
        });

    }
}
