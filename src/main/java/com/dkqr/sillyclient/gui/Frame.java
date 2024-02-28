package com.dkqr.sillyclient.gui;

public class Frame extends Gui{
    public Frame(int x1, int y1, int x2, int y2, int colour) {
        super(x1, y1, x2, y2, colour);
    }

    public boolean isDraggable() {
        return true;
    }
}
