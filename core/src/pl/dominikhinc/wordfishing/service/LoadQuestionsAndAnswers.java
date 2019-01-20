package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.frames.Question;

public class LoadQuestionsAndAnswers {

    private ArrayList<Question> questionArrayList;

    public LoadQuestionsAndAnswers(Skin skin , WordFishing game){
        questionArrayList = new ArrayList<Question>();
        for(int i = 0; i < 10 ; i++){
            Question q = new Question( "" + i,"" + i,skin , game);
            questionArrayList.add(q);
        }
    }

    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }
}
