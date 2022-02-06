package com.artrointel.cog.api;

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLES30;

import androidx.annotation.Nullable;

import com.artrointel.cog.api.renderers.CanvasRenderer;
import com.artrointel.cog.api.renderers.gl.GLTextureExt;

import java.util.HashMap;
import java.util.Map;

/**
 * COGEngine render canvas into the GL View, i.e. {@link android.opengl.GLSurfaceView} or {@link android.view.TextureView}.
 * COGEngine should be created on GL Thread.
 */
public class COGSurfaceEngine {
    private static Map<Thread, COGSurfaceEngine> sInstanceMap = new HashMap<>();
    private CanvasRenderer mCanvasRenderer;

    public static COGSurfaceEngine getInstance(Thread glThread, Context context, GLEventHandler handler, int canvasWidth, int canvasHeight) {
        if(!sInstanceMap.containsKey(glThread)) {
            sInstanceMap.put(glThread, new COGSurfaceEngine(glThread, context, handler, canvasWidth, canvasHeight));
        }
        return sInstanceMap.get(glThread);
    }

    private COGSurfaceEngine(Thread glThread, Context context, GLEventHandler handler, int canvasWidth, int canvasHeight) {
        sInstanceMap.put(glThread, this);

        GLES30.glViewport(0, 0, canvasWidth, canvasHeight);
        mCanvasRenderer = new CanvasRenderer(context, canvasWidth, canvasHeight);
        GLTextureExt texForCanvas = mCanvasRenderer.getTextureExtForCanvas();
        texForCanvas.setOnFrameAvailableListener( (surfaceTexture) -> {
            handler.queueGLEvent(() -> texForCanvas.updateTexImage());
        });
    }

    public void updateViewPort(int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    public void draw() {
        mCanvasRenderer.render();
    }

    public @Nullable
    Canvas lockCanvas() {
        return mCanvasRenderer.lockCanvas();
    }

    public void unlockCanvas() {
        mCanvasRenderer.unlockCanvas();
    }
}
