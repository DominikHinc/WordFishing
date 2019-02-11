package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;

public class OptionsScreen extends AbstractScreen {

    //TODO Make abstract screen that from wihich This game and help screen will extend

    private Image bgImage;
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private Label textInputLabel;
    private CheckBox textInputBox;

    public OptionsScreen(WordFishing game) {
        super(game);
    }

    @Override
    protected void init() {
        initBgImage();
        initBackToMenuButton();
        initTextInputLabel();
        initTextInputCheckBox();
    }

    private void initTextInputCheckBox() {
        textInputBox = new CheckBox("   Sprawdzanie tekstowe",game.getSkin());
        textInputBox.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/6);
        textInputBox.getImage().setScale(3);
        textInputBox.setChecked(game.isTextInput());
        textInputBox.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                game.setTextInput(!game.isTextInput());
                /*if(game.isTextInput()){
                    System.out.println("True");
                }else{
                    System.out.println("False");
                }*/
            }
        });
        stage.addActor(textInputBox);
    }

    private void initTextInputLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getSkin().getFont("font");
        textInputLabel = new Label("Sprawdzanie tekstowe" , labelStyle);
        //textInputLabel.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/6);
        //stage.addActor(textInputLabel);
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
        //TODO Make better background
        bgImage = new Image(new Texture("Temporary.Menu.Background.png"));
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
