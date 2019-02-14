package pl.dominikhinc.wordfishing.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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
    private String text;
    private ArrayList<Question> questionArrayList;
    private ArrayList<String> answerArrayList;
    private ArrayList<Answer> answerButtonList;
    private int arraySize;
    private int correctAnswerNumber;
    private int currentQuestionIndex;
    private Question lastQuestion;
    private LoadQuestionsAndAnswers loadQuestionsAndAnswers;
    private boolean givenAnswer;
    //private int questionNumber = 0;
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
        skin = game.getSkin();
        skin2 = game.getSkin2();
    }

    private void initBgImage() {
        //TODO Make better background
        defaultBg = game.getDefaultBg();
        correctAnswer = game.getCorrectAnswer();
        wrongAnswer = game.getWrongAnswer();
        bgImage = new Image(defaultBg);
        stage.addActor(bgImage);
    }
    private void initAnswerButtons() {
        if(game.isTextInput() == false){
            answerButtonList = new ArrayList<Answer>();
            for(int i = 0; i < 4 ; i++){
                final Answer a = new Answer("",skin,game);
                a.setPositions(game,i);
                stage.addActor(a);
                a.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        givenAnswer = a.getIsCorrect();
                        checkAnswerButtons();
                    }
                });
                answerButtonList.add(a);
            }
        }
    }
    private void createAnswers() {
        if(game.isTextInput() == false){
            correctAnswerNumber = MathUtils.random(0,3);
            ArrayList<String> tempAnswerArray = (ArrayList<String>) answerArrayList.clone();
            tempAnswerArray.remove(question.getAnswer());
            for(int i = 0; i < 4 ; i++){
                if(i == correctAnswerNumber){
                    answerButtonList.get(i).setIsCorrect(true);
                    answerButtonList.get(i).setAnswer(question.getAnswer());
                }else{
                    answerButtonList.get(i).setIsCorrect(false);
                    int j = MathUtils.random(0,tempAnswerArray.size()-1);
                    answerButtonList.get(i).setAnswer(tempAnswerArray.get(j));
                    tempAnswerArray.remove(j);
                }
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
        if(game.isTextInput() == true){
            question.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    reactOnClick();
                }
            });
        }
        createAnswers();
    }

    private void createLastQuestion() {
        lastQuestion = new Question("Koniec","",skin2,game);
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

    private void checkAnswerButtons() {
        if (givenAnswer == true) {
            //questionNumber++;
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
    private void checkAnswerText(){
        if (text.equals(question.getAnswer().toLowerCase())){
            //questionNumber++;
            correctAnswer();
            questionArrayList.remove(currentQuestionIndex);
        }else{
            wrongAnswer();
            Label.LabelStyle labelStyle = new Label.LabelStyle(game.getFontRed(),new Color(225,73,70,1));
            final Label label = new Label(question.getAnswer(),labelStyle);
            label.setFontScale(1.5f);
            label.setPosition(game.SCREEN_WIDTH/2-label.getWidth()/2*1.5f,game.SCREEN_HEIGHT/4);
            stage.addActor(label);
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    label.remove();
                }
            }, 3);
        }
        if(questionArrayList.isEmpty() == true){
            stage.addActor(lastQuestion);
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
        int delay;
        if(game.isTextInput() == true){
            delay = 3;
        }else{
            delay = 1;
        }
        bgImage.setDrawable(new SpriteDrawable(new Sprite(wrongAnswer)));
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                bgImage.setDrawable(new SpriteDrawable(new Sprite(defaultBg)));
            }
        }, delay);
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
        checkAnswerText();
    }
    @Override
    public void canceled() {
    }
}
