package com.artrointel.cog.api.renderers;

import android.content.Context;
import android.graphics.Canvas;
import android.opengl.GLES30;

import androidx.annotation.Nullable;

import com.artrointel.cog.R;
import com.artrointel.cog.api.renderers.gl.GLAttribute;
import com.artrointel.cog.api.renderers.gl.GLProgram;
import com.artrointel.cog.api.renderers.gl.GLTextureExt;
import com.artrointel.cog.api.renderers.gl.GLTextureSet;
import com.artrointel.cog.api.renderers.gl.GLUniform;
import com.artrointel.cog.api.renderers.utils.ResourceUtils;

public class CanvasRenderer implements Renderer {

    private GLProgram mProgram;
    private GLAttribute mPosition;
    private GLAttribute mTexCoord;
    private GLTextureExt mTextureExt;
    private GLTextureSet mTextures;

    public CanvasRenderer(Context context, int canvasWidth, int canvasHeight) {
        mProgram = new GLProgram(
                ResourceUtils.ReadStringFromResource(context, R.raw.vtexturing),
                ResourceUtils.ReadStringFromResource(context, R.raw.ftexturing));
        mProgram.useProgram();

        mPosition = new GLAttribute(GLAttribute.TYPE.VEC2, "aPos", GLAttribute.BUFFER_POSITION_QUAD_FLIP);
        mTexCoord = new GLAttribute(GLAttribute.TYPE.VEC2, "aTexCoord", GLAttribute.BUFFER_UV_QUAD);
        mTextureExt = new GLTextureExt(canvasWidth, canvasHeight, "uTexture");
        mTextures = new GLTextureSet();
        mTextures.add(mTextureExt);
    }

    @Override
    public void render() {
        mProgram.useProgram();

        mPosition.bind(mProgram);
        mTexCoord.bind(mProgram);
        mTextures.bind(mProgram);

        GLES30.glClearColor(.3f, .3f, .3f, 1.0f);
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 4);
    }

    public GLTextureExt getTextureExtForCanvas() {
        return mTextureExt;
    }

    public void unlockCanvas() {
        mTextureExt.unlockCanvas();
    }

    @Nullable
    public Canvas lockCanvas() {
        return mTextureExt.lockCanvas();
    }

}
