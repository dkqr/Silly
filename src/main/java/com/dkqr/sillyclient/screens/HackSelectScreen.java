package com.dkqr.sillyclient.screens;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.modules.Module;
import com.dkqr.sillyclient.modules.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class HackSelectScreen extends Screen {
    public HackSelectScreen(Text title) {
        super(title);
    }
    @Override
    public void init() {
        int offset = 0;
        for (Module mod : ModuleManager.modules) {
            int x = offset % 2 == 0 ? width / 3 - 50 : width*2 / 3 - 50;
            //int y = (int) (Math.floor((double) offset /2) * height / 3) + 20;
            int y = 50 + (22 * (offset / 2));
            addDrawableChild(ButtonWidget.builder(Text.literal(mod.getName()), button -> {
                mod.toggle();
            }).dimensions(x, y, 100, 20).build());
            offset += 1;
        }
    }
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        renderBackground(context);
    }
}
