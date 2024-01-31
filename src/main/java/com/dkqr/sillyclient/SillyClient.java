package com.dkqr.sillyclient;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SillyClient implements ClientModInitializer {
    public static final String MODID = "SillyClient";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    @Override
    public void onInitializeClient() {
        LOGGER.info("Starting SillyClient!! :3");
    }
}
