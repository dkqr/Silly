package com.dkqr.sillyclient.gui;

import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class Gui {
    public int x1,y1,x2,y2,colour;
    public int width,height;
    public Gui(int x1, int y1, int x2, int y2, int colour) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.colour = colour;
        this.width = x2 - x1;
        this.height = y2 - y1;
    }

    public void setX() {

    }

    public void onPress() {

    }
    public void draw(DrawContext context) {
        context.fill(x1,y1,x2,y2, colour);
    }
}
