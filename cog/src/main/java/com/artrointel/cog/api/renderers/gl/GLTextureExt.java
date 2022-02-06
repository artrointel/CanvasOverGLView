package com.artrointel.cog.api.renderers.gl;

import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.view.Surface;

import androidx.annotation.AnyThread;
import androidx.annotation.Nullable;

public class GLTextureExt extends GLTexture {
    SurfaceTexture mSurfaceTexture;
    Surface mSurface;
    Canvas mCanvas;

    private Boolean mLock = new Boolean(false);

    public GLTextureExt(int width, int height, String uName) {
        super(width, height, uName);
        mGLTextureType = GLES11Ext.GL_TEXTURE_EXTERNAL_OES;

        mSurfaceTexture = new SurfaceTexture(mTexId);
        mSurfaceTexture.setDefaultBufferSize(width, height);
        mSurface = new Surface(mSurfaceTexture);
    }

    @AnyThread
    public void unlockCanvas() {
        synchronized (mLock) {
            if(mLock) {
                mLock = false;
                mSurface.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    @AnyThread
    @Nullable
    public Canvas lockCanvas() {
        synchronized (mLock) {
            if(mLock) {
                return null;
            }
            mLock = true;
            mCanvas = mSurface.lockHardwareCanvas();
            return mCanvas;
        }
    }

    public void updateTexImage() {
        mSurfaceTexture.updateTexImage();
    }

    public void setOnFrameAvailableListener(SurfaceTexture.OnFrameAvailableListener listener) {
        mSurfaceTexture.setOnFrameAvailableListener(listener);
    }
}
