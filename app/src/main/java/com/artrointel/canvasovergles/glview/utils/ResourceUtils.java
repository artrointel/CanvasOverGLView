package com.artrointel.canvasovergles.glview.utils;

import android.content.Context;
import android.content.res.Resources;

import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResourceUtils {
    private static Context sContext;

    public static void InitializeContext(Context context) {
        sContext = context;
    }

    private static void checkContext() {
        Assert.assertTrue("Not in android context", sContext != null);
    }

    public static String ReadStringFromResource(int resId) {
        checkContext();

        String str = "";
        try {
            Resources r = sContext.getResources();
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
            Assert.assertTrue(e.getMessage().toString(), false);
        }

        return str;
    }
}
