#version 320 es
// Use GL_OES_EGL_image_external_essl3 instead
#extension GL_OES_EGL_image_external : require
precision mediump float;

uniform samplerExternalOES uTexture;

in vec2 uUv;
in vec2 apos;

out vec4 fragColor;

void main() {
    //fragColor = texture(uTexture, uUv) * uColor;
    fragColor = texture(uTexture, uUv) + vec4(apos.xy, 0.0, 1.0);
}
