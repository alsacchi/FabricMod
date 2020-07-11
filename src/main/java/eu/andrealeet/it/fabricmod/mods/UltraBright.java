package eu.andrealeet.it.fabricmod.mods;

import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import eu.andrealeet.it.fabricmod.mod.Mod;

public class UltraBright extends Mod implements UpdateListener {
 
    public UltraBright() {
        super("UltraBright", "See everything everywhere!");
    }    

    @Override
    public void onEnable() {
        EVENTS.add(UpdateListener.class, this);
    }

    @Override
    public void onDisable() {
        EVENTS.remove(UpdateListener.class, this);
        MC.options.gamma = 1F;
    }

    @Override
    public void onUpdate() {
        MC.options.gamma = 16F;
    }

}