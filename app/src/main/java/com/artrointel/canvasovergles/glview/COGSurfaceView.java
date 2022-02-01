package com.artrointel.canvasovergles.glview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.View;

public class COGSurfaceView extends GLSurfaceView {

    public COGSurfaceRenderer mRenderer;

    public COGSurfaceView(Context context) {
        super(context);
        initialize(context);
    }

    public COGSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(final Context context) {
        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mRenderer = new COGSurfaceRenderer(context, getWidth(), getHeight());
                setRenderer(mRenderer);
            }
        });
    }

    public void setCanvasDrawingListener(COGSurfaceRenderer.CanvasDrawingListener listener) {
        mRenderer.setCanvasDrawingListener(listener);
    }
}
