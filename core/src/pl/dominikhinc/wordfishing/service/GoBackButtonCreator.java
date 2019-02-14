package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import pl.dominikhinc.wordfishing.WordFishing;

public class GoBackButtonCreator {

    private Button buttonToReturn;
    private Button.ButtonStyle buttonStyle;
    private TextureRegionDrawable buttonUp;


    public Button createButton(WordFishing game){
        buttonUp = new TextureRegionDrawable(new TextureRegion(game.getGoBackButton()));
        buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = buttonUp;
        buttonStyle.down = buttonUp;
        buttonToReturn = new Button(buttonStyle);
        buttonToReturn.setHeight(66*2);
        buttonToReturn.setWidth(93*2);
        buttonToReturn.setOrigin(buttonToReturn.getWidth() / 2 , buttonToReturn.getHeight() / 2);
        buttonToReturn.setPosition(buttonToReturn.getWidth() / 3, game.SCREEN_HEIGHT-buttonToReturn.getHeight() * 1.5f);

        return buttonToReturn;
    }
}
