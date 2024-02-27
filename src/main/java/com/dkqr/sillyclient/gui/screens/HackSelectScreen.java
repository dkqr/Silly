package com.dkqr.sillyclient.gui.screens;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.gui.Frame;
import com.dkqr.sillyclient.modules.Category;
import net.fabricmc.fabric.mixin.datagen.client.MinecraftClientMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class HackSelectScreen extends Screen {
    public HackSelectScreen(Text title) {
        super(title);
    }
    private int prevMouse;
    private int lastX;
    private int lastY;

    private ArrayList<Frame> frames = new ArrayList<>();
    @Override
    public void init() {
        /*int offset = 0;
        for (Module mod : ModuleManager.modules) {
            int x = offset % 2 == 0 ? width / 3 - 50 : width*2 / 3 - 50;
            //int y = (int) (Math.floor((double) offset /2) * height / 3) + 20;
            int y = 50 + (22 * (offset / 2));
            addDrawableChild(ButtonWidget.builder(Text.literal(mod.getName()), button -> {
                mod.toggle();
            }).dimensions(x, y, 100, 20).build());
            offset += 1;
        }*/
        for (Category cate : Category.values()) {
            int offset = (120 * cate.ordinal());
            frames.add(new Frame(40 + offset, 40, 120 + offset, 80, "A"));
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
            if (prevMouse == GLFW.GLFW_RELEASE) {
                for (Frame f : frames) {
                    if (mouseX >= f.x1 && mouseY >= f.y1 && mouseX <= f.x2 && mouseY <= f.y2) {
                        f.onPress();
                    }
                }
            } else {
                for (Frame f : frames) {
                    if (f.isDraggable()) {

                    }
                }
                lastX = mouseX;
                lastY = mouseY;
            }
        }
        prevMouse = GLFW.glfwGetMouseButton(client.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_1);
    }
}
