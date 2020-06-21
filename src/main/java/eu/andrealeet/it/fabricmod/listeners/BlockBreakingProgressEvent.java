package eu.andrealeet.it.fabricmod.listeners;

import java.util.ArrayList;

import eu.andrealeet.it.fabricmod.listener.Event;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class BlockBreakingProgressEvent extends Event<BlockBreakingProgressListener> {

    private final BlockPos blockPos;
    private final Direction direction;

    public BlockBreakingProgressEvent(BlockPos blockPos, Direction direction) {
        this.blockPos = blockPos;
        this.direction = direction;
    }

    @Override
		public void fire(ArrayList<BlockBreakingProgressListener> listeners) {
			for(BlockBreakingProgressListener listener : listeners)
				listener.onBlockBreakingProgress(this);
		}
		
		@Override
		public Class<BlockBreakingProgressListener> getListenerType() {
			return BlockBreakingProgressListener.class;
		}

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public Direction getDirection() {
        return direction;
    }
}