package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;
import pl.dominikhinc.wordfishing.service.IdCalculate;

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
        helpLabel = new Label("Nauczanie kompleksowe polega na \ndługotrwałym nauczeniu się danego \nzestawu słówek, dletego po \nwybraniu tej opcji aplikacja będzie \nprzypominać o regularnej nauce." , labelStyle);
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
