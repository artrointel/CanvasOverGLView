package com.artrointel.canvasovergles.glview.renderers.gl;

import android.opengl.GLES30;

import java.util.ArrayList;

public class GLTextureSet implements IGLBindable {
    private ArrayList<GLTexture> mTextures;

    public void add(GLTexture texture) {
        mTextures.add(texture);
    }

    @Override
    public void bind() {
        for(int i = 0; i < mTextures.size(); i++) {
            GLTexture texture = mTextures.get(i);
            GLES30.glActiveTexture(GLES30.GL_TEXTURE0 + i);
            GLES30.glBindTexture(texture.getType(), texture.getId());
        }
    }
}
