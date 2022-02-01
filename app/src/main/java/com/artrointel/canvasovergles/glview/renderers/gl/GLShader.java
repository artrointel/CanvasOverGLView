package com.artrointel.canvasovergles.glview.renderers.gl;

import android.opengl.GLES20;
import android.opengl.GLES30;

import org.junit.Assert;

public class GLShader {
    final int mShaderId;
    final int mType;

    GLShader(int type, String shaderSource) {
        mType = type;
        mShaderId = createShader(type, shaderSource);
    }

    private static int createShader(int type, String shaderSource) {
        final int shaderId = GLES30.glCreateShader(type);
        Assert.assertTrue("Cannot create shader!", shaderId != 0);

        GLES30.glShaderSource(shaderId, shaderSource);
        GLES30.glCompileShader(shaderId);

        int[] status = new int[1];
        GLES30.glGetShaderiv(shaderId, GLES20.GL_COMPILE_STATUS, status, 0);
        Assert.assertTrue("Cannot compile shader : " + GLES30.glGetShaderInfoLog(shaderId), status[0] != 0);

        return shaderId;
    }
}
