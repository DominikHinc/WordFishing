package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import pl.dominikhinc.wordfishing.WordFishing;

public class SplashScreen extends AbstractScreen {

    private Texture splashImg;

    public SplashScreen(final WordFishing game) {
        super(game);

        Timer.schedule(new Task() {
            @Override
            public void run() {
                game.setScreen(new MenuScreen(game));
            }
        }, 3);
    }

    @Override
    protected void init() {
        // TODO implement better assets loading when game grows
        splashImg = new Texture("Temporary_Splash_Screen.png");
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        spriteBatch.draw(splashImg, 0, 0);
        spriteBatch.end();
    }



}
