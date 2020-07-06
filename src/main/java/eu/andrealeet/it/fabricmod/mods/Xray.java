package eu.andrealeet.it.fabricmod.mods;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eu.andrealeet.it.fabricmod.listeners.GetAmbientOcclusionLightLevelEvent;
import eu.andrealeet.it.fabricmod.listeners.GetAmbientOcclusionLightLevelListener;
import eu.andrealeet.it.fabricmod.listeners.RenderBlockEntityEvent;
import eu.andrealeet.it.fabricmod.listeners.RenderBlockEntityListener;
import eu.andrealeet.it.fabricmod.listeners.SetOpaqueCubeEvent;
import eu.andrealeet.it.fabricmod.listeners.SetOpaqueCubeListener;
import eu.andrealeet.it.fabricmod.listeners.ShouldDrawSideEvent;
import eu.andrealeet.it.fabricmod.listeners.ShouldDrawSideListener;
import eu.andrealeet.it.fabricmod.listeners.TesselateBlockEvent;
import eu.andrealeet.it.fabricmod.listeners.TesselateBlockListener;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener;
import eu.andrealeet.it.fabricmod.mod.Mod;
import net.minecraft.block.Block;
import net.minecraft.util.registry.Registry;

public class Xray extends Mod implements UpdateListener, SetOpaqueCubeListener, GetAmbientOcclusionLightLevelListener, ShouldDrawSideListener, TesselateBlockListener, RenderBlockEntityListener {

    private final List<String> ores = Arrays.asList("minecraft:anvil", "minecraft:beacon", "minecraft:bone_block", "minecraft:bookshelf", "minecraft:brewing_stand",
                                                    "minecraft:chain_command_block", "minecraft:chest", "minecraft:clay",
                                                    "minecraft:coal_block", "minecraft:coal_ore", "minecraft:command_block",
                                                    "minecraft:crafting_table", "minecraft:diamond_block",
                                                    "minecraft:diamond_ore", "minecraft:dispenser", "minecraft:dropper",
                                                    "minecraft:emerald_block", "minecraft:emerald_ore",
                                                    "minecraft:enchanting_table", "minecraft:end_portal",
                                                    "minecraft:end_portal_frame", "minecraft:ender_chest",
                                                    "minecraft:furnace", "minecraft:glowstone", "minecraft:gold_block",
                                                    "minecraft:gold_ore", "minecraft:hopper", "minecraft:iron_block",
                                                    "minecraft:iron_ore", "minecraft:ladder", "minecraft:lapis_block",
                                                    "minecraft:lapis_ore", "minecraft:lava", "minecraft:mossy_cobblestone",
                                                    "minecraft:nether_portal", "minecraft:nether_quartz_ore",
                                                    "minecraft:redstone_block", "minecraft:redstone_ore",
                                                    "minecraft:repeating_command_block", "minecraft:spawner",
                                                    "minecraft:tnt", "minecraft:torch", "minecraft:trapped_chest",
                                                    "minecraft:water");

    public Xray() {
        super("Xray", "See every ore!");
    }

    @Override
    public void onEnable() {
        EVENTS.add(UpdateListener.class, this);
        EVENTS.add(SetOpaqueCubeListener.class, this);
        EVENTS.add(GetAmbientOcclusionLightLevelListener.class, this);
        EVENTS.add(ShouldDrawSideListener.class, this);
        EVENTS.add(TesselateBlockListener.class, this);
        EVENTS.add(RenderBlockEntityListener.class, this);
        MC.worldRenderer.reload();
    }

    @Override
    public void onDisable() {
        EVENTS.remove(UpdateListener.class, this);
        EVENTS.remove(SetOpaqueCubeListener.class, this);
        EVENTS.remove(GetAmbientOcclusionLightLevelListener.class, this);
        EVENTS.remove(ShouldDrawSideListener.class, this);
        EVENTS.remove(TesselateBlockListener.class, this);
        EVENTS.remove(RenderBlockEntityListener.class, this);
        MC.worldRenderer.reload();
        MC.options.gamma = 1F;
    }

    @Override
    public void onUpdate() {
        MC.options.gamma = 16F;
    }

    @Override
    public void onSetOpaqueCube(SetOpaqueCubeEvent event) {
        event.cancel();
    }

    @Override
    public void onGetAmbientOcclusionLightLevel(GetAmbientOcclusionLightLevelEvent event) {
        event.setLightLevel(1);
    }

    @Override
	public void onShouldDrawSide(ShouldDrawSideEvent event) {
		event.setRendered(isVisible(event.getState().getBlock()));
	}

    @Override
	public void onTesselateBlock(TesselateBlockEvent event) {
		if(!isVisible(event.getState().getBlock()))
			event.cancel();
	}
	
	@Override
	public void onRenderBlockEntity(RenderBlockEntityEvent event) {
		if(!isVisible(MC.world.getBlockState(event.getBlockEntity().getPos()).getBlock()))
			event.cancel();
	}
	
	private boolean isVisible(Block block) {
		String name = Registry.BLOCK.getId(block).toString();
		int index = Collections.binarySearch(ores, name);
		return index >= 0;
	}

    
}