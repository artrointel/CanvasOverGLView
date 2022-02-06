package com.artrointel.canvasovergles.glview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.View;

import com.artrointel.canvasovergles.glview.utils.GLEventHandler;

public class COGSurfaceView extends GLSurfaceView {

    public COGSurfaceRenderer mRenderer;
    private COGSurfaceRenderer.CanvasDrawingListener mListener;

    public COGSurfaceView(Context context) {
        super(context);
        initialize(context);
    }

    public COGSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(final Context context) {
        // TODO
        GLEventHandler.sView = this;

        setEGLContextClientVersion(3);
        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                mRenderer = new COGSurfaceRenderer(context, getWidth(), getHeight());
                mRenderer.setCanvasDrawingListener(mListener);
                setRenderer(mRenderer);
                // TODO update it for android life-cycle
                setPreserveEGLContextOnPause(true);
                setRenderMode(RENDERMODE_CONTINUOUSLY);
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setCanvasDrawingListener(COGSurfaceRenderer.CanvasDrawingListener listener) {
        mListener = listener;
    }
}
