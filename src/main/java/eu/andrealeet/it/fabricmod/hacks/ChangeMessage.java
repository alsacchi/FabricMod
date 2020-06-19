package eu.andrealeet.it.fabricmod.hacks;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.listeners.ChatOutputEvent;
import eu.andrealeet.it.fabricmod.listeners.ChatOutputListener;
import net.minecraft.item.ItemStack;

public class ChangeMessage extends Hack implements ChatOutputListener {

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
        if(event.getOriginalMessage().contains("test")) {
            event.cancel();
            ItemStack item = MC.player.getMainHandStack();
            if(!item.isEmpty())
                System.out.println(item.getTag().toString());
            
        }
    }
    
}