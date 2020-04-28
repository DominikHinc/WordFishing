package pl.dominikhinc.wordfishing.screens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.AssetMenager;
import pl.dominikhinc.wordfishing.service.MenuButtonCreator;

public class MenuScreen extends AbstractScreen {

    private Image bgImg;
    private Image logo;
    private TextButton playButton;
    private TextButton contactButton;
    private TextButton helpButton;
    private MenuButtonCreator buttonCreator;
    private Label creditLabel;
    private TextButton creditsButton;
    private AssetMenager assetMenager;
    private static boolean WASALREADYLOADED = false;

    public MenuScreen(WordFishing game){
        super(game);
    }

    @Override
    protected void init(){
        //if(WASALREADYLOADED == false){
         //   assetMenager = new AssetMenager(game);
         //   WASALREADYLOADED = true;

        //}

        Gdx.input.setOnscreenKeyboardVisible(false);
        game.setTextInput(game.getPreferences().getBoolean(game.textInputPreferences));
        initBg();
        initButtons();
        initButtonsListeners();
        initLogo();
        initCreditLabel();
        initCreditsButton();
    }

    private void initCreditsButton() {
        creditsButton = new TextButton("Credits",game.getSkin(),"credits");
        creditsButton.setSize(WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT/24);
        creditsButton.setPosition(WordFishing.SCREEN_WIDTH - creditsButton.getWidth(),0);
        creditsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new CreditsScreen(game));
            }
        });
        stage.addActor(creditsButton);
    }

    private void initCreditLabel() {
        creditLabel = new Label("Made by: Dominik Hinc",game.getSkin(),"test");
        stage.addActor(creditLabel);
    }

    private void initLogo() {
        logo = new Image(game.getLogo());
        //logo.setSize(419,202);
        logo.setSize(838*WordFishing.SCALE,404*WordFishing.SCALE);
        logo.setOrigin(logo.getWidth()/2,logo.getHeight()/2);
        logo.setPosition(game.SCREEN_WIDTH/2 - logo.getWidth()/2,game.SCREEN_HEIGHT-game.SCREEN_HEIGHT/4 - logo.getHeight()/2);
        Action a = Actions.rotateBy(12,5);
        Action b = Actions.rotateBy(-12,5);
        Action c = Actions.rotateBy(-12,5);
        Action d = Actions.rotateBy(12,5);
        logo.addAction(Actions.forever(Actions.sequence(a,b,c,d)));
        stage.addActor(logo);
    }


    private void initButtons() {
        buttonCreator = new MenuButtonCreator();
        playButton = buttonCreator.createButton("Rozpocznij", game);
        contactButton = buttonCreator.createButton("Kontakt" , game);
        helpButton = buttonCreator.createButton("Dostosuj" , game);
        stage.addActor(playButton);
        stage.addActor(contactButton);
        stage.addActor(helpButton);
    }
    private void initButtonsListeners() {
        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new BookChooseScreen(game));

            }
            });
        contactButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
               game.setScreen(new ContactScreen(game));
            }
        });
        helpButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GraphicChangeScreen(game));
            }
        });


    }
    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        /*
        for(int i = 0; i < 1080;i+=64){
            for(int j = 0; j < 1920;j+=64){
                shapeRenderer.setColor(currentColor.r *i*j/50000,currentColor.g*i*j/50000,currentColor.b*i*j/50000,currentColor.a);
                shapeRenderer.rect(i,j,64,64);
            }
        }*/

        spriteBatch.begin();
        stage.act();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
    }

    private void initBg() {
        //TODO add better bgImage
        //bgImg = new Image(new Texture("Temporary.Menu.Background.png"));
       //bgImg.setSize(WordFishing.SCREEN_WIDTH,WordFishing.SCREEN_HEIGHT);
        //stage.addActor(bgImg);
    }
}
