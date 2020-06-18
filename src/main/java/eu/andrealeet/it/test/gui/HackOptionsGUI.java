package eu.andrealeet.it.test.gui;

import java.util.Collection;

import eu.andrealeet.it.test.HackClient;
import eu.andrealeet.it.test.HacksManager;
import eu.andrealeet.it.test.hack.Hack;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;

public class HackOptionsGUI extends Screen {
    
    private HacksManager hacksManager = HackClient.INSTANCE.getHax();

    public HackOptionsGUI(Text title) {
        super(title);
    }

    public void init() {

        Collection<Hack> hacks = hacksManager.getHacks();
        int i = 0;
        for(Hack hack : hacks){ 
            this.addButton(new ButtonWidget(this.width / 2 - 105 + i % 2 * 110, this.height / 6 - 12 + 24 * (i >> 1), 100, 20, I18n.translate(hack.getName()), (buttonWidget) -> {
                hack.setEnabled(!hack.isEnabled());
                buttonWidget.setMessage(hack.getName());
            }));
            i++;
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
     }

    @Override
    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        this.drawCenteredString(this.font, this.title.asFormattedString(), this.width / 2, 15, 16777215);
        super.render(mouseX, mouseY, delta);
    }

}