package eu.andrealeet.it.fabricmod.mixin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.andrealeet.it.fabricmod.HackClient;
import eu.andrealeet.it.fabricmod.hacks.TimerHack;
import net.minecraft.client.render.RenderTickCounter;

@Mixin(RenderTickCounter.class)
public abstract class RenderTickCounterMixin {

    @Shadow
    private float lastFrameDuration;
    
    @Inject(at = {@At(value = "FIELD",
		target = "Lnet/minecraft/client/render/RenderTickCounter;prevTimeMillis:J",
		opcode = Opcodes.PUTFIELD,
		ordinal = 0)}, method = {"beginRenderTick(J)V"})
	public void onBeginRenderTick(long long_1, CallbackInfo ci) {
        TimerHack timerHack = HackClient.INSTANCE.getHax().timerHack;
		lastFrameDuration *= timerHack.getTimerSpeed();
	}
    
}