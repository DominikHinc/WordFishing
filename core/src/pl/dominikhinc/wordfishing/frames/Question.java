package pl.dominikhinc.wordfishing.frames;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.SplitText;

public class Question extends TextButton {
    public static final int WIDTH = 85;
    public static final int HEIGHT = 400;

    private String question;
    private String answer;



    private int correctAnswersToGo;


    public Question(String question,String answer, Skin skin, WordFishing game) {
        super(question, skin);
        this.question = question;//.toLowerCase();
        this.answer = answer;//.toLowerCase();
        init(game);

    }

    private void init(WordFishing game) {

       /* if(this.getText().length() > 10){
            this.setWidth(WIDTH*this.getText().length());
        }else{

        }*/
        this.setWidth(WIDTH*10);
        this.setHeight(HEIGHT );
        this.setOrigin(WIDTH / 2 , HEIGHT / 2);
        this.setPosition(game.SCREEN_WIDTH / 2 - this.getWidth() / 2 , game.SCREEN_HEIGHT / 1.7f);
        if(question.length() > 25){
            SplitText splitText = new SplitText();
            this.setText(splitText.splitText(22,question));
        }
        correctAnswersToGo = game.getCorrectAnswersNeededInt();
    }

    /*private void splitText() {
        String[] shownText_temp =  new String[2];
        String shownText = null;
        char[] answerArray = question.toCharArray();
        int border = 22;
        String tempor = tempor = question.substring(border,question.length());
        if(question.contains(" ")){
            int toBack = border;
            int toFront = border;
            while(answerArray[toBack] != ' '  && (toBack > 0)){
                toBack--;
            }
            if(tempor.contains(" ")){
                while(answerArray[toFront] != ' ' &&  toFront < question.length()-1){
                    toFront++;
                }
            }else{
                toFront = 99;
            }

            toBack = border - toBack;
            toFront = toFront - border;
            if(toBack <= toFront){
                shownText_temp[0] = question.substring(0,border-toBack);
                shownText_temp[1] = question.substring(border-toBack ,question.length());
                shownText = shownText_temp[0] +"\n"+ shownText_temp[1];
            }
            if(toFront < toBack){
                shownText_temp[0] = question.substring(0,toFront+border);
                shownText_temp[1] = question.substring(toFront+border ,question.length());
                shownText = shownText_temp[0] +"\n"+ shownText_temp[1];
            }

        }else{
            shownText_temp[0] = question.substring(0,border+1);
            shownText_temp[1] = question.substring(border+1 ,question.length());
            shownText = shownText_temp[0]+ "-" +"\n"+ shownText_temp[1];
        }

        this.setText(shownText);
    }*/
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getCorrectAnswersToGo() {
        return correctAnswersToGo;
    }

    public void setCorrectAnswersToGo(int correctAnswersToGo) {
        this.correctAnswersToGo = correctAnswersToGo;
    }
}
