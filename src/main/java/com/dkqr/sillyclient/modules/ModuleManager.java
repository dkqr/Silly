package com.dkqr.sillyclient.modules;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.modules.cheats.FlyHack;
import com.dkqr.sillyclient.modules.cheats.NoFallHack;
import com.dkqr.sillyclient.modules.cheats.SpeedHack;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class ModuleManager {
    public static final ArrayList<Module> modules = new ArrayList<>();
    public Module flyHack = new FlyHack("Fly Hack", Category.MOVEMENT, GLFW.GLFW_KEY_G);
    public Module noFallHack = new NoFallHack("NoFall Hack", Category.MOVEMENT, GLFW.GLFW_KEY_H);
    public Module speedHack = new SpeedHack("Speed Hack", Category.MOVEMENT, GLFW.GLFW_KEY_C);

    public ModuleManager() {
        addModule(flyHack);
        addModule(noFallHack);
        addModule(speedHack);

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            int offset = 2;
            for (Module mod : modules) {
                if (mod.getState()) {
                    drawContext.drawText(SillyClient.client.textRenderer, mod.getName(), 2, offset, 0xffffff, true);
                    offset += 10;
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
