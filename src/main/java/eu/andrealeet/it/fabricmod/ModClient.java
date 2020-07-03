package eu.andrealeet.it.fabricmod;

import eu.andrealeet.it.fabricmod.listener.EventManager;
import eu.andrealeet.it.fabricmod.mixininterfaces.IMinecraftClient;
import net.minecraft.client.MinecraftClient;

public enum ModClient {
    
    INSTANCE;

    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static final IMinecraftClient IMC = (IMinecraftClient)MC;
    public static final String MC_VERSION = "1.15.2";

    private EventManager eventManager;
    private ModsManager ModsManager;

    public void initialize() {

        System.out.println("Initializing Andrea TEST MOD!");
        eventManager = new EventManager(this);
        ModsManager = new ModsManager();
    }

    public ModsManager getMods() {
        return ModsManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

}