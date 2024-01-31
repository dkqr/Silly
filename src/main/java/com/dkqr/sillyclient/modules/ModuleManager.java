package com.dkqr.sillyclient.modules;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.modules.cheats.FlyHack;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<>();
    private final Module flyHack = new FlyHack("Fly Hack", Category.MOVEMENT, GLFW.GLFW_KEY_G);
    public ModuleManager() {
        addModule(flyHack);

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            for (Module mod : modules) {
                mod.onTick();
                if (mod.getKeybind().isPressed()) {
                    if (mod.keyPressed)
                        continue;
                    mod.keyPressed = true;
                    if (client.player != null) {
                        mod.toggle();
                        client.player.sendMessage(Text.of("[" + mod.getName() + "] " + (mod.getState() ? "On" : "Off")));
                    }
                } else {
                    mod.keyPressed = false;
                }
            }
        });
    }
    private void addModule(Module mod) {
        modules.add(mod);
    }
}
