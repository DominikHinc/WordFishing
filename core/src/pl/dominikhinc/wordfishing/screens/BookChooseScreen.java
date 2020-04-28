package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.graphics.Texture;
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

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;

public class BookChooseScreen extends AbstractScreen {
    private Image bgImage;
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private Table table;
    private ScrollPane scrollPane;
    private List<String> list;
    private Skin skin;
    private TextButton chooseButton;
    private Label choosePublisherLabel;
    //private Table mcmrok, container, innerContainer, testino;
    private Image mainImage;
    private Button forward, back;
    private int bookNumber = 1;
    private static final int NUMBER_OF_BOOKS = 2;

    public BookChooseScreen(WordFishing game) {
        super(game);
        initChoosingProcess();
        //initList();

    }

    private void initChoosingProcess() {
        initTable();
        addToTable();
        //initMainImage();
        //initBackAndForwardButtons();
        //initChooseButton();
        //initLabel();
    }

    private void addToTable() {
        Image img1 = new Image(game.getMcmrok());
        Image img2 = new Image(game.getMcmRPG());
        Image img3 = new Image(game.assetManager.get("gatewayplus1.jpg",Texture.class));
        Image img4 = new Image(game.assetManager.get("gatewayplus2.jpg",Texture.class));
        Image img5 = new Image(game.assetManager.get("gatewayplus3.jpg",Texture.class));
        Image img6 = new Image(game.assetManager.get("gatewayplus4.jpg",Texture.class));
        Image img7 = new Image(game.assetManager.get("gatewayplus5.jpg",Texture.class));

        img1.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new UnitChooseScreen(game,"Repetytorium Ósmoklasisty Macmillan"));
            }
        });

        img2.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new UnitChooseScreen(game,"Repetytorium do szkół ponadgimnazjalnych MacMillan"));
            }
        });

        img6.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new UnitChooseScreen(game,"Gateway Plus 4"));
            }
        });
        img5.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new UnitChooseScreen(game,"Gateway Plus 3"));
            }
        });
        /*img7.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new UnitChooseScreen(game,"Testy"));
            }
        });*/
        float width = 376*WordFishing.SCALE;
        float height = 536*WordFishing.SCALE;
        float pad = 60*WordFishing.SCALE;
        table.add(img1).fillX().left().size(width,height).pad(pad,0,0,0);
        table.add(img2).fillX().right().size(width,height).pad(pad,pad,0,0);
        table.row();
        table.add(img3).fillX().left().size(width,height).pad(pad,0,0,0);
        table.add(img4).fillX().right().size(width,height).pad(pad,pad,0,0);
        table.row();
        table.add(img5).fillX().left().size(width,height).pad(pad,0,0,0);
        table.add(img6).fillX().right().size(width,height).pad(pad,pad,0,0);
        table.row();
        table.add(img7).fillX().left().size(width,height).pad(pad,0,0,0);
        table.row();

    }

    private void initTable() {
        table = new Table(game.getSkin());
        stage.addActor(table);
        //table.setFillParent(true);
        scrollPane = new ScrollPane(table);
        scrollPane.setBounds(110*WordFishing.SCALE,220*WordFishing.SCALE,WordFishing.SCREEN_WIDTH - 220*WordFishing.SCALE,WordFishing.SCREEN_HEIGHT - 440*WordFishing.SCALE);
        stage.addActor(scrollPane);

        //table.pad(1000,0,0,0);
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
                    case 1:game.setScreen(new UnitChooseScreen(game,"Repetytorium Ósmoklasisty Macmillan"));break;
                    case 2:game.setScreen(new UnitChooseScreen(game,"Repetytorium do szkół ponadgimnazjalnych MacMillan"));break;
                    //case 3:game.setScreen(new UnitChooseScreen (game,"Testy"));break;
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
                game.setScreen(new UnitChooseScreen (game,"Repetytorium Ósmoklasisty Macmillan"));
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
