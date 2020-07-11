package eu.andrealeet.it.fabricmod.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import eu.andrealeet.it.fabricmod.ModClient;

import eu.andrealeet.it.fabricmod.mods.Timer;
import net.minecraft.client.render.RenderTickCounter;

@Mixin(RenderTickCounter.class)
public abstract class RenderTickCounterMixin {

    @Shadow
    private float lastFrameDuration;
    
    @Inject(at = {@At(value = "FIELD",
		target = "Lnet/minecraft/client/render/RenderTickCounter;prevTimeMillis:J",
		opcode = Opcodes.PUTFIELD,
		ordinal = 0)}, method = {"beginRenderTick(J)I"})
	public void onBeginRenderTick(long timeMillis, CallbackInfoReturnable<Integer> ci) {
        Timer timerMod = ModClient.INSTANCE.getMods().timerMod;
		lastFrameDuration *= timerMod.getTimerSpeed();
	}
    
}