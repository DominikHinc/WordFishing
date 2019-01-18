package pl.dominikhinc.wordfishing.screens;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import pl.dominikhinc.wordfishing.WordFishing;

public class GameScreen extends AbstractScreen {

    private Image bgImage;
    private MenuScreen menuScreen;
    private Label test;

    public GameScreen(WordFishing game) {
        super(game);
    }

    @Override
    protected void init() {
        initBgImage();
        initMe
        test = new Label();
        test.setText("Rozgrywka");

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
