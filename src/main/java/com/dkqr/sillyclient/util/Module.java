package com.dkqr.sillyclient.util;

import net.minecraft.client.MinecraftClient;

public abstract class Module {
    private final String name;
    private final Category category;
    private final int keybind;
    private boolean enabled = false;
    protected MinecraftClient client = MinecraftClient.getInstance();
    public Module(String name, Category category, int keybind) {
        this.name = name;
        this.category = category;
        this.keybind = keybind;
    }
    public String getName() {
        return name;
    }
    public Category getCategory() {
        return category;
    }
    public int getKeybind() {
        return keybind;
    }
    public void toggle() {
        enabled = !enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
    public void setState(boolean state) {
        if (enabled == state)
            return;
        enabled = state;
    }
    public void onEnable() { }
    public void onDisable() { }
    public void onTick() { }

}
