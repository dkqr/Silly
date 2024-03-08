package com.dkqr.sillyclient.gui;

import com.dkqr.sillyclient.modules.Category;
import com.dkqr.sillyclient.modules.Module;
import com.dkqr.sillyclient.modules.ModuleManager;
import com.ibm.icu.text.ArabicShaping;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Frame extends Gui{
    public boolean open = false;
    public String text;
    public ArrayList<Option> children = new ArrayList<>();
    public Category cate;
    public Frame(int x1, int y1, int x2, int y2, int colour, String text, Category cate) {
	    super(x1, y1, x2, y2, colour);
        this.text = text;
        this.cate = cate;
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
            ArrayList<Module> mods = ModuleManager.modules;
            mods.removeIf(module -> module.getCategory() != cate);
            this.y2 = this.y2 + (mods.size() * 30);
        } else {
            for (Option g : this.children) {
                g.setEnabled(false);
            }
            ArrayList<Module> mods = ModuleManager.modules;
            mods.removeIf(module -> module.getCategory() != cate);
            this.y2 = this.y2 - (mods.size() * 30);
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
