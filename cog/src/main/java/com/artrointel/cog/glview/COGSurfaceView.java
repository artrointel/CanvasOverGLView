package com.artrointel.cog.glview;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import com.artrointel.cog.api.GLEventHandler;

public class COGSurfaceView extends GLSurfaceView {

    private COGSurfaceRenderer mRenderer;
    private COGSurfaceRenderer.CanvasDrawingDelegation mCanvasDrawing;

    public COGSurfaceView(Context context) {
        super(context);
        initialize(context);
    }

    public COGSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(final Context context) {
        setEGLContextClientVersion(3);
        addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            mRenderer = new COGSurfaceRenderer(context, new GLEventHandler() {
                @Override
                public void handleEvent(Runnable e) {
                    queueEvent(e);
                }
            }, getWidth(), getHeight());
            mRenderer.setCanvasDrawingDelegation(mCanvasDrawing);
            setRenderer(mRenderer);
            // TODO update it for android life-cycle
            setPreserveEGLContextOnPause(true);
            setRenderMode(RENDERMODE_CONTINUOUSLY);
        });
    }

    // TODO on surface destroyed
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

    public void setCanvasDrawing(COGSurfaceRenderer.CanvasDrawingDelegation canvasDrawing) {
        mCanvasDrawing = canvasDrawing;
    }
}
