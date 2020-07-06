package eu.andrealeet.it.fabricmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.ClientBrandRetriever;

@Mixin(ClientBrandRetriever.class)
public class ClientBrandRetrieverMixin {
    @Inject(at = @At("RETURN"), method = "getClientModName()Ljava/lang/String;", cancellable = true)
    private static void getClientModName(CallbackInfoReturnable<String> info) {
        info.setReturnValue("\u00A7cEsperimenti di alsacchi, NON CONTIENE MODIFICHE! (https://github.com/alsacchi/fabricmod/)");
    }
}