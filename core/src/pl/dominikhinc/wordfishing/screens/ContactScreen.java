package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;

public class ContactScreen extends AbstractScreen {

    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private Label helpLabel;


    public ContactScreen(WordFishing game) {
        super(game);
    }

    @Override
    protected void init() {
        initBgImage();
        initBackToMenuButton();
        initHelpLabel();

    }


    private void initHelpLabel() {
        helpLabel = new Label("Kontakt:\nwordfishingkontakt@gmail.com\n\n\nAplikacja ma na celu pomoc w \nnauce słówek z języka angielskiego.\n\n\nZestawy słówek zaczerpnięte są\nz książek znajdujących się obecnie\nw programie nauczania." , game.getSkin());
        //helpLabel = new Label("ąśęńółĄŚĘÓŃŁćĆŻżŹźaAbBcCdDeEfFgGhHiIjJkKlLmMnNoOuUpPrRsStTwWxXyYzZ",game.getSkin(),"test");
        helpLabel.setPosition(100*WordFishing.SCALE,game.SCREEN_HEIGHT - helpLabel.getHeight() - 400*WordFishing.SCALE);
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
