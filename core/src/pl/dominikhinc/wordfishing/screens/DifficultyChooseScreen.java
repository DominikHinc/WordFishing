package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;
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

    public DifficultyChooseScreen(WordFishing game, String s) {
        super(game);
        choosenBook = s;
        this.game = game;
        game.setComplex(false);
        initR();
    }

    private void initR(){
        initChooseButtons();
        initChooseButtonsListeners();
        initTimeSinceCompletedLabel();
    }

    private void initTimeSinceCompletedLabel() {
        System.out.println(game.getPreferences().getLong(choosenBook+".TimeWhenCompleted"));
        if(game.getPreferences().getLong(choosenBook+".TimeWhenCompleted") == 0){
            timeSinceCompleted = new Label("Ten zestaw słówek nigdy \nnie został ukończony",game.getSkin());
            timeSinceCompleted.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/3.5f);
        }else{
            long time = TimeUtils.millis() - game.getPreferences().getLong(choosenBook+".TimeWhenCompleted");
            time = time/1000;
            timeSinceCompleted = new Label("Czas od ostatniego ukończenia \ndanego zbioru słówek:\n"+time+" sec",game.getSkin());
            timeSinceCompleted.setPosition(game.SCREEN_WIDTH/8,game.SCREEN_HEIGHT - game.SCREEN_HEIGHT/4);
        }
        stage.addActor(timeSinceCompleted);
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
    }
}
