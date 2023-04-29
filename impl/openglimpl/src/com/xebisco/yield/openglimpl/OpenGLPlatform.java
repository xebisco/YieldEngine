/*
 * Copyright [2022-2023] [Xebisco]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xebisco.yield.openglimpl;

import com.xebisco.yield.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class OpenGLPlatform implements PlatformGraphics, FontLoader {

    private long windowID = -1;
    private static boolean initiatedGLFW, setupThread = true;
    private PlatformInit platformInit;

    @Override
    public void dispose() {

    }

    @Override
    public void init(PlatformInit platformInit) {
        this.platformInit = platformInit;
        GLFWErrorCallback.createPrint(System.err).set();

        if (!initiatedGLFW && !glfwInit()) {
            initiatedGLFW = true;
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        updateWindow();
    }

    public void updateWindow() {
        if (windowID != -1)
            glfwDestroyWindow(windowID);
        long monitor = NULL;
        if (platformInit.isFullscreen())
            monitor = glfwGetPrimaryMonitor();
        windowID = glfwCreateWindow((int) platformInit.getWindowSize().getWidth(), (int) platformInit.getWindowSize().getHeight(), platformInit.getTitle(), monitor, NULL);
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(windowID, pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            assert vidmode != null;
            glfwSetWindowPos(
                    windowID,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }
        if (platformInit.isVerticalSync())
            glfwSwapInterval(1);

        glfwShowWindow(windowID);
    }

    @Override
    public void frame() {
        if (setupThread) {
            glfwMakeContextCurrent(windowID);
            GL.createCapabilities();
        }
    }

    @Override
    public void draw(DrawInstruction drawInstruction) {

    }

    @Override
    public void resetRotation() {

    }

    @Override
    public boolean shouldClose() {
        if (windowID == -1) return false;
        return glfwWindowShouldClose(windowID);
    }

    @Override
    public void conclude() {

    }

    @Override
    public Vector2D getCamera() {
        return null;
    }

    @Override
    public void setCamera(Vector2D camera) {

    }

    @Override
    public Object loadFont(Font font) {
        return null;
    }

    @Override
    public void unloadFont(Font font) {

    }

    @Override
    public double getStringWidth(String text, Object fontRef) {        //TODO
            float length = 0f;
            for (int i = 0; i < text.length(); i++) {
                IntBuffer advancewidth = BufferUtils.createIntBuffer(1);
                IntBuffer leftsidebearing = BufferUtils.createIntBuffer(1);
                STBTruetype.stbtt_GetCodepointHMetrics((STBTTFontinfo) fontRef, text.charAt(i), advancewidth, leftsidebearing);
                length += advancewidth.get(0);
            }
            return length;
    }

    @Override
    public double getStringHeight(String text, Object fontRef) {

        //TODO
        return ((STBTTFontinfo) fontRef).sizeof();
    }
}
