package eu.andrealeet.it.fabricmod;

import eu.andrealeet.it.fabricmod.listener.EventManager;
import net.minecraft.client.MinecraftClient;

public enum HackClient {
    
    INSTANCE;

    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static final String MC_VERSION = "1.15.2";

    private EventManager eventManager;
    private HacksManager hacksManager;

    public void initialize() {

        System.out.println("Initializing Andrea TEST MOD!");
        eventManager = new EventManager(this);
        hacksManager = new HacksManager();
    }

    public HacksManager getHax() {
        return hacksManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

}