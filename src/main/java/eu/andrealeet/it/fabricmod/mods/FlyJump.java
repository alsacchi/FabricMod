package eu.andrealeet.it.fabricmod.mods;


import eu.andrealeet.it.fabricmod.mod.Mod;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;


public class FlyJump extends Mod implements UpdateListener {

    public FlyJump() {
        super("FlyJump", "Fly like you are jumping constantly!");
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
        if(MC.options.keyJump.isPressed()) {
            MC.player.jump();
        }
    }
    
        
}