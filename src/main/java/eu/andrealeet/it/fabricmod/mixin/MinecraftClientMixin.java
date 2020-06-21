package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

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

}