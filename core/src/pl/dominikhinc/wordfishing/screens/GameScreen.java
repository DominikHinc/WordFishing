package pl.dominikhinc.wordfishing.screens;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.frames.Question;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;
import pl.dominikhinc.wordfishing.service.LoadQuestionsAndAnswers;


public class GameScreen extends AbstractScreen implements Input.TextInputListener {

    private Image bgImage ;
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    //private QuestionStyleCreator questionStyleCreator;
    private Question question;
    private Skin skin;
    private TextField textField;
    private String text;
    private Label label;
    private ArrayList<Question> questionArrayList;
    private LoadQuestionsAndAnswers loadQuestionsAndAnswers;
    private int questionNumber = 0;
    private Texture correctAnswer,wrongAnswer, defaultBg;

    public GameScreen(WordFishing game) {
        super(game);
    }

    @Override
    protected void init() {
        initBgImage();
        loadSkin();
        initBackToMenuButton();
        initQuestionList();
        createQuestion();
        //initFirstQuestion();
        //initTextField();
    }

    private void loadSkin() {
        skin = new Skin(Gdx.files.internal("glassy-ui.json"));
    }

    private void createQuestion() {
        question = questionArrayList.get(questionNumber);
        stage.addActor(question);
        question.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                reactOnClick();
            }
        });
    }

    private void initQuestionList() {
        loadQuestionsAndAnswers = new LoadQuestionsAndAnswers(skin, game);
        questionArrayList = loadQuestionsAndAnswers.getQuestionArrayList();
    }

    /*private void initTextField() {
        textField = new TextField("xd",skin);
        textField.setWidth(1080);
        textField.setHeight(100);
        textField.setPosition(0,game.SCREEN_HEIGHT-textField.getHeight() - goBackButton.getX());
        textField.setAlignment(Align.center);
        textField.setTextFieldListener(new TextField.TextFieldListener() {
            public void keyTyped (TextField textField, char key) {
                if (key == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });
        stage.addActor(textField);
    }*/

    /*private void initFirstQuestion() {
        //TEMPORARY

        //questionStyleCreator = new QuestionStyleCreator();
        question = new Question("Pytanie", skin , game);
        stage.addActor(question);
        Action moveAction = Actions.sequence(
                Actions.moveBy(300, 120, 5),
                Actions.moveBy(-300, -120, 3)
        );

        question.addAction(moveAction);
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

    private void checkAnswer() {
        if(text.equals(question.getAnswer())){
            questionNumber++;
            correctAnswer();
            createQuestion();
        }
        else{
            wrongAnswer();
        }
    }

    private void wrongAnswer() {
        bgImage.setDrawable(new SpriteDrawable(new Sprite(wrongAnswer)));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                bgImage.setDrawable(new SpriteDrawable(new Sprite(defaultBg)));
            }
        }, 1);
    }


    private void correctAnswer() {
        //image.setDrawable(new SpriteDrawable(new Sprite(newTexture)));
        bgImage.setDrawable(new SpriteDrawable(new Sprite(correctAnswer)));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                bgImage.setDrawable(new SpriteDrawable(new Sprite(defaultBg)));
            }
        }, 1);
    }

    private void reactOnClick(){
        Gdx.input.getTextInput(this,question.getQuestion(),"","Odpowiedź");
    }
    private void initBgImage() {
        //TODO Make better background
        defaultBg = new Texture("Temporary.Menu.Background.png");
        correctAnswer = new Texture("Correct_Answer.png");
        wrongAnswer = new Texture("Wrong_Answer.png");
        bgImage = new Image(defaultBg);
        stage.addActor(bgImage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();

        spriteBatch.begin();
        stage.act(delta);
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
    }
    public void input(String text){
        this.text = text;
        checkAnswer();
    }


    @Override
    public void canceled() {
    }
}
