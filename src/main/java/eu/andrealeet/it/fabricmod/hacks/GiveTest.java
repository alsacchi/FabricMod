package eu.andrealeet.it.fabricmod.hacks;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;

public class GiveTest extends Hack implements UpdateListener{
    private final ItemStack item = new ItemStack(Items.WRITABLE_BOOK, 1);
    public GiveTest() {
        super("GiveTest", "Creative item generator");
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
        for(int i = 0; i < 9; i++) {
            if(MC.player.inventory.getInvStack(i).isEmpty())
                MC.player.networkHandler.sendPacket(new CreativeInventoryActionC2SPacket(i+36, item));

            
        }   
    }
    
}