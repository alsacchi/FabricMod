package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.andrealeet.it.fabricmod.ModClient;
import eu.andrealeet.it.fabricmod.listeners.RenderBlockEntityEvent;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(BlockEntityRenderDispatcher.class)
public class BlockEntityRenderDispatcherMixin {
    
    @Inject(at = {@At("HEAD")},
        method = {"render(Lnet/minecraft/block/entity/BlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V"},
        cancellable = true)
    private <E extends BlockEntity> void onRender(E blockEntity, float number, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, CallbackInfo ci) {
        RenderBlockEntityEvent event = new RenderBlockEntityEvent(blockEntity);
        ModClient.INSTANCE.getEventManager().fire(event);
        if(event.isCancelled()) {
            ci.cancel();
        }
    }
}