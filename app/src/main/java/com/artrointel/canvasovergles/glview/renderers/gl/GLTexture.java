package com.artrointel.canvasovergles.glview.renderers.gl;

import android.opengl.GLES30;

public class GLTexture {

    protected int mWidth;
    protected int mHeight;

    protected int mGLTextureType;
    protected int mTexId;

    public GLTexture(int width, int height) {
        mWidth = width;
        mHeight = height;
        mGLTextureType = GLES30.GL_TEXTURE;

        int[] id = new int[1];
        GLES30.glGenTextures(1, id, 0);
        mTexId = id[0];
    }

    int getType() {
        return mGLTextureType;
    }

    int getId() {
        return mTexId;
    }
}
