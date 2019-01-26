package pl.dominikhinc.wordfishing.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.frames.Question;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;
import pl.dominikhinc.wordfishing.service.LoadQuestionsAndAnswers;


public class GameScreen extends AbstractScreen implements Input.TextInputListener {

    private Image bgImage;
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private Question question;
    private Skin skin;
    private String text;
    private ArrayList<Question> questionArrayList;
    private int arraySize;
    private int currentQuestionIndex;
    private Question lastQuestion;
    private LoadQuestionsAndAnswers loadQuestionsAndAnswers;
    private int questionNumber = 0;
    private Texture correctAnswer, wrongAnswer, defaultBg;

    public GameScreen(WordFishing game) {
        super(game);
    }

    @Override
    protected void init() {
        initBgImage();
        loadSkin();
        initBackToMenuButton();
        initQuestionList();
        createFirstQuestion();
        createLastQuestion();
    }

    private void createLastQuestion() {
        lastQuestion = new Question("Koniec","",skin,game);
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

    private void loadSkin() {
        skin = new Skin(Gdx.files.internal("glassy-ui.json"));
    }

    private void initBgImage() {
        //TODO Make better background
        defaultBg = new Texture("Temporary.Menu.Background.png");
        correctAnswer = new Texture("Correct_Answer.png");
        wrongAnswer = new Texture("Wrong_Answer.png");
        bgImage = new Image(defaultBg);
        stage.addActor(bgImage);
    }
    private void createFirstQuestion() {
        question = questionArrayList.get(0);
        stage.addActor(question);
        question.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                reactOnClick();
            }
        });
    }
    private void createQuestion() {
        question.remove();
        currentQuestionIndex = MathUtils.random(0,questionArrayList.size()-1);
        question = questionArrayList.get(currentQuestionIndex);
        stage.addActor(question);
        question.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                reactOnClick();
            }
        });
    }
    private void reactOnClick() {
        Gdx.input.getTextInput(this, question.getQuestion(), "", "Odpowiedź");
    }

    private void initQuestionList() {
        loadQuestionsAndAnswers = new LoadQuestionsAndAnswers(skin, game);
        questionArrayList = loadQuestionsAndAnswers.getQuestionArrayList();
        arraySize = questionArrayList.size();
    }

    private void initBackToMenuButton() {
        goBackButtonCreator = new GoBackButtonCreator();
        goBackButton = goBackButtonCreator.createButton(game);
        goBackButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));

            }
        });
        stage.addActor(goBackButton);

    }

    private void checkAnswer() {
        if (text.equals(question.getAnswer())) {
            questionNumber++;
            correctAnswer();
            questionArrayList.remove(currentQuestionIndex);

        } else {
            wrongAnswer();
        }
        if(questionArrayList.isEmpty() == true){
            stage.addActor(lastQuestion);
        }else{
            createQuestion();
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
        bgImage.setDrawable(new SpriteDrawable(new Sprite(correctAnswer)));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                bgImage.setDrawable(new SpriteDrawable(new Sprite(defaultBg)));
            }
        }, 1);
    }

    public void input(String text) {
        this.text = text.toLowerCase();
        checkAnswer();
    }


    @Override
    public void canceled() {
    }
}
