package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;

public class BookChooseScreen extends AbstractScreen {

        //TODO Make abstract screen that from which This game and help screen will extend

        private Image bgImage;
        private GoBackButtonCreator goBackButtonCreator;
        private Button goBackButton;
        private List<String> list;
        private ScrollPane scrollPane;
        private Skin skin;
        private TextButton chooseButton;

        public BookChooseScreen(WordFishing game) {
            super(game);
            initList();
            initChooseButton();
        }

    private void initChooseButton() {
            chooseButton = new TextButton("Wybiez" ,game.getSkin());
            chooseButton.setHeight(170);
            chooseButton.setWidth(game.SCREEN_WIDTH - game.SCREEN_WIDTH / 4);
            chooseButton.setPosition(game.SCREEN_WIDTH / 8,game.SCREEN_HEIGHT / 8 - chooseButton.getHeight());
            stage.addActor(chooseButton);
            chooseButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y){
                    System.out.println(list.getSelected());
                    game.setScreen(new GameScreen (game));
                }
            });
    }

    private void initList() {
            this.skin = game.getSkin();
            list = new List<String>(skin, "plain");
            String[] strings = new String[50];
            for (int i = 0, k = 0; i < 50; i++) {
                strings[k++] = "String: " + i;

            }
            list.setItems(strings);

            scrollPane = new ScrollPane(list);
            //scrollPane.setBounds(0, 0, game.SCREEN_WIDTH, game.SCREEN_HEIGHT);
            scrollPane.setHeight(game.SCREEN_HEIGHT - game.SCREEN_HEIGHT / 4);
            scrollPane.setWidth(game.SCREEN_WIDTH - game.SCREEN_WIDTH / 4);
            scrollPane.setPosition(game.SCREEN_WIDTH / 8 , game.SCREEN_HEIGHT / 8);
            scrollPane.setSmoothScrolling(true);
            scrollPane.setTransform(true);
            //scrollPane.setScale(0.9f);
            /*
            list.setHeight(game.SCREEN_HEIGHT - game.SCREEN_HEIGHT / 4);
            list.setWidth(game.SCREEN_WIDTH - game.SCREEN_WIDTH / 4);
            list.setPosition(game.SCREEN_WIDTH / 8 , game.SCREEN_HEIGHT / 8);
              */

            stage.addActor(scrollPane);
        }

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


