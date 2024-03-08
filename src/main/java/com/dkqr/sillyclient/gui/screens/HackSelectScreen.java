package com.dkqr.sillyclient.gui.screens;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.gui.Frame;
import com.dkqr.sillyclient.gui.Option;
import com.dkqr.sillyclient.modules.Category;
import com.dkqr.sillyclient.modules.Module;
import com.dkqr.sillyclient.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.Window;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;

public class HackSelectScreen extends Screen {
    public HackSelectScreen(Text title) {
        super(title);
    }
    private int prevMouse1,prevMouse2;
    private int lastX;
    private int lastY;
    private boolean curDrag;

    private ArrayList<Frame> frames = new ArrayList<>();
    @Override
    public void onDisplayed() {
        frames.clear();
        for (Category cate : Category.values()) {
            int offset = (120 * cate.ordinal());
            Frame f = new Frame(40 + offset, 40, 120 + offset, 70, Color.GRAY.getRGB(), cate.name(), cate);
            int oOffset = 30;
            for (Module m : ModuleManager.modules) {
                if (m.getCategory() == cate) {
                    f.addChild(new Option(f.x1, f.y1 + oOffset, f.x2, f.y2 + oOffset, m.getState() ? Color.GRAY.getRGB() : Color.BLACK.getRGB(), m.getName()));
                    oOffset += 30;
                }
            }
            frames.add(f);
        }
    }

    public void tick() {
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        for (Frame f : frames) {
            f.draw(context);
        }
        assert client != null;
        if (GLFW.glfwGetMouseButton(client.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS) {
            if (prevMouse1 == GLFW.GLFW_RELEASE) {
                for (Frame f : frames) {
                    if (mouseX >= f.x1 && mouseY >= f.y1 && mouseX <= f.x2 && mouseY <= f.y2) {
                        f.onPress(GLFW.GLFW_MOUSE_BUTTON_1);
                    }
                }
            } else {
                for (Frame f : frames) {
                    if (f.isDraggable() && f.dragging) {
                        Window win = MinecraftClient.getInstance().getWindow();
                        int xOffset = f.x1 + (mouseX - lastX);
                        if (xOffset < 0) {
                            xOffset = 0;
                        } else if (xOffset > win.getWidth()/2-f.width) {
                            xOffset = win.getWidth()/2-f.width;
                        }
                        f.setX(xOffset);
                        int yOffset = f.y1 + (mouseY - lastY);
                        if (yOffset < 0) {
                            yOffset = 0;
                        } else if (yOffset > win.getHeight()/2-f.height) {
                            yOffset = win.getHeight()/2-f.height;
                        }
                        f.setY(yOffset);
                        break;
                    } else if (mouseX >= f.x1 && mouseY >= f.y1 && mouseX <= f.x2 && mouseY <= f.y2 && !curDrag) {
                        f.dragging = true;
                        curDrag = true;
                        break;
                    }
                }
                lastX = mouseX;
                lastY = mouseY;
            }
        } else {
            for (Frame f : frames) {
                f.dragging = false;
                curDrag = false;
            }
        }
        if (GLFW.glfwGetMouseButton(client.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_2) == GLFW.GLFW_PRESS) {
            if (prevMouse2 == GLFW.GLFW_RELEASE) {
                for (Frame f : frames) {
                    if (f.dragging) {
                        f.onPress(GLFW.GLFW_MOUSE_BUTTON_2);
                    } else if (mouseX >= f.x1 && mouseY >= f.y1 && mouseX <= f.x2 && mouseY <= f.y2){
                        f.onPress(GLFW.GLFW_MOUSE_BUTTON_2);
                    }
                }
            }
        }
        prevMouse1 = GLFW.glfwGetMouseButton(client.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_1);
        prevMouse2 = GLFW.glfwGetMouseButton(client.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_2);
    }
}
