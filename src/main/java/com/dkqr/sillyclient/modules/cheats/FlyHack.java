package com.dkqr.sillyclient.modules.cheats;

import com.dkqr.sillyclient.modules.Category;
import com.dkqr.sillyclient.modules.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class FlyHack extends Module {
    public FlyHack(String name, Category category, int keybind) {
        super(name, category, keybind);
    }
    public void onEnable() {
        if (client.player != null) {
            client.player.getAbilities().flying = true;
            client.player.getAbilities().allowFlying = true;
        }
    }
    public void onDisable() {
        if (client.player != null) {
            client.player.getAbilities().flying = false;
            client.player.getAbilities().allowFlying = false;
        }
    }
    public void onTick() {
        if (client.player != null) {
            client.player.getAbilities().allowFlying = true;
        }
    }
}
