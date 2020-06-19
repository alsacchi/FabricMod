package eu.andrealeet.it.fabricmod.hacks;

import eu.andrealeet.it.fabricmod.hack.Hack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.text.LiteralText;

public class GiveTest extends Hack {

    public GiveTest() {
        super("GiveTest", "Test");
    }

    @Override
    protected void onEnable() {
        ItemStack item = new ItemStack(Items.SPLASH_POTION, 64);
        CompoundTag tag = new CompoundTag();
        tag.putInt("Amplifier", 125);
        tag.putInt("Duration", 2000);
        tag.putInt("Id", 6);
        ListTag effects = new ListTag();
        effects.add(tag);
        CompoundTag nbt = new CompoundTag();
        nbt.put("CustomPotionEffects", effects);
        item.setTag(nbt);
        item.setCustomName(new LiteralText("MAGIA"));

        MC.player.giveItemStack(item);
    }
    
}