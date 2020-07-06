package eu.andrealeet.it.fabricmod.listeners;

import java.util.ArrayList;

import eu.andrealeet.it.fabricmod.listener.Event;
import net.minecraft.block.BlockState;

public class GetAmbientOcclusionLightLevelEvent extends Event<GetAmbientOcclusionLightLevelListener> {
    
    private final BlockState state;
    private float lightLevel;
    private final float defaultLightLevel;

    public GetAmbientOcclusionLightLevelEvent(BlockState state, float lightLevel) {
        this.state = state;
        this.lightLevel = lightLevel;
        this.defaultLightLevel = lightLevel;
    }

    public BlockState getState() {
        return state;
    }

    public float getLightLevel() { 
        return lightLevel;
    }

    public float getDefaultLightLevel() {
        return defaultLightLevel;
    }

    public void setLightLevel(float lightLevel) {
        this.lightLevel = lightLevel;
    }

    @Override
	public void fire(ArrayList<GetAmbientOcclusionLightLevelListener> listeners) {
		for(GetAmbientOcclusionLightLevelListener listener : listeners)
			listener.onGetAmbientOcclusionLightLevel(this);
	}
	
	@Override
	public Class<GetAmbientOcclusionLightLevelListener> getListenerType() {
		return GetAmbientOcclusionLightLevelListener.class;
	}
}