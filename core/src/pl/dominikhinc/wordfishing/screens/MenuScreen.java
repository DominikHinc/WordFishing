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
    /*private BitmapFont font;
    private TextButton.TextButtonStyle textButtonStyle;
    private TextureRegionDrawable buttonUp;
    private TextureRegionDrawable buttonDown;*/

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
        /*buttonUp = new TextureRegionDrawable(new TextureRegion(new Texture("Guzik_Menu_Up.png")));
        buttonDown = new TextureRegionDrawable(new TextureRegion(new Texture("Guzik_Menu_Down.png")));
        font = new BitmapFont(Gdx.files.internal("Font_2.fnt"));
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.up = buttonUp;
        textButtonStyle.down = buttonDown;
        textButtonStyle.fontColor = Color.BLACK;
        grajButton = new TextButton("Rozpocznij",textButtonStyle);
        grajButton.setHeight(85*2);
        grajButton.setWidth(306*2);
        grajButton.setOrigin(grajButton.getWidth() / 2 , grajButton.getHeight() / 2);
        grajButton.setPosition(game.SCREEN_WIDTH / 2 - grajButton.getWidth() / 2 , game.SCREEN_HEIGHT - 5 * game.SCREEN_HEIGHT / 10);*/
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
        //TODO add bettr bgImage
        bgImg = new Image(new Texture("Temporary.Menu.Background.png"));
        stage.addActor(bgImg);
    }
}
