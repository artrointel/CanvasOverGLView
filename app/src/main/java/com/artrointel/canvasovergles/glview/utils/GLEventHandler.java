package com.artrointel.canvasovergles.glview.utils;

import android.opengl.GLSurfaceView;

import androidx.annotation.MainThread;

public class GLEventHandler {

    public static GLSurfaceView sView;

    public void queueEvent(Runnable glEvent) {
        sView.queueEvent(glEvent);
    }
}
