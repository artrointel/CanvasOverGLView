package com.artrointel.canvasoverglview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.artrointel.cog.glview.COGSurfaceView;

public class MainActivity extends AppCompatActivity {

    COGSurfaceView mCOGView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCOGView = findViewById(R.id.cogview);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final LinearLayout exampleViewContainer = (LinearLayout) inflater.inflate(
                R.layout.example_layout,
                findViewById(R.id.exampleView));

        // Your Business UI Logic here.
        final View imageView = exampleViewContainer.getChildAt(0);
        exampleViewContainer.setOnTouchListener((v, e) -> {
            if(e.getActionMasked() == MotionEvent.ACTION_MOVE) {
                int width = imageView.getWidth();
                int height = imageView.getHeight();
                int newX = (int)e.getX() - width/2;
                int newY = (int)e.getY() - height/2;
                imageView.layout(newX, newY, newX + width, newY + height);
            }
            return true;
        });

        // Run the view.draw(canvas) into the COGView
        mCOGView.setCanvasDrawing(canvas -> {
            if(canvas != null) {
                exampleViewContainer.draw(canvas);
            }
        });
    }
}
