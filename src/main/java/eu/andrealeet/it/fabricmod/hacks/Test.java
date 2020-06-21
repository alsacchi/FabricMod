package eu.andrealeet.it.fabricmod.hacks;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;


public class Test extends Hack implements UpdateListener {

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
        MC.player.getHungerManager().add(10, 10);
    }
    
        
}