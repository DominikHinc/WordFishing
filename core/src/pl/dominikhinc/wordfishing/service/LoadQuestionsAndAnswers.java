package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.io.BufferedReader;
import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.frames.Question;

public class LoadQuestionsAndAnswers {

    private ArrayList<Question> questionArrayList;

    public LoadQuestionsAndAnswers(Skin skin , WordFishing game){
        initQuestions(skin,game);
    }

    private void initQuestions(Skin skin, WordFishing game) {
        FileHandle file = Gdx.files.internal("data/test.txt");
        questionArrayList = new ArrayList<Question>();
        String text = file.readString();
        BufferedReader reader = file.reader(text.length());
        try
        {
            String wiersz = null;
            while((wiersz = reader.readLine())!=null)
            {
                String[] wynik = wiersz.split("/");
                Question q = new Question(wynik[0],wynik[1],skin,game);
                questionArrayList.add(q);
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
}
