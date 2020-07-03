package eu.andrealeet.it.fabricmod.mods;


import eu.andrealeet.it.fabricmod.mod.Mod;
import eu.andrealeet.it.fabricmod.listeners.ChatOutputEvent;
import eu.andrealeet.it.fabricmod.listeners.ChatOutputListener;

public class ChangeMessage extends Mod implements ChatOutputListener {

    public ChangeMessage() {
        super("Change Chat!", "Changes a chat message!");
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
        String message = event.getOriginalMessage();
        switch(message) {
            case "test":
                event.setMessage("\u5C3A\uD835\uDC1A\uFF2D\u0E04\u24E1\u044F\u0E4F \u722A\u24B6\u24C7\u02B3\uFF2F\u1DB0\u03AD"); 
                break;
            case "login":
                event.setMessage("/login password1");
                break;
        }
    }
    
}