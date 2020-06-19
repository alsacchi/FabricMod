package eu.andrealeet.it.fabricmod.mixin;

import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import eu.andrealeet.it.fabricmod.gui.HackOptionsGUI;

@Mixin(SettingsScreen.class)
public abstract class InjectGui extends Screen {
	public InjectGui(Text title) {
		super(title);
	}
	
	@Inject(at = @At("TAIL"), method = "init()V")
	private void init(CallbackInfo info) {
		this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 138, 200, 20, I18n.translate("Open Menu"), (buttonWidget) -> {
			this.minecraft.openScreen(new HackOptionsGUI(new LiteralText("HACK GUI")));
		 }));
	}
}

