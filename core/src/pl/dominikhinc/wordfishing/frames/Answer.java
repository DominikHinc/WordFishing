package pl.dominikhinc.wordfishing.frames;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import pl.dominikhinc.wordfishing.WordFishing;

public class Answer extends TextButton {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 240;
    private static int answerNumber = 1;

    private String answer;



    public Answer(String answer, Skin skin, WordFishing game) {
        super(answer, skin);
        this.answer = answer.toLowerCase();
        if(answerNumber > 4){
            answerNumber = 1;
        }
        init(game);
    }

    private void init(WordFishing game){
        if(answer.length()>12){
            answer = answer.substring(0,11);
            answer += "...";
            this.setText(answer);
        }
        this.setWidth(WIDTH*10);

        this.setHeight(HEIGHT);

        setOrigin(this.getWidth()/2,this.getHeight()/2);

        setPositions(game);
        answerNumber++;

    }

    private void setPositions(WordFishing game) {
        switch (answerNumber){
            case 1:
                this.setPosition(game.SCREEN_WIDTH/12,game.SCREEN_HEIGHT/4);break;
            case 2:
                this.setPosition(game.SCREEN_WIDTH - game.SCREEN_WIDTH/12 - this.getWidth(),game.SCREEN_HEIGHT/4);break;
            case 3:
                this.setPosition(game.SCREEN_WIDTH/12,game.SCREEN_HEIGHT/12);break;
            case 4:
                this.setPosition(game.SCREEN_WIDTH - game.SCREEN_WIDTH/12 - this.getWidth(), game.SCREEN_HEIGHT/12);break;
        }

    }
}
