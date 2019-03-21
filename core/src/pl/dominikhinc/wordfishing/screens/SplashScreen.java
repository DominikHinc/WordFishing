package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.AssetMenager;

public class SplashScreen extends AbstractScreen {

    private Texture splashImg;
    //private AssetMenager assetMenager;

    public SplashScreen(final WordFishing game) {
        super(game);

        Timer.schedule(new Task() {
            @Override
            public void run() {
                game.setScreen(new MenuScreen(game));
            }
        }, 0.5f);
        //assetMenager = new AssetMenager(game);
    }

    @Override
    protected void init() {
        splashImg = new Texture("Splash_Screen.png");
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        spriteBatch.draw(splashImg, 0, 0);
        spriteBatch.end();
    }



}
