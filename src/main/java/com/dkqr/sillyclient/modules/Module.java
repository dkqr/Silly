package com.dkqr.sillyclient.modules;

import com.dkqr.sillyclient.SillyClient;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public abstract class Module {
    private final String name;
    private final Category category;
    private final int keycode;
    private final KeyBinding keybind;
    private boolean enabled = false;
    public boolean keyPressed;
    protected MinecraftClient client = MinecraftClient.getInstance();
    public Module(String name, Category category, int keycode) {
        this.name = name;
        this.category = category;
        this.keycode = keycode;
        this.keybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                this.name,
                InputUtil.Type.KEYSYM,
                this.keycode,
                SillyClient.MODID
        ));
    }
    public String getName() {
        return name;
    }
    public Category getCategory() {
        return category;
    }
    public int getKeycode() {
        return keycode;
    }
    public KeyBinding getKeybind() {
        return keybind;
    }
    public boolean getState() {
        return enabled;
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
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
    public void onEnable() { }
    public void onDisable() { }
    public void onTick() { }

}
