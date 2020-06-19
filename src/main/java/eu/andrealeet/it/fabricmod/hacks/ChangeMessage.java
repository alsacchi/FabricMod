package eu.andrealeet.it.fabricmod.hacks;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.listeners.ChatOutputEvent;
import eu.andrealeet.it.fabricmod.listeners.ChatOutputListener;

public class ChangeMessage extends Hack implements ChatOutputListener {

    public ChangeMessage() {
        super("Change Chat!", "Changes the word \"test\" to \"tost\"");
    }

    @Override
    protected void onEnable() {
        EVENTS.add(ChatOutputListener.class, this);
    }

    @Override
    protected void onDisable() {
        EVENTS.remove(ChatOutputListener.class, this);
    }

    @Override
    public void onSentMessage(ChatOutputEvent event) {
        if(event.getOriginalMessage().equalsIgnoreCase("test")) {
            event.setMessage("tost");
        }
    }
    
}