package eu.andrealeet.it.fabricmod.mods;


import eu.andrealeet.it.fabricmod.mod.Mod;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;

public class Test extends Mod implements UpdateListener {

    public Test() {
        super("Test", "Testing!");
    }

    @Override
    protected void onEnable() {
        EVENTS.add(UpdateListener.class, this);
    }

    @Override
    protected void onDisable() {
        EVENTS.remove(UpdateListener.class, this);
    }

    @Override
    public void onUpdate() {
    }
    
        
}