package com.dkqr.sillyclient.modules.cheats;

import com.dkqr.sillyclient.modules.Category;
import com.dkqr.sillyclient.modules.Module;
import net.minecraft.util.math.Vec3d;

public class SpeedHack extends Module {
    public SpeedHack(String name, Category category, int keycode) {
        super(name, category, keycode);
    }
    public void onTick() {
        if (client.player.isSneaking() || client.player.forwardSpeed == 0 && client.player.sidewaysSpeed == 0)
            return;
        if (!client.player.isOnGround())
            return;
        Vec3d plrVel = client.player.getVelocity();
        client.player.setVelocity(plrVel.x * 1.8, 0.5, plrVel.z * 1.8);
    }
}
