package pl.dominikhinc.wordfishing.screens;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import pl.dominikhinc.wordfishing.WordFishing;

public class MenuScreen extends AbstractScreen {

    private Image bgImg;
    private Button grajButton;

    public MenuScreen(WordFishing game){
        super(game);
    }

    @Override
    protected void init(){
        initBg();
        initGrajButton();
    }

    private void initGrajButton() {
        grajButton = new Button()

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
