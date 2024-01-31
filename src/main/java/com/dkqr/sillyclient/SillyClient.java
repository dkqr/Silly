package com.dkqr.sillyclient;

import com.dkqr.sillyclient.modules.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SillyClient implements ClientModInitializer {
    public static final String MODID = "SillyClient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    public static MinecraftClient client = MinecraftClient.getInstance();
    @Override
    public void onInitializeClient() {
        LOGGER.info("Starting SillyClient!! :3");
        ModuleManager mm = new ModuleManager();
    }
}
