package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import eu.andrealeet.it.fabricmod.mixininterfaces.IClientPlayerInteractionManager;
import eu.andrealeet.it.fabricmod.mixininterfaces.IMinecraftClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.thread.ReentrantThreadExecutor;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin extends ReentrantThreadExecutor<Runnable> implements IMinecraftClient {
    
    @Shadow
    private ClientPlayerInteractionManager interactionManager;

    public MinecraftClientMixin(String name) {
        super(name);
    }

    @Override
	public IClientPlayerInteractionManager getInteractionManager() {
        return (IClientPlayerInteractionManager)interactionManager;
    }
    
    @Inject(at = @At("RETURN"), method = "isModded()Z", cancellable = true)
    private void isModded(CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(false);
    }

    @Inject(at = @At("RETURN"), method = "getVersionType()Ljava/lang/String", cancellable = true)
    public void getVersionType(CallbackInfoReturnable<String> info) {
        info.setReturnValue("release");
    }

    @Inject(at = @At("RETURN"), method = "getGameVersion()Ljava/lang/String", cancellable = true)
    public void getGameVersion(CallbackInfoReturnable<String> info) {
        info.setReturnValue("1.15.2/vanilla");
    }

}