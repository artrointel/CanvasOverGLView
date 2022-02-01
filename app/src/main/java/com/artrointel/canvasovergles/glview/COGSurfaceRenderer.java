package com.artrointel.canvasovergles.glview;

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class COGSurfaceRenderer implements GLSurfaceView.Renderer {

    Context mContext;
    COGEngine mCOGEngine;

    int mCanvasBufferWidth;
    int mCanvasBufferHeight;

    public interface CanvasDrawingListener {
        void onDraw(Canvas canvas);
    }

    private CanvasDrawingListener mListener;

    public COGSurfaceRenderer(Context context, int canvasWidth, int canvasHeight) {
        mContext = context;
        mCanvasBufferWidth = canvasWidth;
        mCanvasBufferHeight = canvasHeight;
    }

    public void setCanvasDrawingListener(CanvasDrawingListener listener) {
        mListener = listener;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        mCOGEngine = new COGEngine(mContext, mCanvasBufferWidth, mCanvasBufferHeight);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        mCOGEngine.updateViewPort(w, h);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        if(mListener != null) {
            mListener.onDraw(mCOGEngine.lockCanvas());
            mCOGEngine.unlockCanvas();
        }

        mCOGEngine.draw();
    }
}
