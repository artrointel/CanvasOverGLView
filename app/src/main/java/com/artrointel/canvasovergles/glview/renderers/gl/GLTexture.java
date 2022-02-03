package com.artrointel.canvasovergles.glview.renderers.gl;

import android.opengl.GLES11Ext;
import android.opengl.GLES30;
import android.opengl.GLES31;
import android.opengl.GLES31Ext;

import javax.microedition.khronos.opengles.GL10;

public class GLTexture {
    private final String mName;

    protected int mWidth;
    protected int mHeight;

    protected int mGLTextureType;
    protected int mTexId;

    public GLTexture(int width, int height, String uName) {
        mWidth = width;
        mHeight = height;
        mName = uName;

        mGLTextureType = GLES30.GL_TEXTURE;

        int[] id = new int[1];
        GLES30.glGenTextures(1, id, 0);
        mTexId = id[0];

        // TODO make separated
        GLES30.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, mTexId);
        GLES30.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLES30.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        GLES30.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_WRAP_S, GL10.GL_CLAMP_TO_EDGE);
        GLES30.glTexParameteri(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GL10.GL_TEXTURE_WRAP_T, GL10.GL_CLAMP_TO_EDGE);
    }

    String getName() {
        return mName;
    }

    int getType() {
        return mGLTextureType;
    }

    int getId() {
        return mTexId;
    }
}
