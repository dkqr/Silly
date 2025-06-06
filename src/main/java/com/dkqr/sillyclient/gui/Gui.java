package com.dkqr.sillyclient.gui;

import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;

public class Gui {
    public int x1,y1,x2,y2,colour;
    public int width,height;
    public boolean dragging = false;
    public int yOffset=0, xOffset=0;
    protected ArrayList<Gui> children = new ArrayList<>();
    protected Gui parent;
    public Gui(int x1, int y1, int x2, int y2, int colour) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.colour = colour;
        this.width = x2 - x1;
        this.height = y2 - y1;
    }

    public void setX(int x) {
        this.x1 = x;
        this.x2 = x + width;
        for (Gui c : children) {
            c.setX(x + c.xOffset);
        }
    }
    public void setY(int y) {
        this.y1 = y;
        this.y2 = y + height;
        for (Gui c : children) {
            c.setY(y + c.yOffset);
        }
    }
    public void setParent(Gui gui) {
        yOffset = this.y1 - gui.y1;
        xOffset = this.x1 - gui.x1;
        parent = gui;
    }
    public void addChild(Gui g) {
        g.setParent(this);
        children.add(g);
    }
    public ArrayList<Gui> getChildren() {
        return children;
    }

    public void onPress(int mouse) {

    }
    public boolean isDraggable() {
        return false;
    }
    public void draw(DrawContext context) {
        context.fill(x1,y1,x2,y2, colour);
        for (Gui g : getChildren()) {
            g.draw(context);
        }
    }
}
