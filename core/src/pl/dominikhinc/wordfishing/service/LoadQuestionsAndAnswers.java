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

    public LoadQuestionsAndAnswers(Skin skin ,Skin skin2, WordFishing game, String choosenBook){
        initQuestions(skin,skin2,game,choosenBook);
    }

    private void initQuestions(Skin skin,Skin skin2, WordFishing game,String choosenBook) {
        FileHandle file = fileHandle(choosenBook);
        questionArrayList = new ArrayList<Question>();
        answerArrayList = new ArrayList<String>();
        String text = file.readString();
        BufferedReader reader = file.reader(text.length());
        try
        {
            String wiersz = null;
            while((wiersz = reader.readLine())!=null)
            {
                if (game.isQuestionInEnglish() == false) {
                    String[] wynik = wiersz.split("/");
                    Question q = new Question(wynik[0],wynik[1],skin2,game);
                    questionArrayList.add(q);
                    String a = wynik[1];
                    answerArrayList.add(a);
                }else{
                    String[] wynik = wiersz.split("/");
                    Question q = new Question(wynik[1],wynik[0],skin2,game);
                    questionArrayList.add(q);
                    String a = wynik[0];
                    answerArrayList.add(a);
                }

            }
        }
        catch(Exception ex)
        {
            System.out.println("Nie mozna odczytac pliku z kartami!");
            ex.printStackTrace();
        }

    }
    public FileHandle fileHandle(String choosenBook){
        FileHandle file = Gdx.files.internal("data/"+choosenBook+".txt");
        /*
        switch (choosenBook){
            case"Repetytorium do szkol ponad gimnazjalnych Unit 3":file = Gdx.files.internal("data/Repetytorium do szkol ponad gimnazjalnych Unit 3.txt");break;
            case"test":file = Gdx.files.internal("data/test.txt");break;
        }
        */


        return file;
    }

    public ArrayList<Question> getQuestionArrayList() {
        return questionArrayList;
    }

    public ArrayList<String> getAnswerArrayList() {
        return answerArrayList;
    }
}
