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
    private Image logo;
    private TextButton playButton;
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
        initLogo();
    }

    private void initLogo() {
        logo = new Image(new Texture("Logo.png"));
        logo.setSize(419,202);
        logo.setOrigin(logo.getWidth()/2,logo.getHeight()/2);
        logo.setPosition(game.SCREEN_WIDTH/2 - logo.getWidth()/2,game.SCREEN_HEIGHT-game.SCREEN_HEIGHT/4 - logo.getHeight()/2);
        stage.addActor(logo);
    }


    private void initButtons() {
        buttonCreator = new MenuButtonCreator();
        playButton = buttonCreator.createButton("Rozpocznij", game);
        optionsButton = buttonCreator.createButton("Opcje" , game);
        helpButton = buttonCreator.createButton("Pomoc" , game);
        stage.addActor(playButton);
        stage.addActor(optionsButton);
        stage.addActor(helpButton);
    }
    private void initButtonsListeners() {
        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new BookChooseScreen(game));
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
