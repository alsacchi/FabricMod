package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eu.andrealeet.it.fabricmod.HackClient;
import eu.andrealeet.it.fabricmod.listeners.BlockBreakingProgressEvent;
import eu.andrealeet.it.fabricmod.mixininterfaces.IClientPlayerInteractionManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

@Mixin(ClientPlayerInteractionManager.class)
public abstract class ClientPlayerInteractionManagerMixin implements IClientPlayerInteractionManager {
    
    @Shadow
    private MinecraftClient client;
    
    @Shadow
    
    private float currentBreakingProgress;
    @Shadow
    private int blockBreakingCooldown;
    
	@Shadow
    private boolean breakingBlock;
    
    private boolean overrideReach;

    @Inject(at = {@At(value = "INVOKE",
		target = "Lnet/minecraft/client/network/ClientPlayerEntity;getEntityId()I",
		ordinal = 0)},
		method = {
			"updateBlockBreakingProgress(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Z"})
	private void onPlayerDamageBlock(BlockPos blockPos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
		BlockBreakingProgressEvent event = new BlockBreakingProgressEvent(blockPos, direction);
		HackClient.INSTANCE.getEventManager().fire(event);
	}

	@Inject(at = {@At("HEAD")}, method = {"getReachDistance()F"}, cancellable = true)
	private void onGetReachDistance(CallbackInfoReturnable<Float> ci) {
		if(overrideReach)
			ci.setReturnValue(10F);
	}

	@Inject(at = {@At("HEAD")}, method = {"hasExtendedReach()Z"}, cancellable = true)
	private void hasExtendedReach(CallbackInfoReturnable<Boolean> cir) {
		if(overrideReach)
			cir.setReturnValue(true);
	}

    @Shadow
	private void sendPlayerAction(PlayerActionC2SPacket.Action playerActionC2SPacket$Action, BlockPos blockPos, Direction direction) {
		
	}

    @Override
	public void sendPlayerActionC2SPacket(Action action, BlockPos blockPos, Direction direction) {
		sendPlayerAction(action, blockPos, direction);
    }
    
    @Override
	public float getCurrentBreakingProgress() {
		return currentBreakingProgress;
	}

    @Override
	public void setOverrideReach(boolean overrideReach) {
		this.overrideReach = overrideReach;
    }
    
    @Override
	public void setBreakingBlock(boolean breakingBlock) {
		this.breakingBlock = breakingBlock;
    }
    
    @Override
	public void setBlockHitDelay(int delay) {
		blockBreakingCooldown = delay;
	}
}