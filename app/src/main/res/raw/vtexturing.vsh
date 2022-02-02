#version 320 es

in vec2 aPos;
in vec2 aTexCoord;

out vec2 uUv;
out vec2 apos;

void main() {
    uUv = aTexCoord;
    apos = aPos;
    gl_Position = vec4(aPos, 0.0, 1.0);
}
