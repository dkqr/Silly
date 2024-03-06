package com.dkqr.sillyclient.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;

public class Frame extends Gui{
    public boolean open = false;
    public String text;
    public ArrayList<Option> children = new ArrayList<>();
    public Frame(int x1, int y1, int x2, int y2, int colour, String text) {
	    super(x1, y1, x2, y2, colour);
        this.text = text;
    }

    public void addChild(Option g) {
        this.children.add(g);
        super.addChild(g);
    }

    @Override
    public void onPress(int mouse) {
        if (mouse != GLFW.GLFW_MOUSE_BUTTON_2) return;
        open = !open;
        if (open) {
            for (Option g : this.children) {
                g.setEnabled(true);
            }
            this.y2 = this.y2 + 100;
        } else {
            for (Option g : this.children) {
                g.setEnabled(false);
            }
            this.y2 = this.y2 - 100;
        }
	    this.height = this.y2 - this.y1;
    }

    @Override
    public void draw(DrawContext context) {
        super.draw(context);
        context.drawText(MinecraftClient.getInstance().textRenderer, text, x1 + 7, y1 + 7, Color.WHITE.getRGB(), true);
    }

    public boolean isDraggable() {
        return true;
    }
}
