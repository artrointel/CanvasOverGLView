package com.artrointel.cog.api.renderers.gl;

import android.opengl.GLES30;
import android.util.Log;

public class GLShader {
    private static final String TAG = GLShader.class.getSimpleName();

    final int mShaderId;
    final int mType;

    GLShader(int type, String shaderSource) {
        mType = type;
        mShaderId = createShader(type, shaderSource);
    }

    private static int createShader(int type, String shaderSource) {
        final int shaderId = GLES30.glCreateShader(type);
        if(shaderId == 0) {
            Log.e(TAG,"Cannot create shader!");
        }

        GLES30.glShaderSource(shaderId, shaderSource);
        GLES30.glCompileShader(shaderId);

        int[] status = new int[1];
        GLES30.glGetShaderiv(shaderId, GLES30.GL_COMPILE_STATUS, status, 0);
        if(status[0] == 0) {
            Log.e(TAG,"Cannot compile shader : ");
        }

        return shaderId;
    }
}
