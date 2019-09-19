package pl.dominikhinc.wordfishing.frames;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.SplitText;

public class Answer extends TextButton {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 240;

    private String answer;
    private boolean isCorrect = false;

    private WordFishing game;

    public Answer(String answer, Skin skin, WordFishing game) {
        super(answer, skin);
        this.answer = answer;
        this.game = game;
        this.setTransform(true);
        init();
    }

    private void init(){

        this.setWidth(WIDTH*10*WordFishing.SCALE);

        this.setHeight(HEIGHT*WordFishing.SCALE);

        this.getLabel().setWrap(true);

        this.setSkin(game.getSkin());

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
        /*if(answer.length()>=12){
            //splitText();
            SplitText splitText = new SplitText();
            this.setText(splitText.splitText(answer.length()/2,answer));

        }else {
            this.setText(answer);
        }*/
        /*if(answer.length() >= 25){
            this.setStyle(game.getSkinSmallFont().get("default",TextButton.TextButtonStyle.class));
        }else{*/
            //
        // this.setStyle(game.getSkin().get("default",TextButton.TextButtonStyle.class));
       // }

    }


    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean aTrue) {
        isCorrect = aTrue;
    }

}



