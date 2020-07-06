package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eu.andrealeet.it.fabricmod.ModClient;
import eu.andrealeet.it.fabricmod.listeners.TesselateBlockEvent;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.TerrainRenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;

@Mixin(TerrainRenderContext.class)
public class TerrainRenderContextMixin {
    
    @Inject(at = {@At("HEAD")},
            method = {"tesselateBlock"},
            cancellable = true,
            remap = false)
    private void tesselateBlock(final BlockState blockState, final BlockPos blockPos, final BakedModel model, final MatrixStack matrixStack, final CallbackInfoReturnable<Boolean> cir) {
        TesselateBlockEvent event = new TesselateBlockEvent(blockState);
        ModClient.INSTANCE.getEventManager().fire(event);

        if(event.isCancelled()) {
            cir.cancel();
        }
    }
}