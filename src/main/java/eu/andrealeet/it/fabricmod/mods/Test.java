package eu.andrealeet.it.fabricmod.mods;

import eu.andrealeet.it.fabricmod.mod.Mod;
import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.nio.charset.Charset;
import java.util.Random;

import eu.andrealeet.it.fabricmod.listeners.UpdateListener;

public class Test extends Mod implements UpdateListener {

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
        byte[] array = new byte[375];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        Text first = new LiteralText(generatedString);
        Text second = first;
        Text third = first;
        Text fourth = first;
        MC.player.networkHandler.sendPacket(new UpdateSignC2SPacket(MC.player.getBlockPos(), first, second, third, fourth));
    }
    
        
}