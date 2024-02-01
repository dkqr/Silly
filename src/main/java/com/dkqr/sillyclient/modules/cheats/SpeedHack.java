package com.dkqr.sillyclient.modules.cheats;

import com.dkqr.sillyclient.modules.Category;
import com.dkqr.sillyclient.modules.Module;

public class SpeedHack extends Module {
    public SpeedHack(String name, Category category, int keycode) {
        super(name, category, keycode);
    }
    public void onEnable() {
        client.player.getAbilities().setWalkSpeed(0.07F);
    }
    public void onDisable() {
        client.player.getAbilities().setWalkSpeed(0.1F);
    }
}
