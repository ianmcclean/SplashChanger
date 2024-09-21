package me.mrsam7k.splashchanger.mixin;


import me.mrsam7k.splashchanger.SplashChanger;
import me.mrsam7k.splashchanger.config.Config;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(net.minecraft.client.gui.components.SplashRenderer.class)
public class SplashRenderer {

    @Mutable @Shadow @Final
    private String splash;

    @Inject(method = "render", at = @At("HEAD"))
    public void render(GuiGraphics guiGraphics, int i, Font font, int j, CallbackInfo ci) {

        String splash = "";
        if(Config.splashMode.equals(Config.SplashModes.SINGLE_SPLASH))
            splash = Config.customSplash;

        else if(Config.splashMode.equals(Config.SplashModes.RANDOM_SPLASH)){
            if(SplashChanger.CACHED_SPLASHES == Config.customSplashes)
                splash = SplashChanger.CACHED_SPLASHES.get(SplashChanger.RANDOM_SPLASH_INT);
            else {
                SplashChanger.RANDOM_SPLASH_INT = new Random().nextInt(Config.customSplashes.size());
                splash = Config.customSplashes.get(SplashChanger.RANDOM_SPLASH_INT);
                SplashChanger.CACHED_SPLASHES = Config.customSplashes;
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append("§").append(Config.color.getCode());

        String[] formats = {
                Config.OBFUSCATED ? "§k" : "",
                Config.BOLD ? "§l" : "",
                Config.STRIKETHROUGH ? "§m" : "",
                Config.UNDERLINE ? "§n" : "",
                Config.ITALIC ? "§o" : ""
        };

        for (String format : formats) {
            if(!format.isEmpty())
                sb.append(format);
        }

        if(SplashChanger.USER != null)
            sb.append(splash.replace("&", "§").replace("%name", SplashChanger.USER.getName()));

        if(!Config.splashMode.equals(Config.SplashModes.ORIGINAL))
            this.splash = sb.toString();
    }


}
