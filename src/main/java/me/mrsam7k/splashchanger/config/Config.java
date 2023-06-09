package me.mrsam7k.splashchanger.config;

import com.google.common.collect.Lists;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

public class Config extends MidnightConfig {

    @Entry public static splashModeEnum splashMode = splashModeEnum.RANDOM_SPLASH;
    public enum splashModeEnum {
        SINGLE_SPLASH, RANDOM_SPLASH, ORIGINAL
    }
    @Entry public static String customSplash = "Singular Splash!";
    @Entry public static List<String> customSplashes = Lists.newArrayList("%name, that's you... I think", "Change this in options!");
    @Comment public static Comment splashWarning;

    public static Screen getScreen(Screen parent){
        return MidnightConfig.getScreen(parent, "splashchanger");
    }

}
