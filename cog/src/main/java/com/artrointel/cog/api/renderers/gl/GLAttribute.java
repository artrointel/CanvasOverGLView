package com.artrointel.cog.api.renderers.gl;

import android.opengl.GLES30;

import com.artrointel.cog.api.renderers.utils.*;

import java.nio.Buffer;
import java.nio.FloatBuffer;

public class GLAttribute implements IGLBindable {
    public static final FloatBuffer BUFFER_POSITION_QUAD;
    public static final FloatBuffer BUFFER_POSITION_QUAD_FLIP;
    public static final FloatBuffer BUFFER_UV_QUAD;

    static {
        final float QUAD_POSITION[] = {
                -1.f, -1.f,
                -1.f, 1.f,
                1.f, -1.f,
                1.f,  1.f
        };

        final float QUAD_POSITION_FLIP[] = {
                -1.f, 1.f,
                -1.f, -1.f,
                1.f, 1.f,
                1.f, -1.f
        };

        final float QUAD_UV[] = {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 0.0f,
                1.0f, 1.0f
        };

        BUFFER_POSITION_QUAD = DirectBuffer.AllocateFloatBuffer(QUAD_POSITION);
        BUFFER_POSITION_QUAD_FLIP = DirectBuffer.AllocateFloatBuffer(QUAD_POSITION_FLIP);
        BUFFER_UV_QUAD =  DirectBuffer.AllocateFloatBuffer(QUAD_UV);
    }

    public enum TYPE {
        VEC2,
    }

    private TYPE mType;
    private String mAttributeName;
    private Buffer mData;

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
    public void bind(GLProgram program) {
        mLocation = GLES30.glGetAttribLocation(program.getId(), mAttributeName);
        GLES30.glEnableVertexAttribArray(mLocation);
        GLES30.glVertexAttribPointer(mLocation, mSizeByType, GLES30.GL_FLOAT, false, 0, mData);
    }
}
