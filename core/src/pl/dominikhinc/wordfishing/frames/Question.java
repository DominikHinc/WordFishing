package pl.dominikhinc.wordfishing.frames;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import pl.dominikhinc.wordfishing.WordFishing;

public class Question extends TextButton {
    public static final int WIDTH = 85;
    public static final int HEIGHT = 400;

    private String question;
    private String answer;

    public Question(String question,String answer, Skin skin, WordFishing game) {
        super(question, skin);
        this.question = question;//.toLowerCase();
        this.answer = answer;//.toLowerCase();
        init(game);

    }

    private void init(WordFishing game) {
        if(this.getText().length() > 10){
            this.setWidth(WIDTH*this.getText().length());
        }else{
           this.setWidth(WIDTH*10);
        }

        this.setHeight(HEIGHT );
        this.setOrigin(WIDTH / 2 , HEIGHT / 2);
        this.setPosition(game.SCREEN_WIDTH / 2 - this.getWidth() / 2 , game.SCREEN_HEIGHT / 1.7f);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
