package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.io.BufferedReader;
import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.frames.Answer;
import pl.dominikhinc.wordfishing.frames.Question;

public class LoadQuestionsAndAnswers {

    private ArrayList<Question> questionArrayList;
    private ArrayList<Answer> answerArrayList;

    public LoadQuestionsAndAnswers(Skin skin ,Skin skin2, WordFishing game){
        initQuestions(skin,skin2,game);
    }

    private void initQuestions(Skin skin,Skin skin2, WordFishing game) {
        FileHandle file = Gdx.files.internal("data/test.txt");
        questionArrayList = new ArrayList<Question>();
        answerArrayList = new ArrayList<Answer>();
        String text = file.readString();
        BufferedReader reader = file.reader(text.length());
        try
        {
            String wiersz = null;
            while((wiersz = reader.readLine())!=null)
            {
                String[] wynik = wiersz.split("/");
                Question q = new Question(wynik[0],wynik[1],skin2,game);
                questionArrayList.add(q);
                Answer a = new Answer(wynik[1],skin,game);
                answerArrayList.add(a);
            }
        }
        catch(Exception ex)
        {
            System.out.println("Nie mozna odczytac pliku z kartami!");
            ex.printStackTrace();
        }

    }

    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public ArrayList<Answer> getAnswerArrayList() {
        return answerArrayList;
    }
}
