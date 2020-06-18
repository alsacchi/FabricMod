package eu.andrealeet.it.test;

import eu.andrealeet.it.test.listener.EventManager;
import net.minecraft.client.MinecraftClient;

public abstract class Feature {
    
    protected static final HackClient CLIENT = HackClient.INSTANCE;
    protected static final EventManager EVENTS = CLIENT.getEventManager();
    protected static final MinecraftClient MC = HackClient.MC;

    public abstract String getName();

    public abstract String getDescription();

    public boolean isEnabled() {
        return false;
    }

}