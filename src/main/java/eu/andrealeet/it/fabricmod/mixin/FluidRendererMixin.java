package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eu.andrealeet.it.fabricmod.ModClient;
import eu.andrealeet.it.fabricmod.listeners.ShouldDrawSideEvent;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

@Mixin(FluidRenderer.class)
public class FluidRendererMixin {
    
    @Inject(at = {@At("HEAD")},
            method = {"isSideCovered(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;F)Z"},
            cancellable = true)
    private static void onIsSideCovered(BlockView view, BlockPos pos, Direction direction, float maxDeviation, CallbackInfoReturnable<Boolean> cir) {
        BlockState state = view.getBlockState(pos);
        ShouldDrawSideEvent event = new ShouldDrawSideEvent(state);
        ModClient.INSTANCE.getEventManager().fire(event);

        if(event.isRendered() != null) {
            cir.setReturnValue(!event.isRendered());
        }
    }
}