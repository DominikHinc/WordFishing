package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;

public class OptionsScreen extends AbstractScreen {

    //TODO Make abstract screen that from wihich This game and help screen will extend

    private Image bgImage;
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    //private Label textInputLabel;
    private CheckBox textInputBox;
    private CheckBox questionInEnglishBox;
    private Slider slider;
    private Label sliderCount;
    private Label infoAboutSlider;

    public OptionsScreen(WordFishing game) {
        super(game);
    }

    @Override
    protected void init() {
        initBgImage();
        initBackToMenuButton();
        //initTextInputLabel();
        initTextInputCheckBox();
        //initQuestionInEnglishCheckBox();
        initSlider();
        initSliderCountLabel();
        initInfoAboutSliderLabel();
    }

    private void initSliderCountLabel() {
        sliderCount = new Label("",game.getSkin());
        sliderCount.setPosition(game.SCREEN_WIDTH/8 + slider.getWidth() + 50,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/4 + slider.getHeight()/2);
        stage.addActor(sliderCount);
    }

    private void initInfoAboutSliderLabel() {
        infoAboutSlider = new Label("odpowiedzieć poprawnie aby \nsłówko zostało uznane za nauczone",game.getSkin());
        infoAboutSlider.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/3.1f);
        stage.addActor(infoAboutSlider);
    }

    private void initSlider() {
        slider = new Slider(1,9,0.05f,false,game.getSkin());
        slider.setSize(450,100);
        slider.getStyle().knob.setMinHeight(75);
        slider.getStyle().knob.setMinWidth(75);
        slider.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/4);
        slider.setValue(game.getCorrectAnswersNeededInt());
        stage.addActor(slider);

    }

    /*private void initQuestionInEnglishCheckBox() {
        questionInEnglishBox = new CheckBox("   Pytania po angielsku",game.getSkin());
        questionInEnglishBox.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/4);
        questionInEnglishBox.getImage().setScale(3);
        questionInEnglishBox.setChecked(game.isQuestionInEnglish());
        questionInEnglishBox.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                game.setQuestionInEnglish(!game.isQuestionInEnglish());
                game.getPreferences().putBoolean(game.questionInEnglishPreferences,game.isQuestionInEnglish());
                game.getPreferences().flush();
                if(game.isQuestionInEnglish()){
                    System.out.println("True");
                }else{
                    System.out.println("False");
                }
            }
        });
        stage.addActor(questionInEnglishBox);
    }*/

    private void initTextInputCheckBox() {
        textInputBox = new CheckBox("   Sprawdzanie tekstowe",game.getSkin());
        textInputBox.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/6);
        textInputBox.getImage().setScale(3);
        textInputBox.setChecked(game.isTextInput());
        textInputBox.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                game.setTextInput(!game.isTextInput());
                game.getPreferences().putBoolean(game.textInputPreferences,game.isTextInput());
                game.getPreferences().flush();
                /*if(game.isTextInput()){
                    System.out.println("True");
                }else{
                    System.out.println("False");
                }*/
            }
        });
        stage.addActor(textInputBox);
    }

    /*private void initTextInputLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.getSkin().getFont("font");
        textInputLabel = new Label("Sprawdzanie tekstowe" , labelStyle);
        textInputLabel.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/6);
        stage.addActor(textInputLabel);
    }*/

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
        //TODO Make better background
        bgImage = new Image(game.getDefaultBg());
        stage.addActor(bgImage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.act();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
        int tempp = (int) slider.getValue();
        String tempo = String.valueOf(tempp);
        sliderCount.setText(tempo + " razy należy");
        if(slider.isDragging()){
            game.getPreferences().putInteger(game.correctAnswersNeeded,tempp);
            game.getPreferences().flush();
            game.setCorrectAnswersNeededInt(tempp);
        }
    }

}
