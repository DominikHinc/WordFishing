package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;


import java.io.BufferedReader;
import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.frames.PersonalListLabel;
import pl.dominikhinc.wordfishing.frames.Question;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;

public class PersonalListScreen extends AbstractScreen {

    //private GoBackButtonCreator goBackButtonCreator;
    //private Button goBackButton;
    private Array<PersonalListLabel> wordList;
    private String choosenBook;
    private String folder;
    private Table list;
    private ScrollPane scrollPane;
    private TextButton begin,end,reset,save;

    private boolean settingBegin = false;
    private boolean settingEnd = false;
    private boolean toSave = false;

    private int beginIndex = 0;
    private int endIndex;

    public PersonalListScreen(WordFishing game,String choosenBook, String folder) {
        super(game);
        initt(choosenBook,folder);
    }


    protected void initt(String choosenBook, String folder) {
        this.folder = folder;
        this.choosenBook = choosenBook;
        initBackToMenuButton();
        initWords();
        initList();
        initButtons();
    }

    public void reset(){
        for(PersonalListLabel label: wordList){
            label.uncheck();
            label.update();
        }
    }

    public void clicked(int index){
        if(settingBegin){
            beginIndex = index;
            settingBegin = false;
            end.setTouchable(Touchable.enabled);
            reset.setTouchable(Touchable.enabled);
            save.setTouchable(Touchable.enabled);
            begin.setText("Zaznacz Początek");
            if(endIndex > beginIndex){
                setIndexed();
            }
        }else if(settingEnd){
            endIndex = index;
            settingEnd = false;
            begin.setTouchable(Touchable.enabled);
            reset.setTouchable(Touchable.enabled);
            save.setTouchable(Touchable.enabled);
            begin.setText("Zaznacz Koniec");
            if(endIndex > beginIndex){
                setIndexed();
            }
        }

    }
    public void setIndexed(){
        for(PersonalListLabel label: wordList){
            if(label.getIndex() >= beginIndex && label.getIndex() <=endIndex){
                label.check();
                label.update();
            }
        }
    }

