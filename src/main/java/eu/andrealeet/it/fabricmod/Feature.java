package eu.andrealeet.it.fabricmod;

import eu.andrealeet.it.fabricmod.listener.EventManager;
import eu.andrealeet.it.fabricmod.mixininterfaces.IMinecraftClient;
import net.minecraft.client.MinecraftClient;

public abstract class Feature {
    
    protected static final ModClient CLIENT = ModClient.INSTANCE;
    protected static final EventManager EVENTS = CLIENT.getEventManager();
    protected static final MinecraftClient MC = ModClient.MC;
    protected static final IMinecraftClient IMC = ModClient.IMC;
    protected static final String SECTION_SIGN = "\u00A7";

    public abstract String getName();

    public abstract String getDescription();

    public boolean isEnabled() {
        return false;
    }

}