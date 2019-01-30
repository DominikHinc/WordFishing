package pl.dominikhinc.wordfishing.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.frames.Answer;
import pl.dominikhinc.wordfishing.frames.Question;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;
import pl.dominikhinc.wordfishing.service.LoadQuestionsAndAnswers;


public class GameScreen extends AbstractScreen implements Input.TextInputListener {

    private Image bgImage;
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private Question question;
    private Skin skin;
    private Skin skin2;
    //private String text;
    private ArrayList<Question> questionArrayList;
    private ArrayList<String> answerArrayList;
    private ArrayList<Answer> answerButtonList;
    private int arraySize;
    private int correctAnswerNumber;
    private int currentQuestionIndex;
    private Question lastQuestion;
    private LoadQuestionsAndAnswers loadQuestionsAndAnswers;
    private boolean givenAnswer;
    private int questionNumber = 0;
    private Texture correctAnswer, wrongAnswer, defaultBg;
    private String choosenBook;

    public GameScreen(WordFishing game,String chooseBook) {
        super(game);
        init(chooseBook);
    }

    protected void init(String chooseBook) {
        initBgImage();
        loadSkin();
        this.choosenBook = chooseBook;
        initBackToMenuButton();
        initQuestionList();
        initAnswerList();
        initAnswerButtons();
        createQuestion();
        createLastQuestion();
    }




    private void createLastQuestion() {
        lastQuestion = new Question("Koniec","",skin2,game);
    }


    @Override
    protected void init() {

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
        skin2 = new Skin(Gdx.files.internal("questionSkin/glassy-ui.json"));
    }

    private void initBgImage() {
        //TODO Make better background
        defaultBg = new Texture("Temporary.Menu.Background.png");
        correctAnswer = new Texture("Correct_Answer.png");
        wrongAnswer = new Texture("Wrong_Answer.png");
        bgImage = new Image(defaultBg);
        stage.addActor(bgImage);
    }
    private void initAnswerButtons() {
        answerButtonList = new ArrayList<Answer>();
        for(int i = 0; i < 4 ; i++){
            final Answer a = new Answer("",skin,game);
            a.setPositions(game,i);
            stage.addActor(a);
            a.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    givenAnswer = a.getIsCorrect();
                    checkAnswer();
                }
            });
            answerButtonList.add(a);
        }
    }
    private void createAnswers() {
        correctAnswerNumber = MathUtils.random(0,3);
        ArrayList<String> tempAnswerArray = (ArrayList<String>) answerArrayList.clone();
        tempAnswerArray.remove(question.getAnswer());
        for(int i = 0; i < 4 ; i++){
            if(i == correctAnswerNumber){
                answerButtonList.get(i).setisCorrect(true);
                answerButtonList.get(i).setAnswer(question.getAnswer());
            }else{
                answerButtonList.get(i).setisCorrect(false);
                int j = MathUtils.random(0,tempAnswerArray.size()-1);
                answerButtonList.get(i).setAnswer(tempAnswerArray.get(j));
                tempAnswerArray.remove(j);
            }
        }

    }

    private void createQuestion() {
        if(question != null){
            question.remove();
        }
        currentQuestionIndex = MathUtils.random(0,questionArrayList.size()-1);
        question = questionArrayList.get(currentQuestionIndex);
        stage.addActor(question);
        question.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                reactOnClick();
            }
        });
        createAnswers();
    }
    private void reactOnClick() {
        Gdx.input.getTextInput(this, question.getQuestion(), "", "Odpowiedź");
    }

    private void initQuestionList() {
        loadQuestionsAndAnswers = new LoadQuestionsAndAnswers(skin, skin2, game,choosenBook);
        questionArrayList = loadQuestionsAndAnswers.getQuestionArrayList();
        arraySize = questionArrayList.size();
    }
    private void initAnswerList(){
        answerArrayList = loadQuestionsAndAnswers.getAnswerArrayList();
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
        if (givenAnswer == true) {
            questionNumber++;
            correctAnswer();
            questionArrayList.remove(currentQuestionIndex);

        } else {
            wrongAnswer();
        }
        if(questionArrayList.isEmpty() == true){
            stage.addActor(lastQuestion);
            for(Answer an :answerButtonList){
                an.setText("");
            }
            Button endGameButton = new Button(new Button.ButtonStyle());
            endGameButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new MenuScreen(game));
                }
            });
            endGameButton.setHeight(game.SCREEN_HEIGHT);
            endGameButton.setWidth(game.SCREEN_WIDTH);
            stage.addActor(endGameButton);

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

    }


    @Override
    public void canceled() {
    }
}
