package com.artrointel.canvasovergles.glview.renderers.gl;

import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.view.Surface;

public class GLTextureExt extends GLTexture {

    SurfaceTexture mSurfaceTexture;
    Surface mSurface;
    Canvas mCanvas;

    public GLTextureExt(int width, int height) {
        super(width, height);
        mGLTextureType = GLES11Ext.GL_TEXTURE_EXTERNAL_OES;

        mSurfaceTexture = new SurfaceTexture(mTexId);
        mSurfaceTexture.setDefaultBufferSize(width, height);
        mSurface = new Surface(mSurfaceTexture);
    }

    public void unlockCanvas() {
        mSurface.unlockCanvasAndPost(mCanvas);
        mSurfaceTexture.updateTexImage();
    }

    public Canvas lockCanvas() {
        mCanvas = mSurface.lockHardwareCanvas();
        return mCanvas;
    }

}
