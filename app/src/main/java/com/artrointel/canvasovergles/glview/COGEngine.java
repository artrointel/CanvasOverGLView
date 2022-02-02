package com.artrointel.canvasovergles.glview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.artrointel.canvasovergles.glview.renderers.CanvasRenderer;
import com.artrointel.canvasovergles.glview.renderers.gl.GLContext;
import com.artrointel.canvasovergles.glview.utils.ResourceUtils;

// thread safe
public class COGEngine {
    private GLContext mGLContext;
    private CanvasRenderer mCanvasRenderer;

    public COGEngine(Context context, int canvasWidth, int canvasHeight) {
        initialize(context, canvasWidth, canvasHeight);
    }

    public void initialize(Context context, int canvasWidth, int canvasHeight) {
        ResourceUtils.InitializeContext(context);

        mGLContext = GLContext.InitializeGLContext(Thread.currentThread());
        mGLContext.setViewPort(canvasWidth, canvasHeight);
        mCanvasRenderer = new CanvasRenderer(canvasWidth, canvasHeight);
    }

    void updateViewPort(int width, int height) {
        mGLContext.setViewPort(width, height);
    }

    void draw() {
        mCanvasRenderer.render();
    }

    Canvas lockCanvas() {
        return mCanvasRenderer.lockCanvas();
    }

    void unlockCanvas() {
        mCanvasRenderer.unlockCanvas();
    }
}
