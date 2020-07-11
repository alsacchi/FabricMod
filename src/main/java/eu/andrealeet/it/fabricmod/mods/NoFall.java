package eu.andrealeet.it.fabricmod.mods;

import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import eu.andrealeet.it.fabricmod.mod.Mod;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class NoFall extends Mod implements UpdateListener {
    
    public NoFall() {
        super("No.fall", "Get no fall damage!");
    }

    @Override
    public void onEnable() {
        EVENTS.add(UpdateListener.class, this);
    }

    @Override
    public void onDisable() {
        EVENTS.remove(UpdateListener.class, this);
    }

    @Override
    public void onUpdate() {
        ClientPlayerEntity player = MC.player;
		if(player.fallDistance <= 2)
			return;
		
		player.networkHandler.sendPacket(new PlayerMoveC2SPacket(true));
    }
}