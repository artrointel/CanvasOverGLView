package com.artrointel.canvasovergles.glview.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class DirectBuffer {

    public static FloatBuffer AllocateFloatBuffer(float[] data) {
        return AllocateFloatBuffer(data.length * 4, data);
    }

    public static FloatBuffer AllocateFloatBuffer(int length, float[] data) {
        FloatBuffer ret = ByteBuffer.allocateDirect(length)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        ret.put(data);
        ret.position(0);
        return ret;
    }
}
