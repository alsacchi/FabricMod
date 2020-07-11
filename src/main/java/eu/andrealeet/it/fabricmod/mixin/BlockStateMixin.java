package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eu.andrealeet.it.fabricmod.ModClient;
import eu.andrealeet.it.fabricmod.listeners.GetAmbientOcclusionLightLevelEvent;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.AbstractState;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(BlockState.class)
public class BlockStateMixin extends AbstractState<Block, BlockState> {
    
    private BlockStateMixin(Block block, ImmutableMap<Property<?>, Comparable<?>> immutableMap) {
        super(block, immutableMap);
    }

    @Inject(at = {@At("TAIL")},
            method = {"getAmbientOcclusionLightLevel(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)F"},
            cancellable = true)
    private void onGetAmbientOcclusionLightLevel(BlockView blockView, BlockPos blockPos, CallbackInfoReturnable<Float> cir) {
        GetAmbientOcclusionLightLevelEvent event = new GetAmbientOcclusionLightLevelEvent((BlockState)(Object) this, cir.getReturnValueF());
        ModClient.INSTANCE.getEventManager().fire(event);
        cir.setReturnValue(event.getLightLevel());
    }
}