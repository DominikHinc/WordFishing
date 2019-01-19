package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import pl.dominikhinc.wordfishing.WordFishing;



public class MenuButtonCreator {

    private TextButton grajButton;
    /*
    private TextButton.TextButtonStyle textButtonStyle;
    private TextureRegionDrawable buttonUp;
    private TextureRegionDrawable buttonDown;
    */
    private Skin skin;
    private int whichLine = 5;

    public TextButton createButton(String text , WordFishing game){
        /*buttonUp = new TextureRegionDrawable(new TextureRegion(new Texture("Guzik_Menu_Up.png")));
        buttonDown = new TextureRegionDrawable(new TextureRegion(new Texture("Guzik_Menu_Down.png")));
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getFont();
        textButtonStyle.up = buttonUp;
        textButtonStyle.down = buttonDown;
        textButtonStyle.fontColor = Color.BLACK;*/
        skin = new Skin(Gdx.files.internal("glassy-ui.json"));
        grajButton = new TextButton(text,skin);
        grajButton.setHeight(85*2);
        grajButton.setWidth(306*2);
        grajButton.setOrigin(grajButton.getWidth() / 2 , grajButton.getHeight() / 2);
        grajButton.setPosition(game.SCREEN_WIDTH / 2 - grajButton.getWidth() / 2 , game.SCREEN_HEIGHT - whichLine * game.SCREEN_HEIGHT / 10);
        whichLine++;
        return grajButton;
    }
}
