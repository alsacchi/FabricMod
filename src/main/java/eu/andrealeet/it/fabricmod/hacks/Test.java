package eu.andrealeet.it.fabricmod.hacks;

import java.nio.charset.Charset;
import java.util.Random;

import eu.andrealeet.it.fabricmod.hack.Hack;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WritableBookItem;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.packet.c2s.play.BookUpdateC2SPacket;
import net.minecraft.util.Hand;

public class Test extends Hack implements UpdateListener {
    private int i = 0;

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
        if (i % 20 == 0) {

            ItemStack item = MC.player.inventory.getMainHandStack();
            if (item.getItem() instanceof WritableBookItem) {

                ListTag tag = new ListTag();
                for (int j = 0; j < 100; j++) {
                    byte[] array = new byte[1024];
                    new Random().nextBytes(array);
                    String generatedString = new String(array, Charset.forName("UTF-8"));
                    tag.add(StringTag.of(generatedString));
                }
                item.putSubTag("pages", tag);
                item.putSubTag("author", StringTag.of("NOBODY"));
                item.putSubTag("title", StringTag.of("Libro_figo"));

                MC.player.networkHandler.sendPacket(new BookUpdateC2SPacket(item, true, Hand.MAIN_HAND));
                // MC.player.dropSelectedItem(true);
            }
            MC.player.inventory.scrollInHotbar(1);
        }
        i++;
    }
}