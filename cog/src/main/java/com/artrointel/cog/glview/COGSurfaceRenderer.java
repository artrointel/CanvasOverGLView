package com.artrointel.cog.glview;

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLSurfaceView;

import com.artrointel.cog.api.COGSurfaceEngine;
import com.artrointel.cog.api.GLEventHandler;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class COGSurfaceRenderer implements GLSurfaceView.Renderer {

    private Context mContext;
    private COGSurfaceEngine mCOGSurfaceEngine;
    private GLEventHandler mGLEventHandler;

    private int mCanvasBufferWidth;
    private int mCanvasBufferHeight;

    /**
     * Draw into the canvas to be rendered.
     */
    public interface CanvasDrawingDelegation {
        void onDraw(Canvas canvas);
    }

    private CanvasDrawingDelegation mCanvasDrawingDelegation;

    public COGSurfaceRenderer(Context context, GLEventHandler handler, int canvasWidth, int canvasHeight) {
        mContext = context;
        mGLEventHandler = handler;
        mCanvasBufferWidth = canvasWidth;
        mCanvasBufferHeight = canvasHeight;
    }

    public void setCanvasDrawingDelegation(CanvasDrawingDelegation listener) {
        mCanvasDrawingDelegation = listener;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        mCOGSurfaceEngine = COGSurfaceEngine.getInstance(Thread.currentThread(), mContext, mGLEventHandler, mCanvasBufferWidth, mCanvasBufferHeight);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        mCOGSurfaceEngine.updateViewPort(w, h);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        if(mCanvasDrawingDelegation != null) {
            mGLEventHandler.handleEvents();
            mCanvasDrawingDelegation.onDraw(mCOGSurfaceEngine.lockCanvas());
            mGLEventHandler.queueGLEvent(() -> mCOGSurfaceEngine.unlockCanvas());
        }

        mCOGSurfaceEngine.draw();
    }
}
