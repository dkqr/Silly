package com.dkqr.sillyclient.modules.cheats;

import com.dkqr.sillyclient.modules.Category;
import com.dkqr.sillyclient.modules.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFallHack extends Module {
    public NoFallHack(String name, Category category, int keycode) {
        super(name, category, keycode);
    }
    @Override
    public void onTick() {
        if (client.player != null && client.player.fallDistance > 2f)
            client.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.OnGroundOnly(true));
    }
}
