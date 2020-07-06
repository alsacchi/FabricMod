package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eu.andrealeet.it.fabricmod.ModClient;
import eu.andrealeet.it.fabricmod.listeners.ShouldDrawSideEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

@Mixin(Block.class)
public abstract class BlockMixin implements ItemConvertible {
    
    @Inject(at = {@At("HEAD")},
            method = {"shouldDrawSide(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;)Z"},
            cancellable =  true)
    private static void onShouldDrawSide(BlockState state, BlockView blockView, BlockPos pos, Direction side, CallbackInfoReturnable<Boolean> cir) {
        
        ShouldDrawSideEvent event = new ShouldDrawSideEvent(state);
        ModClient.INSTANCE.getEventManager().fire(event);
        if(event.isRendered() != null) {
            cir.setReturnValue(event.isRendered());
        }
    }

}