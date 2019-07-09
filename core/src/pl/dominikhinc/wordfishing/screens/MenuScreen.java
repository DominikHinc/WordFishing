package pl.dominikhinc.wordfishing.screens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.AssetMenager;
import pl.dominikhinc.wordfishing.service.MenuButtonCreator;
import pl.dominikhinc.wordfishing.service.NotificationUpdateService;

public class MenuScreen extends AbstractScreen {

    private Image bgImg;
    private Image logo;
    private TextButton playButton;
    private TextButton contactButton;
    private TextButton helpButton;
    private MenuButtonCreator buttonCreator;
    private AssetMenager assetMenager;

    private static boolean WASALREADYLOADED = false;

    public MenuScreen(WordFishing game){
        super(game);
    }

    @Override
    protected void init(){
        if(WASALREADYLOADED == false){
            assetMenager = new AssetMenager(game);
            WASALREADYLOADED = true;

        }
        Gdx.input.setOnscreenKeyboardVisible(false);
        game.setTextInput(game.getPreferences().getBoolean(game.textInputPreferences));
        initBg();
        initButtons();
        initButtonsListeners();
        initLogo();

    }

    private void initLogo() {
        logo = new Image(game.getLogo());
        //logo.setSize(419,202);
        logo.setSize(838,404);
        logo.setOrigin(logo.getWidth()/2,logo.getHeight()/2);
        logo.setPosition(game.SCREEN_WIDTH/2 - logo.getWidth()/2,game.SCREEN_HEIGHT-game.SCREEN_HEIGHT/4 - logo.getHeight()/2);
        Action a = Actions.rotateBy(12,5);
        Action b = Actions.rotateBy(-12,5);
        Action c = Actions.rotateBy(-12,5);
        Action d = Actions.rotateBy(12,5);
        logo.addAction(Actions.sequence(a,b,c,d));
        stage.addActor(logo);
    }


    private void initButtons() {
        buttonCreator = new MenuButtonCreator();
        playButton = buttonCreator.createButton("Rozpocznij", game);
        contactButton = buttonCreator.createButton("Kontakt" , game);
        helpButton = buttonCreator.createButton("Pomoc" , game);
        stage.addActor(playButton);
        stage.addActor(contactButton);
        stage.addActor(helpButton);
    }
    private void initButtonsListeners() {
        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new PublisherChooseScreen(game));

            }
            });
        contactButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
               game.setScreen(new ContactScreen(game));
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
        stage.act();
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
