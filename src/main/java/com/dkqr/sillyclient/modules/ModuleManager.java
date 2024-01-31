package com.dkqr.sillyclient.modules;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.modules.cheats.FlyHack;
import com.dkqr.sillyclient.modules.cheats.NoFallHack;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<>();
    public Module flyHack = new FlyHack("Fly Hack", Category.MOVEMENT, GLFW.GLFW_KEY_G);
    public Module noFallHack = new NoFallHack("NoFall Hack", Category.MOVEMENT, GLFW.GLFW_KEY_H);

    public ModuleManager() {
        addModule(flyHack);
        addModule(noFallHack);
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            int offset = 1;
            for (Module mod : modules) {
                if (mod.getState()) {
                    drawContext.drawText(SillyClient.client.textRenderer, mod.getName(), 1, offset, 0xffffff, false);
                    offset += 9;
                }
            }
        });
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            for (Module mod : modules) {
                if (mod.getKeybind().isPressed()) {
                    if (mod.keyPressed)
                        continue;
                    mod.keyPressed = true;
                    if (client.player != null) {
                        mod.toggle();
                        //client.player.sendMessage(Text.of("[" + mod.getName() + "] " + (mod.getState() ? "On" : "Off")));
                    }
                } else {
                    mod.keyPressed = false;
                }
                if (mod.getState()) {
                    mod.onTick();
                }
            }
        });
    }
    private void addModule(Module mod) {
        modules.add(mod);
    }
}
