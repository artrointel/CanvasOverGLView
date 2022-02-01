package com.artrointel.canvasovergles.glview.renderers;

import android.graphics.Canvas;
import android.opengl.GLES30;

import com.artrointel.canvasovergles.R;
import com.artrointel.canvasovergles.glview.renderers.gl.GLAttribute;
import com.artrointel.canvasovergles.glview.renderers.gl.GLProgram;
import com.artrointel.canvasovergles.glview.renderers.gl.GLTextureExt;
import com.artrointel.canvasovergles.glview.renderers.gl.GLTextureSet;
import com.artrointel.canvasovergles.glview.renderers.gl.GLUniform;
import com.artrointel.canvasovergles.glview.utils.ResourceUtils;

public class CanvasRenderer implements Renderer {

    GLProgram mProgram;
    GLAttribute mPosition;
    GLAttribute mUV;
    GLUniform mColor;
    GLTextureExt mTextureExt;
    GLTextureSet mTextures;

    public static Float COLOR_WHITE[] = {
            1.0f, 0.0f, 0.0f, 1.0f
    };

    public CanvasRenderer(int canvasWidth, int canvasHeight) {
        mProgram = new GLProgram(
                ResourceUtils.ReadStringFromResource(R.raw.vtexturing),
                ResourceUtils.ReadStringFromResource(R.raw.ftexturing));
        mProgram.useProgram();

        mPosition = new GLAttribute(GLAttribute.TYPE.VEC2, "aPos", GLAttribute.BUFFER_POSITION_QUAD);
        mUV = new GLAttribute(GLAttribute.TYPE.VEC2, "aUV", GLAttribute.BUFFER_UV_QUAD);

        mTextureExt = new GLTextureExt(canvasWidth, canvasHeight);
        mTextures.add(mTextureExt);

        mColor = new GLUniform(GLUniform.TYPE.VEC4, "uColor", COLOR_WHITE);
    }

    @Override
    public void render() {
        mProgram.useProgram();

        mPosition.bind();
        mUV.bind();
        mColor.bind();
        mTextures.bind();

        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 4);
    }

    public void unlockCanvas() {
        mTextureExt.unlockCanvas();
    }

    public Canvas lockCanvas() {
        return mTextureExt.lockCanvas();
    }
}
