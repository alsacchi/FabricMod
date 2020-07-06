package eu.andrealeet.it.fabricmod.listeners;

import java.util.ArrayList;

import eu.andrealeet.it.fabricmod.listener.CancellableEvent;
import net.minecraft.block.entity.BlockEntity;

public class RenderBlockEntityEvent extends CancellableEvent<RenderBlockEntityListener> {

    private final BlockEntity blockEntity;

    public RenderBlockEntityEvent(BlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }

    public BlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
	public void fire(ArrayList<RenderBlockEntityListener> listeners) {
		for(RenderBlockEntityListener listener : listeners)
			listener.onRenderBlockEntity(this);
	}
	
	@Override
	public Class<RenderBlockEntityListener> getListenerType() {
		return RenderBlockEntityListener.class;
	}
    
}