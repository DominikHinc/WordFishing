package pl.dominikhinc.wordfishing.screens;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.MenuButtonCreator;

public class MenuScreen extends AbstractScreen {

    private Image bgImg;
    private TextButton grajButton;
    private TextButton optionsButton;
    private TextButton helpButton;
    private MenuButtonCreator buttonCreator;

    public MenuScreen(WordFishing game){
        super(game);

    }

    @Override
    protected void init(){
        initBg();
        initButtons();
        initButtonsListeners();
    }



    private void initButtons() {
        buttonCreator = new MenuButtonCreator();
        grajButton = buttonCreator.createButton("Rozpocznij", game);
        optionsButton = buttonCreator.createButton("Opcje" , game);
        helpButton = buttonCreator.createButton("Pomoc" , game);
        stage.addActor(grajButton);
        stage.addActor(optionsButton);
        stage.addActor(helpButton);
    }
    private void initButtonsListeners() {
        grajButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameScreen(game));
            }
            });
        optionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new OptionsScreen(game));
            }
        });
        helpButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new HelpScreen(game));
            }
        });


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

    private void initBg() {
        //TODO add better bgImage
        bgImg = new Image(new Texture("Temporary.Menu.Background.png"));
        stage.addActor(bgImg);
    }
}
