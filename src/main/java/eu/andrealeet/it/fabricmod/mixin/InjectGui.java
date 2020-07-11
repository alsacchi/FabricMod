package eu.andrealeet.it.fabricmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.andrealeet.it.fabricmod.gui.ModOptionsGUI;

@Mixin(OptionsScreen.class)
public abstract class InjectGui extends Screen {
	public InjectGui(Text title) {
		super(title);
	}
	
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		this.addButton(new ButtonWidget(this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, new LiteralText(""), (buttonWidget) -> {
			MinecraftClient.getInstance().openScreen(new ModOptionsGUI(new LiteralText("Experiments!")));
		}));
		
	}
}

