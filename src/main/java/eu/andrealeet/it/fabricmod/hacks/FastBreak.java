package eu.andrealeet.it.fabricmod.hacks;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.listeners.BlockBreakingProgressEvent;
import eu.andrealeet.it.fabricmod.listeners.BlockBreakingProgressListener;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import eu.andrealeet.it.fabricmod.mixininterfaces.IClientPlayerInteractionManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket.Action;

public class FastBreak extends Hack implements UpdateListener, BlockBreakingProgressListener {

    public FastBreak() {
        super("Fast Break", "Destroy block faster!");
    }

    @Override
    public void onEnable() {
        EVENTS.add(UpdateListener.class, this);
        EVENTS.add(BlockBreakingProgressListener.class, this);
    }
    
    @Override
    public void onDisable() {
        EVENTS.remove(UpdateListener.class, this);
        EVENTS.remove(BlockBreakingProgressListener.class, this);
    }

    @Override
    public void onUpdate() {
        IMC.getInteractionManager().setBlockHitDelay(0);
        
    }

    @Override
    public void onBlockBreakingProgress(BlockBreakingProgressEvent event) {
        IClientPlayerInteractionManager im = IMC.getInteractionManager();
		
		if(im.getCurrentBreakingProgress() >= 1)
			return;
		
		Action action = PlayerActionC2SPacket.Action.STOP_DESTROY_BLOCK;
		BlockPos blockPos = event.getBlockPos();
		Direction direction = event.getDirection();
		im.sendPlayerActionC2SPacket(action, blockPos, direction);
    }

}