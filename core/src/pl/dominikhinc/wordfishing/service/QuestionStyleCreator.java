package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import pl.dominikhinc.wordfishing.WordFishing;

public class QuestionStyleCreator {

    private TextButton.TextButtonStyle textButtonStyle;
    private TextureRegionDrawable buttonTexture;


    public TextButton.TextButtonStyle createQuestionStyle(WordFishing game){
        buttonTexture = new TextureRegionDrawable(new TextureRegion(new Texture("Question_Frame.png")));
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = game.getFont();
        textButtonStyle.up = buttonTexture;
        textButtonStyle.down = buttonTexture;
        textButtonStyle.fontColor = Color.BLACK;

        return textButtonStyle;

    }
}
