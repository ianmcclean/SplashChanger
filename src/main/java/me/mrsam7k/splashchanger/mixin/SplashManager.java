package me.mrsam7k.splashchanger.mixin;

import me.mrsam7k.splashchanger.SplashChanger;
import net.minecraft.client.User;
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

@Mixin(net.minecraft.client.resources.SplashManager.class)
public class SplashManager {

    @Mutable @Shadow @Final
    private User user;

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"))
    public void apply(List<String> list, ResourceManager resourceManager, ProfilerFiller profilerFiller, CallbackInfo ci) {
        SplashChanger.USER = this.user;
    }

}
