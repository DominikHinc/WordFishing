package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;

public class PublisherChooseScreen extends AbstractScreen {
    private Image bgImage;
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private List<String> list;
    private ScrollPane scrollPane, scrollpane;
    private Skin skin;
    private TextButton chooseButton;
    private Label choosePublisherLabel;
    //private Table mcmrok, container, innerContainer, testino;
    private Image mainImage;
    private Button forward, back;
    private int bookNumber = 1;
    private static final int NUMBEROFBOOKS = 3;

    public PublisherChooseScreen(WordFishing game) {
        super(game);
        initCoosingProcess();
        //initList();

    }

    private void initCoosingProcess() {
        initMainImage();
        initBackAndForwardButtons();
        initChooseButton();
        //initLabel();
    }

    private void initMainImage() {
        mainImage = new Image(game.getMcmrok());
        mainImage.setSize(750,1065);
        mainImage.setPosition(game.SCREEN_WIDTH/2-mainImage.getWidth()/2,game.SCREEN_HEIGHT/4);
        stage.addActor(mainImage);

    }
    private void initBackAndForwardButtons() {
        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(game.getGoBackerButton()));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(game.getGoBackerButton()));
        forward = new Button(buttonStyle);
        forward.setSize(160,160);
        forward.setPosition(game.SCREEN_WIDTH-forward.getWidth(),game.SCREEN_HEIGHT/2);
        stage.addActor(forward);
        forward.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                if(bookNumber+1 > NUMBEROFBOOKS){
                    bookNumber = 1;
                }else{
                    bookNumber++;
                }
                updateBook();
            }
        });

        back = new Button(buttonStyle);
        back.setSize(160,160);
        back.setPosition(back.getWidth(),game.SCREEN_HEIGHT/2+back.getHeight());
        back.setTransform(true);
        back.rotateBy(180);
        stage.addActor(back);
        back.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                if(bookNumber-1 < 1){
                    bookNumber = 2;
                }else{
                    bookNumber--;
                }

                updateBook();
            }
        });

    }
    private void updateBook(){
        switch(bookNumber){
            case 1:mainImage.setDrawable(new SpriteDrawable(new Sprite(game.getMcmrok())));break;
            case 2:mainImage.setDrawable(new SpriteDrawable(new Sprite(game.getLogo()))) ;break;
            case 3:mainImage.setDrawable(new SpriteDrawable(new Sprite(game.getTempToDelete())));break;
            default: ;break;
        }

    }

    private void initLabel() {
        choosePublisherLabel = new Label("Wybierz Książkę",game.getSkin());
        choosePublisherLabel.setPosition(game.SCREEN_WIDTH-choosePublisherLabel.getWidth()-330,game.SCREEN_HEIGHT-choosePublisherLabel.getHeight()-100);
        choosePublisherLabel.setFontScale(1.5f);
        stage.addActor(choosePublisherLabel);
    }

    private void initChooseButton() {
        chooseButton = new TextButton("Wybierz" ,game.getSkin());
        chooseButton.setHeight(170);
        chooseButton.setWidth(game.SCREEN_WIDTH - game.SCREEN_WIDTH / 4);
        chooseButton.setPosition(game.SCREEN_WIDTH / 8,game.SCREEN_HEIGHT / 8 - chooseButton.getHeight());
        stage.addActor(chooseButton);
        chooseButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                switch(bookNumber){
                    case 1:game.setScreen(new BookChooseScreen (game,"Repetytorium Ósmoklasisty Macmillan"));break;
                    case 2:game.setScreen(new BookChooseScreen (game,"Testy"));break;
                    case 3:game.setScreen(new BookChooseScreen (game,"Testy"));break;
                    default: ;break;
                }

            }
        });
    }

    /*private void initList() {
        mcmrok = new Image(game.getMcmrok());
        mcmrok.setSize(300,425);
        mcmrok.setPosition(game.SCREEN_WIDTH/8.5f,game.SCREEN_HEIGHT/1.75f);
        mcmrok.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new BookChooseScreen (game,"Repetytorium Ósmoklasisty Macmillan"));
            }
        });

        stage.addActor(mcmrok);

        /*container = new Table();
        container.setWidth(900);
        container.setHeight(1600);
        container.setPosition(50,500);

        mcmrok = new Table(game.getSkin());
        mcmrok.add(new Image(game.getMcmrok()));
        mcmrok.add(new Image(game.getLogo()));

        scrollPane = new ScrollPane(mcmrok);
        scrollPane.setHeight(game.SCREEN_HEIGHT - game.SCREEN_HEIGHT / 4);
        scrollPane.setWidth(game.SCREEN_WIDTH - game.SCREEN_WIDTH / 4);
        scrollPane.setPosition(game.SCREEN_WIDTH / 8 , game.SCREEN_HEIGHT / 8);
        scrollPane.setSmoothScrolling(true);
        scrollPane.setTransform(true);

        stage.addActor(scrollPane);


        /*
        mcmrok = new Table(game.getSkin());
        mcmrok.add(new Image(game.getMcmrok())).expandY().fillY();
        mcmrok.add(new Label("", skin)).width(10f).expandY().fillY();// a spacer
        mcmrok.add(new Label("Look at this axe I stole!", game.getSkin())).expandY().fillY();

        testino = new Table(game.getSkin());
        testino.add(new Image(game.getLogo())).expandY().fillY();
        testino.add(new Label("", skin)).width(10f).expandY().fillY();// a spacer
        testino.add(new Label("So dagger, much pointy.", game.getSkin())).expandY().fillY();

        innerContainer = new Table();
        innerContainer.add(mcmrok).expand().fill();
        innerContainer.row();
        innerContainer.add(testino).expand().fill();
        //innerContainer.row();

        scrollpane = new ScrollPane(innerContainer);

        container.add(scrollpane).fill().expand();

        stage.addActor(scrollpane);
        */

        /*
        FileHandle dirHandle = Gdx.files.internal("data/");;
        String l = null;
        ArrayList<String> listOfFileNames = new ArrayList<String>();

        for (FileHandle entry: dirHandle.list()) {
            l = entry.name();
            listOfFileNames.add(l);
        }
        String[] listOfBooks = new String[listOfFileNames.size()];
        int i = 0;
        for(String s: listOfFileNames){
            listOfBooks[i] = s;
            i++;
        }

        this.skin = game.getSkin();
        list = new List<String>(skin, "plain");
        list.setItems(listOfBooks);

        scrollPane = new ScrollPane(list);
        scrollPane.setHeight(game.SCREEN_HEIGHT - game.SCREEN_HEIGHT / 4);
        scrollPane.setWidth(game.SCREEN_WIDTH - game.SCREEN_WIDTH / 4);
        scrollPane.setPosition(game.SCREEN_WIDTH / 8 , game.SCREEN_HEIGHT / 8);
        scrollPane.setSmoothScrolling(true);
        scrollPane.setTransform(true);

        stage.addActor(scrollPane);
    }*/

    @Override
    protected void init() {
        initBgImage();
        initBackToMenuButton();
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
        stage.act();
        spriteBatch.end();
    }

    private void update() {
    }
}
