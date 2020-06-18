package eu.andrealeet.it.test.hacks;

import eu.andrealeet.it.test.hack.Hack;
import eu.andrealeet.it.test.listeners.UpdateListener;

public class Test extends Hack implements UpdateListener {

    public Test() {
        super("Test", "Qua test");
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
        if(MC.options.keyJump.isPressed()) 
            MC.player.jump();
        

    }
}