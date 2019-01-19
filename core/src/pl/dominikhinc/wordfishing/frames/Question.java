package pl.dominikhinc.wordfishing.frames;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import pl.dominikhinc.wordfishing.WordFishing;

public class Question extends TextButton {
    public static final int TEXTURE_WIDTH = 65;
    public static final int TEXTURE_HEIGHT = 800;

    private String question;
    private String answer;

    public Question(String text, Skin skin, WordFishing game) {
        super(text, skin);
        init(game);
    }

    private void init(WordFishing game) {
        this.setWidth(TEXTURE_WIDTH * this.getText().length());
        this.setHeight(TEXTURE_HEIGHT / 3);
        this.setOrigin(TEXTURE_WIDTH / 2 , TEXTURE_HEIGHT / 2);
        this.setPosition(game.SCREEN_WIDTH / 2 - this.getWidth() / 2 , game.SCREEN_HEIGHT / 2);
        question = "Chomik";
        answer = "Hamster";
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
