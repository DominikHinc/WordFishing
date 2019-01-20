package pl.dominikhinc.wordfishing.frames;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import pl.dominikhinc.wordfishing.WordFishing;

public class Question extends TextButton {
    public static final int WIDTH = 65;
    public static final int HEIGHT = 500;

    private String question;
    private String answer;

    public Question(String question,String answer, Skin skin, WordFishing game) {
        super(question, skin);
        this.question = question;
        this.answer = answer;
        init(game);

    }

    private void init(WordFishing game) {
        if(this.getText().length() > 10){
            this.setWidth(WIDTH*this.getText().length());
        }else{
            this.setWidth(WIDTH*10);
        }

        this.setHeight(HEIGHT / 3);
        this.setOrigin(WIDTH / 2 , HEIGHT / 2);
        this.setPosition(game.SCREEN_WIDTH / 2 - this.getWidth() / 2 , game.SCREEN_HEIGHT / 2);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
