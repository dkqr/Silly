package com.dkqr.sillyclient.modules;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.modules.cheats.FlyHack;
import com.dkqr.sillyclient.modules.cheats.KillAura;
import com.dkqr.sillyclient.modules.cheats.NoFallHack;
import com.dkqr.sillyclient.modules.cheats.SpeedHack;
import com.dkqr.sillyclient.gui.screens.HackSelectScreen;
import com.dkqr.sillyclient.util.ColourUtils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

public class ModuleManager {
    public static final ArrayList<Module> modules = new ArrayList<>();
    public Module flyHack = new FlyHack("Fly Hack", Category.MOVEMENT, GLFW.GLFW_KEY_G);
    public Module noFallHack = new NoFallHack("NoFall Hack", Category.MOVEMENT, GLFW.GLFW_KEY_H);
    public Module speedHack = new SpeedHack("Speed Hack", Category.MOVEMENT, GLFW.GLFW_KEY_C);
    public Module killAura = new KillAura("KillAura Hack", Category.COMBAT, GLFW.GLFW_KEY_X);
    public int curColour = 0xffffff;
    KeyBinding modOpen = KeyBindingHelper.registerKeyBinding(new KeyBinding("Open mod", GLFW.GLFW_KEY_RIGHT_SHIFT, SillyClient.MODID));

    public ModuleManager() {
        addModule(flyHack);
        addModule(noFallHack);
        addModule(speedHack);
        addModule(killAura);

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            curColour %= 360;
            curColour += 1;
            int offset = 2;
            for (Module mod : modules) {
                if (mod.getState()) {
                    //SillyClient.LOGGER.info(String.valueOf(ColourUtils.convertHSBToRGB(240, 1, 1)));
                    drawContext.drawText(SillyClient.client.textRenderer, mod.getName(), 2, offset, ColourUtils.convertHSBToRGB(curColour, 1, 1), true);
                    offset += 10;
                }
            }
        });
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (modOpen.isPressed())
                client.setScreen(new HackSelectScreen(Text.literal("Hack Select")));
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
                if (client.player == null) {
                    mod.setState(false);
                }
                if (mod.getState()) {
                    mod.onTick();
                }
            }
        });
    }
    private void addModule(Module mod) {
        modules.add(mod);
        modules.sort((module, t1) -> {
            int res = module.getName().compareTo(t1.getName());
            if (res < 0) {
                return -1;
            } else if (res == 0) {
                return 0;
            }
            return 1;
        });
    }
}
