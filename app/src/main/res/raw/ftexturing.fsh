#version 320 es
#extension GL_OES_EGL_image_external : require
precision mediump float;

uniform samplerExternalOES uTexture;
uniform vec4 uColor;

in vec2 uUv;

out float fragColor;

void main() {
    fragColor = texture(uTexture, uUv) * uColor;
}