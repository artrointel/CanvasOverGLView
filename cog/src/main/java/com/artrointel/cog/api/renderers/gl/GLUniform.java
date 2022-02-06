package com.artrointel.cog.api.renderers.gl;

import android.opengl.GLES30;
import android.util.Log;

import com.artrointel.cog.api.renderers.utils.ResourceUtils;

import java.util.function.Consumer;

public class GLUniform implements IGLBindable {
    private final static String TAG = GLUniform.class.getSimpleName();

    public enum TYPE {
        FLOAT,
        VEC2,
        VEC4,
    }

    private TYPE mType;
    private String mName;
    private Object mData;

    private int mLocation;
    private Consumer mUniformSetter;

    public GLUniform (TYPE type, String uName, Object data) {
        mType = type;
        mName = uName;
        mData = data;

        setup();
    }

    void setup() {
        switch (mType) {
            case FLOAT:
                mUniformSetter = loc -> GLES30.glUniform1f((Integer)loc, (Float) mData);
                break;
            case VEC2:
                mUniformSetter = loc -> {
                    Float[] d = (Float[]) mData;
                    GLES30.glUniform2f((Integer)loc, d[0], d[1]);
                };
                break;
            case VEC4:
                mUniformSetter = o -> {
                    Float[] d = (Float[]) mData;
                    GLES30.glUniform4f(mLocation, d[0], d[1], d[2], d[3]);
                };
                break;
            default:
                Log.e(TAG, "Unsupported data type");
                break;
        }
    }

    @Override
    public void bind(GLProgram program) {
        mLocation = GLES30.glGetUniformLocation(program.getId(), mName);
        mUniformSetter.accept(null);
    }
}
