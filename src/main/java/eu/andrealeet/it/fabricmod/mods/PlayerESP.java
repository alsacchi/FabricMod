package eu.andrealeet.it.fabricmod.mods;

import java.util.List;


import eu.andrealeet.it.fabricmod.mod.Mod;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import net.minecraft.client.network.AbstractClientPlayerEntity;

public class PlayerESP extends Mod implements UpdateListener {
    private List<AbstractClientPlayerEntity> players;
    public PlayerESP() {
        super("Player Glowing", "Glowing PLAYERS!");
    }

    @Override
    protected void onEnable() {
            EVENTS.add(UpdateListener.class, this);
    }

    @Override
    protected void onDisable() {
        EVENTS.remove(UpdateListener.class, this);
        if(players != null) {
            for (AbstractClientPlayerEntity player : players) {
                player.setGlowing(false);
            }
            
        }
    }

    @Override
    public void onUpdate() {
        players = MC.world.getPlayers();
        for (AbstractClientPlayerEntity player : players) {
            player.setGlowing(true);
        }
    }



}