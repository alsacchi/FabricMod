package eu.andrealeet.it.fabricmod.hacks;

import java.util.List;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import net.minecraft.client.network.AbstractClientPlayerEntity;

public class PlayerESP extends Hack implements UpdateListener {
    private List<AbstractClientPlayerEntity> players;
    public PlayerESP() {
        super("PlayerESP", "Glowing PLAYERS!");
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