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
        /*
        String[] shownText_temp =  new String[2];
        String shownText;
        if(answer.length()>15){
            shownText_temp[0] = answer.substring(0,12);
            shownText_temp[1] = answer.substring(13 ,answer.length());
            shownText = shownText_temp[0] +"\n"+ shownText_temp[1];
            this.setText(shownText);
        }*/
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
        if(answer.length()>=12){
            splitText();
        }else {
            this.setText(answer);
        }
    }

    private void splitText() {
        String[] shownText_temp =  new String[2];
        String shownText = null;
        char[] answerArray = answer.toCharArray();
        int border = 10;
        String tempor = tempor = answer.substring(border,answer.length());
        if(answer.contains(" ")){
            int toBack = border;
            int toFront = border;
            while(answerArray[toBack] != ' '  && (toBack > 0)){
                toBack--;
            }
            if(tempor.contains(" ")){
                while(answerArray[toFront] != ' ' &&  toFront < answer.length()-1){
                    toFront++;
                }
            }else{
                toFront = 99;
            }

            toBack = border - toBack;
            toFront = toFront - border;
            if(toBack <= toFront){
                shownText_temp[0] = answer.substring(0,border-toBack);
                shownText_temp[1] = answer.substring(border-toBack ,answer.length());
                shownText = shownText_temp[0] +"\n"+ shownText_temp[1];
            }
            if(toFront < toBack){
                shownText_temp[0] = answer.substring(0,toFront+border);
                shownText_temp[1] = answer.substring(toFront+border ,answer.length());
                shownText = shownText_temp[0] +"\n"+ shownText_temp[1];
            }

        }else{
            shownText_temp[0] = answer.substring(0,border+1);
            shownText_temp[1] = answer.substring(border+1 ,answer.length());
            shownText = shownText_temp[0]+ "-" +"\n"+ shownText_temp[1];
        }

        this.setText(shownText);
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean aTrue) {
        isCorrect = aTrue;
    }

}



