package eu.andrealeet.it.fabricmod.hacks;

import java.util.Random;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import io.netty.buffer.Unpooled;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;


public class Test extends Hack implements UpdateListener {

    public Test() {
        super("Test", "Testing!");
    }

    @Override
    protected void onEnable() {
        EVENTS.add(UpdateListener.class, this);
    }

    @Override
    protected void onDisable() {
        EVENTS.remove(UpdateListener.class, this);
    }

    @Override
    public void onUpdate() {
        MC.player.getHungerManager().add(10, 10);
        byte[] array = new byte[32627];
        new Random().nextBytes(array);
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeByteArray(array);
        MC.player.networkHandler.sendPacket(new CustomPayloadC2SPacket(new Identifier("badlion:mods"), buf));
        
    }
    
        
}