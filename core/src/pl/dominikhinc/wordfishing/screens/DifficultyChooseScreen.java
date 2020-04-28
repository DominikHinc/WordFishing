package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
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
    private String folder;
    private CheckBox personalListCheckBox;
    private TextButton configPersonalList;
    private Label isCreatedLabel;

    public DifficultyChooseScreen(WordFishing game, String s, String folder) {
        super(game);
        choosenBook = s;
        this.game = game;
        this.folder = folder;
        game.setComplex(false);
        game.setTextInput(game.getPreferences().getBoolean(game.textInputPreferences));
        initR();

    }

    private void initR(){
       // initTest();
        NotificationUpdate();
        initChooseButtons();
        initConfigPersonalListButton();
        initChooseButtonsListeners();
        initTimeSinceCompletedLabel();
        initTextInputCheckBox();
        initSlider();
        initSliderCountLabel();
        initPeronalListCheckBox();
        initIsCreatedLabel();
    }

    private void initIsCreatedLabel() {
        isCreatedLabel = new Label("Lista nie została stworzona",game.getSkin());
        FileHandle file = Gdx.files.local("personal/"+folder+"/"+choosenBook+".txt");
        if(file.exists()){
            isCreatedLabel.setColor(0.1f, 0.7f, 0.1f,1);
            isCreatedLabel.setText("Lista została storzona");
        }else{
            isCreatedLabel.setColor(0.9f, 0.1f, 0.1f,1);
            isCreatedLabel.setText("Lista nie została stworzona");
            personalListCheckBox.setChecked(false);
            game.isPersonalList = personalListCheckBox.isChecked();
            game.getPreferences().putBoolean(WordFishing.personalListUse,game.isPersonalList);
            game.getPreferences().flush();
            personalListCheckBox.setTouchable(Touchable.disabled);
        }
        isCreatedLabel.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT/24);
        stage.addActor(isCreatedLabel);
    }

    private void initConfigPersonalListButton() {
        configPersonalList = new TextButton("Utwórz własną liste",game.getSkin());
        configPersonalList.getLabel().setWrap(true);
        configPersonalList.setSize(306*WordFishing.SCALE,170*WordFishing.SCALE);
        configPersonalList.setPosition(WordFishing.SCREEN_WIDTH - WordFishing.SCREEN_WIDTH/8-configPersonalList.getWidth(),WordFishing.SCREEN_HEIGHT/12);
        stage.addActor(configPersonalList);
    }

    private void initPeronalListCheckBox() {
        personalListCheckBox = new CheckBox("  Użyj listy własnej",game.getSkin());
        personalListCheckBox.setPosition(game.SCREEN_WIDTH/14,game.SCREEN_HEIGHT/12);
        personalListCheckBox.getImage().setOrigin(personalListCheckBox.getImage().getWidth()/2,personalListCheckBox.getImage().getHeight()/2);
        personalListCheckBox.getImage().setScale(3*WordFishing.SCALE);
        personalListCheckBox.setChecked(game.getPreferences().getBoolean(WordFishing.personalListUse));
        personalListCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                game.isPersonalList = personalListCheckBox.isChecked();
                game.getPreferences().putBoolean(WordFishing.personalListUse,game.isPersonalList);
                game.getPreferences().flush();
            }
        });
        stage.addActor(personalListCheckBox);
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
        sliderCount.setPosition(game.SCREEN_WIDTH/14 + slider.getWidth() + 50*WordFishing.SCALE,game.SCREEN_HEIGHT/5 + slider.getHeight()/2);
        stage.addActor(sliderCount);
    }

    private void initSlider() {
        slider = new Slider(1,9,0.05f,false,game.getSkin());
        slider.setSize(450*WordFishing.SCALE,100*WordFishing.SCALE);
        slider.getStyle().knob.setMinHeight(50*WordFishing.SCALE);
        slider.getStyle().knob.setMinWidth(50*WordFishing.SCALE);
        slider.setPosition(game.SCREEN_WIDTH/14,game.SCREEN_HEIGHT/5);
        slider.setValue(game.getCorrectAnswersNeededInt());
        stage.addActor(slider);
    }

    private void initTextInputCheckBox() {
        textInputBox = new CheckBox("  Sprawdzanie\n  tekstowe",game.getSkin());
        textInputBox.setPosition(game.SCREEN_WIDTH/14,game.SCREEN_HEIGHT/8);
        textInputBox.getImage().setOrigin(textInputBox.getImage().getWidth()/2,textInputBox.getImage().getHeight()/2);
        textInputBox.getImage().setScale(3*WordFishing.SCALE);
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
            timeSinceCompleted = new Label("Nie Ukończono",game.getSkin(),"ukon");
            timeSinceCompleted.setPosition(game.SCREEN_WIDTH/2 - timeSinceCompleted.getWidth()/2,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/3.5f);
        }else{
            long time = TimeUtils.millis() - game.getPreferences().getLong(choosenBook+".TimeWhenCompleted");
            time = time/1000;

            timeSinceCompleted = new Label("Ukończono:\n "+calculateTime(time)+"  temu",game.getSkin(),"ukon");
            timeSinceCompleted.setPosition(game.SCREEN_WIDTH/2 - timeSinceCompleted.getWidth()/2,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/3);
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
                game.setScreen(new GameScreen(game,choosenBook,folder));
            }
        });
        englishPolish.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setQuestionInEnglish(true);
                game.setScreen(new GameScreen(game,choosenBook,folder));
            }
        });
        complex.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setQuestionInEnglish(true);
                game.setComplex(true);
                game.setTextInput(false);
                game.setScreen(new GameScreen(game,choosenBook,folder));
            }
        });
        configPersonalList.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new PersonalListScreen(game,choosenBook,folder));
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
