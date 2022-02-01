package com.artrointel.canvasovergles.glview.renderers.gl;

import android.opengl.GLES30;

import org.junit.Assert;

import java.util.function.Consumer;

public class GLUniform implements IGLBindable {
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
        mLocation = GLES30.glGetUniformLocation(GLContext.get().getCurrentProgram().getId(), mName);

        switch (mType) {
            case FLOAT:
                mUniformSetter = new Consumer() {
                    @Override
                    public void accept(Object loc) {
                        GLES30.glUniform1f((Integer)loc, (Float) mData);
                    }
                };
                break;
            case VEC2:
                mUniformSetter = new Consumer() {
                    @Override
                    public void accept(Object loc) {
                        Float[] d = (Float[]) mData;
                        GLES30.glUniform2f((Integer)loc, d[0], d[1]);
                    }
                };
                break;
            case VEC4:
                mUniformSetter = new Consumer() {
                    @Override
                    public void accept(Object o) {
                        Float[] d = (Float[]) mData;
                        GLES30.glUniform4f(mLocation, d[0], d[1], d[2], d[3]);
                    }
                };
                break;
            default:
                Assert.assertTrue("Invalid type.", false);
                break;
        }
    }

    @Override
    public void bind() {
        mUniformSetter.accept(null);
    }
}
