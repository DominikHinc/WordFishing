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
    private ArrayList<String> answerArrayList;
    private WordFishing game;

    public LoadQuestionsAndAnswers(Skin skin ,Skin skin2, WordFishing game, String choosenBook, String folder){
        this.game = game;
        initQuestions(skin,skin2,game,choosenBook,folder);
    }

    private void initQuestions(Skin skin,Skin skin2, WordFishing game,String choosenBook,String folder) {
        FileHandle file = fileHandle(choosenBook,folder);
        questionArrayList = new ArrayList<Question>();
        answerArrayList = new ArrayList<String>();

        if(game.getPreferences().getBoolean(WordFishing.personalListUse)){
            FileHandle files = Gdx.files.local("personal/"+folder+"/"+choosenBook+".txt");
            String text = file.readString();
            String[] textArray = text.split("\\r?\\n");
            for(String word:textArray){
                if ((word.contains("/"))){
                    String[] wynik = word.split("/");
                    System.out.println(wynik[0] + " "+wynik[1]);
                    if (game.isQuestionInEnglish() == false) {
                        Question q = new Question(wynik[0], wynik[1], skin2, game);
                        questionArrayList.add(q);
                        String a = wynik[1];
                        answerArrayList.add(a);
                    } else {
                        Question q = new Question(wynik[1], wynik[0], skin2, game);
                        questionArrayList.add(q);
                        String a = wynik[0];
                        answerArrayList.add(a);
                    }
                }



            }
        }else {
            String text = file.readString();
            BufferedReader reader = file.reader(text.length());
            try {
                String wiersz = null;
                while ((wiersz = reader.readLine()) != null) {
                    if (game.isQuestionInEnglish() == false) {
                        String[] wynik = wiersz.split("/");
                        Question q = new Question(wynik[0], wynik[1], skin2, game);
                        questionArrayList.add(q);
                        String a = wynik[1];
                        answerArrayList.add(a);
                    } else {
                        String[] wynik = wiersz.split("/");
                        Question q = new Question(wynik[1], wynik[0], skin2, game);
                        questionArrayList.add(q);
                        String a = wynik[0];
                        answerArrayList.add(a);
                    }

                }
            } catch (Exception ex) {
                System.out.println("Nie mozna odczytac pliku z kartami!");
                ex.printStackTrace();
            }
        }
    }
    public FileHandle fileHandle(String choosenBook, String folder){
        FileHandle file;
        if(game.getPreferences().getBoolean(WordFishing.personalListUse)){
            file = Gdx.files.local("personal/"+folder+"/"+choosenBook+".txt");
        }else{
            file = Gdx.files.internal("data/"+folder+"/"+choosenBook+".txt");
        }

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
