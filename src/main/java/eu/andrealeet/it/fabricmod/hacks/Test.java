package eu.andrealeet.it.fabricmod.hacks;

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
                for (int j = 0; j < 500; j++) {
                    tag.add(StringTag.of(
                            "À̸̷̧̧̹͎̫͕̥̖̣͚̠̻̫̫͍̳͔̮̱A͇̜͎͇̝̜̮̗̖̰͙̭̳͘͝ͅͅĄ̵̙͚͉̭̲̝̘̕A̴̷̺̞͔͍͔͎͖̝̣̝͡A҉̻̙̜̥̩͚̱͉̫͉̗͓̦̜͘A̷̧̨͓͎̠͈̣̻̥̬̬̣̣͓͇̮͙̫̗̗̟͟͝A̢̢̝̻̠̝͎̞̙̖̲̝̬̻͘͞A̦͓̣͙̺͢A̵̡̧̝̜̺͚͎̭̼̭̹̤̱̻̕͠À̡̙̥̞͚̻͍̱̦̣̰́͟͡Ạ̛͉̫̘͍̘͕͘͜ͅA҉͏͎̩͕̻̳̥͔̬̱̼͝A̡̜̬̠͓̳͉̠̗A҉̶̺̗̬̟͚̦͕̠͔̻͚͙̻̺͢͢͝ͅͅĄ̴̳̞̞̲̘̤͙̬̣̀͡ͅÀ̙̣̯̙̖̟̼̝͇̜̞̤̞͓͚͈̙Á̧̡̘̲͚̘̘̼͘͠A̵̧̼̹̠̺͓͟͞A̷̸̪͚͉͔͉̯̖̯̮̭̺̘̬͇̯̘͝A̷̶̙̳̹̬̻̤͎͉̞͍̫̼̦̝̟̜̤A̵͡͏̼̭̣̘͖͕̬͖͇̲͙̝̟̺̟À̼̦̱̭̫̥̺̲̳͢ͅA̛̟͚͔̼̼̭͈͕̞͕͔̙͙̺͟͢Ą̷̨̩̖̗͚̘̻͈Ạ̴̡̝̙̺͔̼̹̜A̶̤̙͍̬̮̖͖̲̳̫̗͕̩͔̹̱̯͜͠A̴̱̻̦̹̫͚̰͜͢͠Ą͚̯̤͚̼̲̬͎̹̘̠̮̫̗̀͝A̷̡̦̫̰̳͚̘͈̲̜̼̫͔ͅA̶̡̨̛͕̥͉͉͔̫͖͚̮͟À̛͚͎̦͓̮̜̹̤͇̙̻͇̺̹̼"));
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