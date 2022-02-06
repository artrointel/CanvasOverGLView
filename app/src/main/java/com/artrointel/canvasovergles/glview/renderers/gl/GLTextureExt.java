package com.artrointel.canvasovergles.glview.renderers.gl;

import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.opengl.GLES11Ext;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import com.artrointel.canvasovergles.glview.utils.GLEventHandler;

public class GLTextureExt extends GLTexture {

    private SurfaceTexture.OnFrameAvailableListener mSurfaceTextureListener;

    SurfaceTexture mSurfaceTexture;
    Surface mSurface;
    Canvas mCanvas;

    public GLTextureExt(int width, int height, String uName) {
        super(width, height, uName);
        mGLTextureType = GLES11Ext.GL_TEXTURE_EXTERNAL_OES;

        mSurfaceTexture = new SurfaceTexture(mTexId);
        mSurfaceTexture.setDefaultBufferSize(width, height);
        mSurface = new Surface(mSurfaceTexture);
    }

    public void unlockCanvas() {
        mSurface.unlockCanvasAndPost(mCanvas);
        mSurfaceTexture.updateTexImage(); // TODO
    }

    public Canvas lockCanvas() {
        mCanvas = mSurface.lockHardwareCanvas();
        if(mSurfaceTextureListener == null) {
            mSurfaceTextureListener = new SurfaceTexture.OnFrameAvailableListener() {
                @Override
                public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                    // TODO make it work in gl thread event. SurfaceView.queueEvent
                    if(!surfaceTexture.isReleased())
                    {
                        new GLEventHandler().queueEvent(() -> {
                            surfaceTexture.updateTexImage();
                        });
                    }

                }
            };
            mSurfaceTexture.setOnFrameAvailableListener(mSurfaceTextureListener);
        }
        return mCanvas;
    }

}
