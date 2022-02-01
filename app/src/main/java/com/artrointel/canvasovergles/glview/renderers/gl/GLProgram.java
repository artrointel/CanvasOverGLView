package com.artrointel.canvasovergles.glview.renderers.gl;

import android.opengl.GLES30;

import org.junit.Assert;

public class GLProgram {
    GLShader mVertexShader;
    GLShader mFragmentShader;
    int mProgramId;

    // creates shader
    // creates program

    public GLProgram(String vertexShaderSource, String fragmentShaderSource) {

        mVertexShader = new GLShader(GLES30.GL_VERTEX_SHADER, vertexShaderSource);
        mFragmentShader = new GLShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderSource);

        mProgramId = GLES30.glCreateProgram();
        Assert.assertTrue("Cannot create program!", mProgramId != 0);

        GLES30.glAttachShader(mProgramId, mVertexShader.mShaderId);
        GLES30.glAttachShader(mProgramId, mFragmentShader.mShaderId);
        GLES30.glLinkProgram(mProgramId);
    }

    public void useProgram() {
        GLContext.get().setCurrentProgram(this);
        GLES30.glUseProgram(mProgramId);
    }

    int getId() {
        return mProgramId;
    }
}
