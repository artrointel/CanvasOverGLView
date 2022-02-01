package com.artrointel.canvasovergles.glview.renderers.gl;

import android.opengl.GLES30;

import com.artrointel.canvasovergles.glview.utils.*;

import java.nio.Buffer;
import java.nio.FloatBuffer;

public class GLAttribute implements IGLBindable {
    public static final FloatBuffer BUFFER_POSITION_QUAD;
    public static final FloatBuffer BUFFER_UV_QUAD;

    static {
        final float QUAD_POSITION[] = {
                -1.f -1.f,
                -1.f, 1.f,
                1.f, -1.f,
                1.f,  1.f
        };

        final float QUAD_UV[] = {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f
        };

        BUFFER_POSITION_QUAD = DirectBuffer.AllocateFloatBuffer(QUAD_POSITION);
        BUFFER_UV_QUAD =  DirectBuffer.AllocateFloatBuffer(QUAD_UV);
    }

    public enum TYPE {
        VEC2,
    }

    TYPE mType;
    String mAttributeName;
    Buffer mData;

    private int mLocation;
    private int mSizeByType;
    private int mLength;

    public GLAttribute(TYPE type, String attrName, Buffer data) {
        mType = type;
        mAttributeName = attrName;
        mData = data;

        setup();
    }

    private void setup() {
        mLocation = GLES30.glGetAttribLocation(GLContext.get().getCurrentProgram().getId(), mAttributeName);

        switch (mType) {
            case VEC2:
                mSizeByType = 2;
                mLength = mData.capacity() / (4 * mSizeByType);
                break;
        }
    }

    public int getLength() {
        return mLength;
    }

    @Override
    public void bind() {
        GLES30.glEnableVertexAttribArray(mLocation);
        GLES30.glVertexAttribPointer(mLocation, mSizeByType, GLES30.GL_FLOAT, false, 4 * mSizeByType, mData);
    }
}
