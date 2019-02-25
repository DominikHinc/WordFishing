package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;

public class HelpScreen extends AbstractScreen {

    private Image bgImage;
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private Label helpLabel;

    public HelpScreen(WordFishing game) {
        super(game);
    }

    @Override
    protected void init() {
        initBgImage();
        initBackToMenuButton();
        initHelpLabel();
    }

    private void initHelpLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getFont();
        helpLabel = new Label("Kontakt:\nwordfishingkontakt@gmail.com\n\n\nAplikacja ma na celu pomoc w \nnauce słówek z języka angielskiego.\n\n\nZestawy słówek zaczerpnięte są\nz książek znajdujących się obecnie\nw programie nauczania." , labelStyle);
        helpLabel.setPosition(100,game.SCREEN_HEIGHT - helpLabel.getHeight() - 400);
        stage.addActor(helpLabel);
    }

    private void initBackToMenuButton() {
        goBackButtonCreator = new GoBackButtonCreator();
        goBackButton = goBackButtonCreator.createButton(game);
        goBackButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(goBackButton);

    }

    private void initBgImage() {
        bgImage = new Image(game.getDefaultBg());
        stage.addActor(bgImage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
    }
}
