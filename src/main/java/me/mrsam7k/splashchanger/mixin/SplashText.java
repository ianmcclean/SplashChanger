package me.mrsam7k.splashchanger.mixin;


import me.mrsam7k.splashchanger.config.Config;
import net.minecraft.client.User;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(SplashManager.class)
public class SplashText {

    @Shadow @Final private User user;

    @Mutable
    @Shadow @Final private List<String> splashes;

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("RETURN"))
    public void apply(List<String> list, ResourceManager resourceManager, ProfilerFiller profilerFiller, CallbackInfo ci) {
        String name = user.getName();
        if(Config.splashMode.equals(Config.splashModeEnum.SINGLE_SPLASH)){
            String splash = Config.customSplash.replace("%name", name);
            this.splashes.clear();
            this.splashes.add(splash);
        } else if(Config.splashMode.equals(Config.splashModeEnum.RANDOM_SPLASH)){
            int randomIndex = new Random().nextInt(Config.customSplashes.size());
            String splash = Config.customSplashes.get(randomIndex).replace("%name", name);
            this.splashes.clear();
            this.splashes.add(splash);
        }

    }


}
