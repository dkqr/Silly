package com.dkqr.sillyclient.mixin;

import com.dkqr.sillyclient.SillyClient;
import com.dkqr.sillyclient.screens.HackSelectScreen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.util.function.Supplier;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Shadow protected abstract ButtonWidget createButton(Text text, Supplier<Screen> screenSupplier);

    @Inject(at = @At("TAIL"), method = "init")
    private void init(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Silly Client"), button -> {
            //SillyClient.client.setScreen(new HackSelectScreen(Text.literal("Hack Select")));
            SillyClient.client.setScreen(new HackSelectScreen(Text.literal("Hack Select")));
        }).width(98).position(5, 5).build());
    }
}
