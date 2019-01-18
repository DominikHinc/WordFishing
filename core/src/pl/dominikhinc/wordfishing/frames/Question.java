package pl.dominikhinc.wordfishing.frames;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import pl.dominikhinc.wordfishing.WordFishing;

public class Question extends TextButton {
    public static final int TEXTURE_WIDTH = 65;
    public static final int TEXTURE_HEIGHT = 800;

    public Question(String text, TextButtonStyle style, WordFishing game) {
        super(text, style);
        init(game);
    }

    private void init(WordFishing game) {
        this.setWidth(TEXTURE_WIDTH * this.getText().length());
        this.setHeight(TEXTURE_HEIGHT / 3);
        this.setOrigin(TEXTURE_WIDTH / 2 , TEXTURE_HEIGHT / 2);
        this.setPosition(game.SCREEN_WIDTH / 2 - this.getWidth() / 2 , game.SCREEN_HEIGHT / 2);


    }
}
