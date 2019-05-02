package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;
import pl.dominikhinc.wordfishing.service.IdCalculate;
import pl.dominikhinc.wordfishing.service.MenuButtonCreator;

public class DifficultyChooseScreen extends AbstractScreen {

    private Image bgImage;
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private MenuButtonCreator buttonCreator;
    private TextButton polishEnglish;
    private TextButton englishPolish;
    private TextButton complex;
    private String choosenBook;
    private Label timeSinceCompleted;
    private CheckBox textInputBox;
    private Slider slider;
    private Label sliderCount;

    public DifficultyChooseScreen(WordFishing game, String s) {
        super(game);
        choosenBook = s;
        this.game = game;
        game.setComplex(false);
        game.setTextInput(game.getPreferences().getBoolean(game.textInputPreferences));
        initR();

    }

    private void initR(){
       // initTest();
        NotificationUpdate();
        initChooseButtons();
        initChooseButtonsListeners();
        initTimeSinceCompletedLabel();
        initTextInputCheckBox();
        initSlider();
        initSliderCountLabel();

    }

    private void initTest() {
        int lib = (game.getPreferences().getInteger(choosenBook+"days")+2)*86400;
        long xd = (TimeUtils.millis() - game.getPreferences().getLong(choosenBook+".TimeWhenCompleted"))/1000;
        Label label = new Label(String.valueOf(xd) +" > "+ String.valueOf(lib),game.getSkin());
        stage.addActor(label);
    }

    private void NotificationUpdate() {
        if(game.getPreferences().getInteger(choosenBook+"days") != 0 && game.getPreferences().getLong(choosenBook+".TimeWhenCompleted") != 0){
            if((TimeUtils.millis() - game.getPreferences().getLong(choosenBook+".TimeWhenCompleted"))/1000 > (game.getPreferences().getInteger(choosenBook+"days")+2)*86400){
                game.getPreferences().putInteger(choosenBook+"days",0);
                game.getPreferences().putLong(choosenBook+".TimeWhenCompleted",0);
                game.getPreferences().flush();
            }
        }
    }

    private void initSliderCountLabel() {
        sliderCount = new Label("",game.getSkin());
        sliderCount.setPosition(game.SCREEN_WIDTH/14 + slider.getWidth() + 50,game.SCREEN_HEIGHT/5 + slider.getHeight()/2);
        stage.addActor(sliderCount);
    }

    private void initSlider() {
        slider = new Slider(1,9,0.05f,false,game.getSkin());
        slider.setSize(450,100);
        slider.getStyle().knob.setMinHeight(75);
        slider.getStyle().knob.setMinWidth(75);
        slider.setPosition(game.SCREEN_WIDTH/14,game.SCREEN_HEIGHT/5);
        slider.setValue(game.getCorrectAnswersNeededInt());
        stage.addActor(slider);
    }

    private void initTextInputCheckBox() {
        textInputBox = new CheckBox("   Sprawdzanie tekstowe",game.getSkin());
        textInputBox.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT/8);
        textInputBox.getImage().setScale(3);
        textInputBox.setChecked(game.isTextInput());
        textInputBox.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                game.setTextInput(!game.isTextInput());
                game.getPreferences().putBoolean(game.textInputPreferences,game.isTextInput());
                game.getPreferences().flush();

            }
        });
        stage.addActor(textInputBox);
    }

    private void initTimeSinceCompletedLabel() {

        if(game.getPreferences().getLong(choosenBook+".TimeWhenCompleted") == 0){
            timeSinceCompleted = new Label("Ten zestaw słówek nigdy \nnie został ukończony",game.getSkin());
            timeSinceCompleted.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/3.5f);
        }else{
            long time = TimeUtils.millis() - game.getPreferences().getLong(choosenBook+".TimeWhenCompleted");
            time = time/1000;

            timeSinceCompleted = new Label("Czas od ostatniego ukończenia \ntego zestawu słówek:\n"+calculateTime(time),game.getSkin());
            timeSinceCompleted.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/3);
        }
        stage.addActor(timeSinceCompleted);
    }

    private String calculateTime(long time){
        if(time < 60){
            return String.valueOf(time)+" sec";
        }
        if(time >= 60 && time < 3600){
            return String.valueOf(time/60) + " min";
        }
        if(time >= 3600 && time < 86400){
            return  String.valueOf(time/3600)+" godz";
        }
        if(time >= 86400){
            return String.valueOf(time/86400)+" dni";
        }
        return String.valueOf(time/86400)+" dni";
    }

    @Override
    protected void init() {
        initBgImage();
        initBackToMenuButton();
    }

    private void initChooseButtonsListeners() {
        polishEnglish.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setQuestionInEnglish(false);
                game.setScreen(new GameScreen(game,choosenBook));
            }
        });
        englishPolish.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setQuestionInEnglish(true);
                game.setScreen(new GameScreen(game,choosenBook));
            }
        });
        complex.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setQuestionInEnglish(true);
                game.setComplex(true);
                game.setTextInput(false);
                game.setScreen(new GameScreen(game,choosenBook));
            }
        });
    }

    private void initChooseButtons() {
        buttonCreator = new MenuButtonCreator();
        polishEnglish = buttonCreator.createButton("Polski-Angielski",game);
        englishPolish = buttonCreator.createButton("Angielski-Polski",game);
        complex = buttonCreator.createButton("Kompleksowe",game);
        stage.addActor(polishEnglish);
        stage.addActor(englishPolish);
        stage.addActor(complex);
    }


    private void initBackToMenuButton() {
        goBackButtonCreator = new GoBackButtonCreator();
        goBackButton = goBackButtonCreator.createButton(game);
        goBackButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(goBackButton);

    }

    private void initBgImage() {
        bgImage = new Image(game.getDefaultBg());
        stage.addActor(bgImage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        int tempp = (int) slider.getValue();
        String tempo = String.valueOf(tempp);
        sliderCount.setText("Ilość powtórzeń: "+tempo);
        if(slider.isDragging()){
            game.getPreferences().putInteger(game.correctAnswersNeeded,tempp);
            game.getPreferences().flush();
            game.setCorrectAnswersNeededInt(tempp);
        }
    }
}
