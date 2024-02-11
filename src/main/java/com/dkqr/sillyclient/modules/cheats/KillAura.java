package com.dkqr.sillyclient.modules.cheats;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.modules.Category;
import com.dkqr.sillyclient.modules.Module;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.util.math.Vec3d;

import java.util.Set;

public class KillAura extends Module {
    public KillAura(String name, Category category, int keycode) {
        super(name, category, keycode);
    }

    @Override
    public void onTick() {
        assert client.world != null;
        assert client.player != null;
        client.world.getEntities().forEach(entity -> {
            if (entity == client.player) return;
            if (client.player.distanceTo(entity) < 4 && entity.canHit() && client.player.getAttackCooldownProgress(0.0f) == 1.0f) {
                //SillyClient.LOGGER.info(entity.getDisplayName().getString());
                client.interactionManager.attackEntity(client.player, entity);
                client.player.swingHand(client.player.getActiveHand());
            }
        });
    }
}
