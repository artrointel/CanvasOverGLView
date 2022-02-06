package com.artrointel.cog.api.renderers.gl;

import android.opengl.GLES30;
import android.util.Log;

public class GLProgram {
    private static final String TAG = GLProgram.class.getSimpleName();
    private final GLShader mVertexShader;
    private final GLShader mFragmentShader;
    private int mProgramId;

    public GLProgram(String vertexShaderSource, String fragmentShaderSource) {

        mVertexShader = new GLShader(GLES30.GL_VERTEX_SHADER, vertexShaderSource);
        mFragmentShader = new GLShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderSource);

        mProgramId = GLES30.glCreateProgram();
        if(mProgramId == 0) {
            Log.e(TAG, "Cannot create program!");
        }

        GLES30.glAttachShader(mProgramId, mVertexShader.mShaderId);
        GLES30.glAttachShader(mProgramId, mFragmentShader.mShaderId);
        GLES30.glLinkProgram(mProgramId);
    }

    public void useProgram() {
        GLES30.glUseProgram(mProgramId);
    }

    public int getId() {
        return mProgramId;
    }
}
