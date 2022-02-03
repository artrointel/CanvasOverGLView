package com.artrointel.canvasovergles.glview.renderers.gl;

import android.opengl.GLES30;

import java.util.ArrayList;

public class GLTextureSet implements IGLBindable {
    private class GLTextureBinder {
        GLTexture texture;
        int location = -1;
        GLTextureBinder(GLTexture texture) {
            this.texture = texture;
        }
    }
    private ArrayList<GLTextureBinder> mTextures= new ArrayList<>();
    private GLProgram mTargetProgram;

    public void add(GLTexture texture) {
        mTextures.add(new GLTextureBinder(texture));
    }

    public void setProgramToBind(GLProgram program) {
        mTargetProgram = program;
    }

    @Override
    public void bind() {
        for(int i = 0; i < mTextures.size(); i++) {
            GLTextureBinder texBinder = mTextures.get(i);
            GLTexture texture = mTextures.get(i).texture;
            GLES30.glActiveTexture(GLES30.GL_TEXTURE0 + i);
            GLES30.glBindTexture(texture.getType(), texture.getId());
            if(texBinder.location == -1) {
                texBinder.location = GLES30.glGetUniformLocation(mTargetProgram.getId(), texture.getName());
            }
            GLES30.glUniform1i(texBinder.location, 0);
        }
    }

}
