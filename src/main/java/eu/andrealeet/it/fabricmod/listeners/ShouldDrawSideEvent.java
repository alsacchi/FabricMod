package eu.andrealeet.it.fabricmod.listeners;

import java.util.ArrayList;

import eu.andrealeet.it.fabricmod.listener.Event;
import net.minecraft.block.BlockState;

public class ShouldDrawSideEvent extends Event<ShouldDrawSideListener> {

    private final BlockState state;
    private Boolean rendered;

    public ShouldDrawSideEvent(BlockState state) {
        this.state = state;
    }

    public BlockState getState() {
        return state;
    }

    public Boolean isRendered() {
        return rendered;
    }

    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }

    @Override
	public void fire(ArrayList<ShouldDrawSideListener> listeners) {
		for(ShouldDrawSideListener listener : listeners)
			listener.onShouldDrawSide(this);
	}
	
	@Override
	public Class<ShouldDrawSideListener> getListenerType() {
		return ShouldDrawSideListener.class;
	}
}