    private void initButtons() {
        begin = new TextButton("Zaznacz Początek",game.getSkin());
        end = new TextButton("Zaznacz Koniec",game.getSkin());
        reset = new TextButton("Reset",game.getSkin());

        begin.getLabel().setWrap(true);
        end.getLabel().setWrap(true);
        save.getLabel().setWrap(true);
        begin.setSize(WordFishing.SCREEN_WIDTH/3 - WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT/12);
        end.setSize(WordFishing.SCREEN_WIDTH/3 - WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT/12);
        reset.setSize(WordFishing.SCREEN_WIDTH/3 - WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT/12);

        begin.setPosition(WordFishing.SCREEN_WIDTH/8/2,WordFishing.SCREEN_HEIGHT/24);
        end.setPosition(WordFishing.SCREEN_WIDTH/8/2+WordFishing.SCREEN_WIDTH/3,WordFishing.SCREEN_HEIGHT/24);
        reset.setPosition(WordFishing.SCREEN_WIDTH/8/2+2*WordFishing.SCREEN_WIDTH/3,WordFishing.SCREEN_HEIGHT/24);

        begin.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                reset();
                settingBegin = true;
                end.setTouchable(Touchable.disabled);
                reset.setTouchable(Touchable.disabled);
                save.setTouchable(Touchable.disabled);
                begin.setText("Wybież z listy");
            }
        });
        end.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                reset();
                settingEnd = true;
                begin.setTouchable(Touchable.disabled);
                reset.setTouchable(Touchable.disabled);
                save.setTouchable(Touchable.disabled);
                end.setText("Wybież z listy");

            }
        });
        reset.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
               reset();

            }
        });
        save.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                toSave = true;

            }
        });
        stage.addActor(begin);
        stage.addActor(end);
        stage.addActor(reset);
    }
    public void saveToFile(){
        FileHandle fileHandle = Gdx.files.local("personal/"+folder+"/"+choosenBook+".txt");
        fileHandle.writeString(" / ",false);
        fileHandle.writeString("\n\r",true);
        int i = 0;
        for(PersonalListLabel label: wordList){
            if(label.checked == true){
                String[] s = label.getText().toString().split(" - ");
                fileHandle.writeString(s[0]+"/"+s[1],true);
                fileHandle.writeString("\n\r",true);
                i++;
            }

        }
        if(i < 5){
            save.setText("Za mało słówek, dodaj więcej po czym naciśnij ponownie");
           fileHandle.delete();

        }else{
            game.setScreen(new DifficultyChooseScreen(game,choosenBook,folder));
        }

    }
    private void initList() {
        //list = new List<Label>(game.getSkin());
        list = new Table(game.getSkin());
        //list.setSize(WordFishing.SCREEN_WIDTH - WordFishing.SCREEN_WIDTH/8*2,WordFishing.SCREEN_HEIGHT - WordFishing.SCREEN_HEIGHT/7*2);
        for (PersonalListLabel label:wordList){
            list.left();
            list.add(label).left().row();
        }
        scrollPane = new ScrollPane(list,game.getSkin());
        scrollPane.setBounds(WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT/7,WordFishing.SCREEN_WIDTH - WordFishing.SCREEN_WIDTH/8*2,WordFishing.SCREEN_HEIGHT - WordFishing.SCREEN_HEIGHT/7*2);
        scrollPane.setTransform(true);
        stage.addActor(scrollPane);
        scrollPane.setOverscroll(false,true);
        scrollPane.setScrollbarsVisible(true);
        scrollPane.setSmoothScrolling(true);
        scrollPane.setFadeScrollBars(true);
    }

    private void initWords() {
        wordList = new Array<>();
        /*if(game.getPreferences().getBoolean(WordFishing.personalListUse)){
            FileHandle file = Gdx.files.local("personal/"+folder+"/"+choosenBook+".txt");
            String text = file.readString();
            String[] textArray = text.split("\\r?\\n");
            int i = -1;
            for(String word:textArray){
                //String[] wynik = word.split("/");
                //String wordd =  wynik[0] + " - " + wynik[1];
                PersonalListLabel label = new PersonalListLabel(word,game.getSkin(),i,this);
                wordList.add(label);
                i++;
            }
        }else{*/
            FileHandle file = Gdx.files.internal("data/"+folder+"/"+choosenBook+".txt");
            String text = file.readString();
            BufferedReader reader = file.reader(text.length());
            int i = -1;
            try
            {
                String wiersz = null;
                while((wiersz = reader.readLine())!=null)
                {
                    String[] wynik = wiersz.split("/");
                    String word =  wynik[0] + " - " + wynik[1];
                    PersonalListLabel label = new PersonalListLabel(word,game.getSkin(),i,this);
                    wordList.add(label);
                    i++;
                }
            }
            catch(Exception ex)
            {
                System.out.println("Nie mozna odczytac pliku z kartami!");
                ex.printStackTrace();
            }
            wordList.removeIndex(0);
       // }

    }

    private void initBackToMenuButton() {
        save = new TextButton("Zapisz",game.getSkin());
        save.setSize(WordFishing.SCREEN_WIDTH - WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT/12);
        save.setPosition(WordFishing.SCREEN_WIDTH/16,WordFishing.SCREEN_HEIGHT/7+WordFishing.SCREEN_HEIGHT - WordFishing.SCREEN_HEIGHT/7*2+WordFishing.SCREEN_HEIGHT/24);
        stage.addActor(save);
        /*
        goBackButtonCreator = new GoBackButtonCreator();
        goBackButton = goBackButtonCreator.createButton(game);
        goBackButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(goBackButton);*/

    }
    @Override
    public void render(float delta) {
        super.render(delta);
        //update();
        if(toSave){
            saveToFile();
            toSave = false;

        }
        scrollPane.act(delta);
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }
    @Override
    protected void init() {

    }
}
