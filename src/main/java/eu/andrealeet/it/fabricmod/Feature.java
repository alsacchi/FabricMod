package eu.andrealeet.it.fabricmod;

import eu.andrealeet.it.fabricmod.listener.EventManager;
import net.minecraft.client.MinecraftClient;

public abstract class Feature {
    
    protected static final HackClient CLIENT = HackClient.INSTANCE;
    protected static final EventManager EVENTS = CLIENT.getEventManager();
    protected static final MinecraftClient MC = HackClient.MC;
    protected static final String SECTION_SIGN = "\u00A7";

    public abstract String getName();

    public abstract String getDescription();

    public boolean isEnabled() {
        return false;
    }

}