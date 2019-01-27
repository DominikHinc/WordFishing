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
    private ArrayList<String> answerArrayList;

    public LoadQuestionsAndAnswers(Skin skin ,Skin skin2, WordFishing game){
        initQuestions(skin,skin2,game);
    }

    private void initQuestions(Skin skin,Skin skin2, WordFishing game) {
        FileHandle file = Gdx.files.internal("data/test.txt");
        questionArrayList = new ArrayList<Question>();
        answerArrayList = new ArrayList<String>();
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
                String a = wynik[1];
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

    public ArrayList<String> getAnswerArrayList() {
        return answerArrayList;
    }
}
