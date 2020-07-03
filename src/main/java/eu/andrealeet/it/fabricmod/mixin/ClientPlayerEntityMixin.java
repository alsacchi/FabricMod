package eu.andrealeet.it.fabricmod.mixin;

import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import eu.andrealeet.it.fabricmod.ModClient;
import eu.andrealeet.it.fabricmod.listeners.ChatOutputEvent;
import eu.andrealeet.it.fabricmod.listeners.UpdateListener.UpdateEvent;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;


@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    @Shadow
    private ClientPlayNetworkHandler networkHandler;

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V", ordinal = 0), method = "tick()V")
    private void onTick(CallbackInfo ci) {
        ModClient.INSTANCE.getEventManager().fire(UpdateEvent.INSTANCE);
    }

    @Inject(at = @At("HEAD"), method = "sendChatMessage(Ljava/lang/String;)V", cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo ci) {
        ChatOutputEvent event = new ChatOutputEvent(message);
        ModClient.INSTANCE.getEventManager().fire(event);

        if(event.isCancelled()) {
            ci.cancel();
            return;
        }

        if(!event.isModified()) {
            return;
        }
        ChatMessageC2SPacket packet = new ChatMessageC2SPacket(event.getMessage());
        networkHandler.sendPacket(packet);
        ci.cancel();
    }

    
}