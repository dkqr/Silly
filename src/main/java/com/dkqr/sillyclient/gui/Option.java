package com.dkqr.sillyclient.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class Option extends Gui {
	public boolean enabled = false;
	public String text;
	public Option(int x1, int y1, int x2, int y2, int colour, String text) {
		super(x1, y1, x2, y2, colour);
		this.text = text;
	}

	@Override
	public void draw(DrawContext context) {
		if (enabled) {
			context.drawText(MinecraftClient.getInstance().textRenderer, text, x1 + 7, y1 + 7, Color.WHITE.getRGB(), true);
			super.draw(context);
		}
	}

	public void setEnabled(boolean b) {
		this.enabled = b;
	}
}
