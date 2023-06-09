package me.mrsam7k.splashchanger;

import eu.midnightdust.lib.config.MidnightConfig;
import me.mrsam7k.splashchanger.config.Config;
import net.fabricmc.api.ModInitializer;

public class SplashChanger implements ModInitializer {

    @Override
    public void onInitialize() {
        MidnightConfig.init("splashchanger", Config.class);
    }


}
