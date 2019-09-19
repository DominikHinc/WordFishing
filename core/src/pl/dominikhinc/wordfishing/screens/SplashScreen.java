package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.AssetMenager;

public class SplashScreen extends AbstractScreen {
    private AssetMenager assetMenager;
    private Image splashImg;
    private ProgressBar progressBar;
    //private AssetMenager assetMenager;

    public SplashScreen(final WordFishing game) {
        super(game);

        assetMenager = new AssetMenager(game);
        game.setAssetMenager(assetMenager);
        assetMenager.load();
        //assetMenager.assetManager.finishLoading();


        /*Timer.schedule(new Task() {
            @Override
            public void run() {
                game.setScreen(new MenuScreen(game));
            }
        }, 0.5f);
        //assetMenager = new AssetMenager(game);*/
    }

    @Override
    protected void init() {
        splashImg = new Image(new Texture("Splash_Screen.png"));
        splashImg.setSize(WordFishing.SCREEN_WIDTH,WordFishing.SCREEN_HEIGHT);
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        assetMenager.assetManager.update();
        if(assetMenager.assetManager.isFinished()){
            assetMenager.setTextures(game);
            assetMenager.setSkins(game);
            game.setScreen(new MenuScreen(game));
        }
        spriteBatch.begin();
        splashImg.draw(spriteBatch,1);
        //spriteBatch.draw(splashImg, 0, 0);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
