package eu.andrealeet.it.fabricmod.mixininterfaces;

import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public interface IClientPlayerInteractionManager {
    
	public float getCurrentBreakingProgress();
	
	public void setBreakingBlock(boolean breakingBlock);
	
	public void sendPlayerActionC2SPacket(Action action,
		BlockPos blockPos, Direction direction);
	
	public void setBlockHitDelay(int delay);
	
	public void setOverrideReach(boolean overrideReach);
}