package com.dkqr.sillyclient.modules.cheats;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.modules.Category;
import com.dkqr.sillyclient.modules.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.Set;

public class KillAura extends Module {
    public KillAura(String name, Category category, int keycode) {
        super(name, category, keycode);
    }

    @Override
    public void onTick() {
        assert client.world != null;
        assert client.player != null;
        ArrayList<Entity> targets = new ArrayList<>();
        client.world.getEntities().forEach(entity -> {
            if (entity == client.player) return;
            if (client.player.distanceTo(entity) < 4 && entity.canHit() && client.player.getAttackCooldownProgress(0) == 1 && ((LivingEntity) entity).getHealth() > 0) {
                //SillyClient.LOGGER.info(entity.getDisplayName().getString());
                targets.add(entity);
            }
        });
        // SORT BY HEALTH
        /*targets.sort((entity, t1) -> {
            if (((LivingEntity) entity).getHealth() < ((LivingEntity) t1).getHealth()) {
                return -1;
            } else if (((LivingEntity) entity).getHealth() == ((LivingEntity) t1).getHealth()) {
                return 0;
            }
            return 1;
        });*/
        // SORT BY UUID
        /*targets.sort((entity, t1) -> {
            if (entity.getUuidAsString().compareTo(t1.getUuidAsString()) < 0) {
                return -1;
            } else if (entity.getUuidAsString().compareTo(t1.getUuidAsString()) == 0) {
                return 0;
            }
            return 1;
        });*/
        // SORT BY DISTANCE
        targets.sort((entity, t1) -> {
            float entityDistToPlr = client.player.distanceTo(entity);
            float t1DistToPlr = client.player.distanceTo(t1);
            if (entityDistToPlr < t1DistToPlr) {
                return -1;
            } else if (entityDistToPlr == t1DistToPlr) {
                return 0;
            }
            return 1;
        });
        if (targets.isEmpty()) return;
        Entity entity = targets.get(0);
        client.interactionManager.attackEntity(client.player, entity);
        client.player.swingHand(client.player.getActiveHand());
    }
}
