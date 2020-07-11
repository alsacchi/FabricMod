package eu.andrealeet.it.fabricmod.gui;

import java.util.Collection;

import eu.andrealeet.it.fabricmod.ModClient;

import eu.andrealeet.it.fabricmod.ModsManager;

import eu.andrealeet.it.fabricmod.mod.Mod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class ModOptionsGUI extends Screen {
    
    private ModsManager ModsManager = ModClient.INSTANCE.getMods();

    public ModOptionsGUI(Text title) {
        super(title);
    }

    public void init() {

        Collection<Mod> Mods = ModsManager.getMods();
        int i = 0;
        for(Mod Mod : Mods){ 
            this.addButton(new ButtonWidget(this.width / 2 - 105 + i % 2 * 110, this.height / 6 - 12 + 24 * (i >> 1), 100, 20, new LiteralText(Mod.getName()), (buttonWidget) -> {
                Mod.setEnabled(!Mod.isEnabled());
                buttonWidget.setMessage(new LiteralText(Mod.getName()));
            }));
            i++;
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrixStack);
        this.drawCenteredString(matrixStack, client.textRenderer, this.title.asString(), this.width / 2, 15, 16777215);
        super.render(matrixStack, mouseX, mouseY, delta);
    }

}