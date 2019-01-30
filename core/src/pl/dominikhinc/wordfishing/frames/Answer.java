package pl.dominikhinc.wordfishing.frames;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import pl.dominikhinc.wordfishing.WordFishing;

public class Answer extends TextButton {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 240;

    private String answer;
    private boolean isCorrect = false;

    public Answer(String answer, Skin skin, WordFishing game) {
        super(answer, skin);
        this.answer = answer;//.toLowerCase();
        init(game);
    }

    private void init(WordFishing game){
        String[] shownText_temp =  new String[2];
        String shownText;
        if(answer.length()>15){
            shownText_temp[0] = answer.substring(0,12);
            shownText_temp[1] = answer.substring(13 ,answer.length());
            shownText = shownText_temp[0] +"\n"+ shownText_temp[1];
            this.setText(shownText);
        }
        this.setWidth(WIDTH*10);

        this.setHeight(HEIGHT);

        setOrigin(this.getWidth()/2,this.getHeight()/2);




    }

    public void setPositions(WordFishing game , int i) {
        switch (i){
            case 0:
                this.setPosition(game.SCREEN_WIDTH/12,game.SCREEN_HEIGHT/4);break;
            case 1:
                this.setPosition(game.SCREEN_WIDTH - game.SCREEN_WIDTH/12 - this.getWidth(),game.SCREEN_HEIGHT/4);break;
            case 2:
                this.setPosition(game.SCREEN_WIDTH/12,game.SCREEN_HEIGHT/12);break;
            case 3:
                this.setPosition(game.SCREEN_WIDTH - game.SCREEN_WIDTH/12 - this.getWidth(), game.SCREEN_HEIGHT/12);break;
        }

    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer){
        this.answer = answer;
        this.setText(answer);
        String[] shownText_temp =  new String[2];
        String shownText;
        if(answer.length()>12){
            shownText_temp[0] = answer.substring(0,11);
            shownText_temp[1] = answer.substring(11 ,answer.length());
            shownText = shownText_temp[0] +"\n"+ shownText_temp[1];
            this.setText(shownText);
        }else {
            this.setText(answer);
        }
        }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setisCorrect(boolean aTrue) {
        isCorrect = aTrue;
    }

}



