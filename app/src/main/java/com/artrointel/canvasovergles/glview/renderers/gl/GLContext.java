package com.artrointel.canvasovergles.glview.renderers.gl;

import android.graphics.Point;
import android.opengl.GLES30;
import android.util.Log;

import org.junit.Assert;

public class GLContext {
    public static final String LOG_TAG = "GLContext";
    // TODO make nonstatic
    private static GLContext sContext;

    private static Thread sGLThread;
    private static GLProgram sCurrentProgram;

    private static Point mViewPort = new Point();

    public static GLContext InitializeGLContext(Thread currThread) {
        Assert.assertTrue("GLContext is already initialized.", sContext == null);
        sGLThread = currThread;
        return sContext = new GLContext();
    }

    static GLContext get() {
        Assert.assertTrue("Not initialized gl context.", sContext != null);
        return sContext;
    }

    public void setViewPort(int width, int height) {
        mViewPort.x = width;
        mViewPort.y = height;
        GLES30.glViewport(0, 0, width, height);
    }

    public static final void checkGLError() {
        Assert.assertTrue("Invalid GL Thread!", Thread.currentThread() == sGLThread);
        int error = 0;
        while((error = GLES30.glGetError()) != GLES30.GL_NO_ERROR) {
            Log.e(LOG_TAG, "GL Error: " + error);
        }
    }

    protected void setCurrentProgram(GLProgram program) {
        sCurrentProgram = program;
    }

    protected GLProgram getCurrentProgram() {
        return sCurrentProgram;
    }
}
