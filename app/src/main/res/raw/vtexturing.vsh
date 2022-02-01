#version 320 es

in vec2 aPos;
in vec2 aTexCoord;

out vec2 uUv;

void main() {
    uUv = aTexCoord;
    gl_Position = vec4(aPos, 0.0, 1.0);
}