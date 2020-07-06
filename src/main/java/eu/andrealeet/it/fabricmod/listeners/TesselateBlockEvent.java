package eu.andrealeet.it.fabricmod.listeners;

import java.util.ArrayList;

import eu.andrealeet.it.fabricmod.listener.CancellableEvent;
import net.minecraft.block.BlockState;

public class TesselateBlockEvent extends CancellableEvent<TesselateBlockListener> {
    
    private final BlockState state;

    public TesselateBlockEvent(BlockState state) {
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    @Override
	public void fire(ArrayList<TesselateBlockListener> listeners) {
		for(TesselateBlockListener listener : listeners)
			listener.onTesselateBlock(this);
	}
	
	@Override
	public Class<TesselateBlockListener> getListenerType() {
		return TesselateBlockListener.class;
	}
}