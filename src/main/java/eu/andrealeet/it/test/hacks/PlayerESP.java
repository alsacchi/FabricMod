package eu.andrealeet.it.test.hacks;

import java.util.List;

import eu.andrealeet.it.test.hack.Hack;
import eu.andrealeet.it.test.listeners.UpdateListener;
import net.minecraft.client.network.AbstractClientPlayerEntity;

public class PlayerESP extends Hack implements UpdateListener {
    private List<AbstractClientPlayerEntity> players;
    public PlayerESP() {
        super("PlayerESP", "Vedi i giocatori");
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