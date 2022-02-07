package com.artrointel.cog.api.renderers.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtils {
    private final static String TAG = ResourceUtils.class.getSimpleName();
    public static String ReadStringFromResource(Context context, int resId) {

        String str = "";
        try {
            Resources r = context.getResources();
            InputStream inputStream = r.openRawResource(resId);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            while (true) {
                int i = inputStream.read();
                if(i == -1) break;
                outputStream.write(i);
            }

            str = outputStream.toString();
            inputStream.close();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        return str;
    }
}
