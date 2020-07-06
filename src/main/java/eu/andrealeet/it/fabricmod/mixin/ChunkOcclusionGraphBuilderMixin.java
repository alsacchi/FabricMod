package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.andrealeet.it.fabricmod.ModClient;
import eu.andrealeet.it.fabricmod.listeners.SetOpaqueCubeEvent;
import net.minecraft.client.render.chunk.ChunkOcclusionDataBuilder;
import net.minecraft.util.math.BlockPos;

@Mixin(ChunkOcclusionDataBuilder.class)
public class ChunkOcclusionGraphBuilderMixin {

    @Inject(at = {@At("HEAD")},
            method = {"markClosed(Lnet/minecraft/util/math/BlockPos;)V"},
            cancellable = true)
    private void onMarkClosed(BlockPos pos, CallbackInfo ci) {
        SetOpaqueCubeEvent event = new SetOpaqueCubeEvent();
        ModClient.INSTANCE.getEventManager().fire(event);

        if(event.isCancelled()) {
            ci.cancel();
        }
    }

    
}