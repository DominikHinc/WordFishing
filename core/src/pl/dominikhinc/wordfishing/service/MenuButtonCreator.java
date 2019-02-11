package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.screens.MenuScreen;


public class MenuButtonCreator {

    private TextButton grajButton;
    private Skin skin;
    private WordFishing game;
    private int whichLine = 5;

    public TextButton createButton(String text , WordFishing game){
        skin = game.getSkin();
        this.game = game;
        grajButton = new TextButton(text,skin);
        grajButton.setHeight(85*2);
        grajButton.setWidth(306*2);
        grajButton.setOrigin(grajButton.getWidth() / 2 , grajButton.getHeight() / 2);
        grajButton.setPosition(game.SCREEN_WIDTH / 2 - grajButton.getWidth() / 2 , game.SCREEN_HEIGHT - whichLine * game.SCREEN_HEIGHT / 10);
        whichLine++;
        return grajButton;
    }

}
