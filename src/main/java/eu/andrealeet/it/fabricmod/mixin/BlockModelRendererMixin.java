package eu.andrealeet.it.fabricmod.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eu.andrealeet.it.fabricmod.ModClient;
import eu.andrealeet.it.fabricmod.listeners.ShouldDrawSideEvent;
import eu.andrealeet.it.fabricmod.listeners.TesselateBlockEvent;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

@Mixin(BlockModelRenderer.class)
public abstract class BlockModelRendererMixin {
    
    @Inject(at = {@At("HEAD")},
            method = {"renderSmooth(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;ZLjava/util/Random;JI)Z",
                        "renderFlat(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;ZLjava/util/Random;JI)Z"},
            cancellable = true)
    private void onRenderSmoothOrFlat(BlockRenderView view, BakedModel model, BlockState state, BlockPos pos, MatrixStack buffer, VertexConsumer vertexConsumer, boolean cull, Random random, long seed, int overlay, CallbackInfoReturnable<Boolean> cir) {
        TesselateBlockEvent event = new TesselateBlockEvent(state);
        ModClient.INSTANCE.getEventManager().fire(event);

        if(event.isCancelled()) {
            cir.cancel();
            return;
        }

        if(!cull) {
            return;
        }

        ShouldDrawSideEvent drawEvent = new ShouldDrawSideEvent(state);
        ModClient.INSTANCE.getEventManager().fire(drawEvent);
        if(Boolean.TRUE.equals(drawEvent.isRendered())) {
            return;
        }
        renderSmooth(view, model, state, pos, buffer, vertexConsumer, false, random, seed, overlay);
    }

    @Shadow
    public boolean renderSmooth(BlockRenderView world, BakedModel model, BlockState state, BlockPos pos, MatrixStack buffer, VertexConsumer vertexConsumer, boolean cull, Random random, long seed, int overlay) {
        return false;
    }

}