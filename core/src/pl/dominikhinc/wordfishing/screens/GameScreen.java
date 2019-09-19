package pl.dominikhinc.wordfishing.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.frames.Answer;
import pl.dominikhinc.wordfishing.frames.Question;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;
import pl.dominikhinc.wordfishing.service.IdCalculate;
import pl.dominikhinc.wordfishing.service.LoadQuestionsAndAnswers;
import pl.dominikhinc.wordfishing.service.NotificationHandler;
import pl.dominikhinc.wordfishing.service.NotificationUpdateService;
import pl.dominikhinc.wordfishing.service.SplitText;


public class GameScreen extends AbstractScreen implements Input.TextInputListener {

    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private Question question;
    private Skin skin;
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
    private TextField textField;
    private String choosenBook;
    private Button sendButton;
    private String folder;
    private Label answerReaction;


    private boolean isTextInputOpened = false;


    public GameScreen(WordFishing game,String chooseBook, String folder) {
        super(game);
        init(chooseBook,folder);
    }

    protected void init(String chooseBook, String folder) {
        initBgImage();
        loadSkin();
        this.choosenBook = chooseBook;
        this.folder = folder;
        initBackToMenuButton();
        initQuestionList();
        initAnswerList();
        initAnswerButtons();
        createQuestion();
        createLastQuestion();
        initTextField();
        initAnswerReacion();
        //setNotifications();
    }

    private void initAnswerReacion() {
        answerReaction = new Label("",skin,"gibson");
        stage.addActor(answerReaction);
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
    }

    private void initBgImage() {

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
    private void initTextField() {
        if(game.isTextInput() == true){
            textField = new TextField("",game.getSkin());
            textField.setSize(700*WordFishing.SCALE,100*WordFishing.SCALE);
            textField.setPosition(game.SCREEN_WIDTH/2-textField.getWidth()/2,game.SCREEN_HEIGHT/2-textField.getHeight());
            stage.addActor(textField);

            Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
            TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(game.assetManager.get("arrow-alt-right.png",Texture.class)));
            buttonStyle.up = textureRegionDrawable;
            buttonStyle.down = textureRegionDrawable;
            sendButton = new Button(buttonStyle);
            sendButton.setSize(100*WordFishing.SCALE,100*WordFishing.SCALE);
            sendButton.setPosition(textField.getX()+textField.getWidth(),textField.getY());
            sendButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    reactOnClick();
                }
            });
            stage.addActor(sendButton);
        }
    }
    private void createQuestion() {
        if(question != null){
            question.remove();
        }
        currentQuestionIndex = MathUtils.random(0,questionArrayList.size()-1);
        question = questionArrayList.get(currentQuestionIndex);
        stage.addActor(question);
        /*if(game.isTextInput() == true){
            question.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    question.setTouchable(Touchable.disabled);
                    reactOnClick();
                }
            });
        }*/
        createAnswers();
    }

    private void createLastQuestion() {
        if(game.isComplex() == false){
            lastQuestion = new Question("Press anywhere to go back to main menu","",skin,game);
        }
        if(game.isComplex() == true){
            lastQuestion = new Question("Press anywhere to continue to second part","",skin,game);
        }

    }

    private void reactOnClick() {
            /*if(isTextInputOpened == false){
                Gdx.input.getTextInput(this, question.getQuestion(), "", "Odpowiedź");
            }
            isTextInputOpened = true;*/
            this.text = textField.getText().toLowerCase();
            checkAnswerText();
            textField.setText("");

    }

    private void initQuestionList() {
        loadQuestionsAndAnswers = new LoadQuestionsAndAnswers(skin, skin, game,choosenBook,folder);
        questionArrayList = loadQuestionsAndAnswers.getQuestionArrayList();
        questionArrayList.remove(0);
        //arraySize = questionArrayList.size();
    }
    private void initAnswerList(){
        answerArrayList = loadQuestionsAndAnswers.getAnswerArrayList();
        answerArrayList.remove(0);
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
            correctAnswer();

            question.setCorrectAnswersToGo(question.getCorrectAnswersToGo() - 1);
            if(question.getCorrectAnswersToGo() < 1){
                questionArrayList.remove(currentQuestionIndex);

            }
            if(questionArrayList.isEmpty() == true){
                displayEndScreen();
            }else{
                createQuestion();
            }
        } else {
            wrongAnswer();
            displayWrongAnswerLabel();
        }


    }
    private void checkAnswerText(){
        boolean isWrong = false;
        if (text.equals(question.getAnswer().toLowerCase())){
            correctAnswer();
            question.setCorrectAnswersToGo(question.getCorrectAnswersToGo() - 1);
            if(question.getCorrectAnswersToGo() < 1){
                questionArrayList.remove(currentQuestionIndex);
            }
        }else{
            wrongAnswer();
            Gdx.input.setOnscreenKeyboardVisible(false);
            isWrong = true;
            displayWrongAnswerLabel();
        }

        if(questionArrayList.isEmpty() == true){
            displayEndScreen();
        }else{
            if(isWrong == true){
                //question.setTouchable(Touchable.disabled);
                textField.setTouchable(Touchable.disabled);
                sendButton.setTouchable(Touchable.disabled);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        //question.setTouchable(Touchable.enabled);
                        textField.setTouchable(Touchable.enabled);
                        sendButton.setTouchable(Touchable.enabled);
                        createQuestion();
                    }
                }, 3);
            }else{
                createQuestion();
            }
        }
    }
    private void displayWrongAnswerLabel(){
        String correctAnswer = question.getAnswer();
        if(correctAnswer.length() >= 25){
            SplitText splitText = new SplitText();
            correctAnswer = splitText.splitText(23,correctAnswer);
        }
        final Label label = new Label(correctAnswer,skin,"wrong");
        label.setColor(0.9f, 0.1f, 0.1f,1);
        if(game.isTextInput()){
            label.setPosition(game.SCREEN_WIDTH/2-label.getWidth()/2,game.SCREEN_HEIGHT/4);
        }else{
            label.setPosition(game.SCREEN_WIDTH/2-label.getWidth()/2,game.SCREEN_HEIGHT/2 - label.getHeight());
        }


        stage.addActor(label);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                label.remove();
            }
        }, 3);
    }

    private void displayEndScreen(){
        stage.addActor(lastQuestion);
        if(game.isTextInput() == false){
            for(Answer an :answerButtonList){
                an.setText("");
            }
        }
        Gdx.input.setOnscreenKeyboardVisible(false);
        Button endGameButton = new Button(new Button.ButtonStyle());
        endGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(game.isComplex() == false){
                    game.getPreferences().putLong(choosenBook+".TimeWhenCompleted",TimeUtils.millis());
                    game.getPreferences().flush();
                    game.setScreen(new MenuScreen(game));
                }
                if(game.isComplex() == true){
                    game.setComplex(false);
                    game.setQuestionInEnglish(false);
                    game.setTextInput(true);
                    game.setScreen(new GameScreen(game,choosenBook,folder));
                    setNotifications();
                }
            }
        });
        endGameButton.setHeight(game.SCREEN_HEIGHT);
        endGameButton.setWidth(game.SCREEN_WIDTH);
        stage.addActor(endGameButton);
    }

    private void setNotifications() {
        int lastBorder = game.getPreferences().getInteger(choosenBook+"days");
        switch(lastBorder){
            case 0:game.getPreferences().putInteger(choosenBook+"days",2);break;
            case 2:game.getPreferences().putInteger(choosenBook+"days",3);break;
            case 3:game.getPreferences().putInteger(choosenBook+"days",4);break;
            case 4:game.getPreferences().putInteger(choosenBook+"days",5);break;
            case 5:game.getPreferences().putInteger(choosenBook+"days",0);break;
        }
        game.getPreferences().flush();
        int time = game.getPreferences().getInteger(choosenBook+"days");
        if(time != 0){
            if(time != 2){
                if(game.getPreferences().getLong(choosenBook+".TimeWhenCompleted") != 0){
                    long temp = ((TimeUtils.millis() - game.getPreferences().getLong(choosenBook+".TimeWhenCompleted"))/1000)/86400;
                    int x = (int) temp - lastBorder;
                    if(x > 0 && time - x > 0){
                        time = time - x;
                    }

                }
            }
            game.getNotificationHandler().showNotification("Czas na nauke!","Musisz powtórzyć sobie: "+choosenBook,time*86400,IdCalculate.calculate(choosenBook)+1);
            game.getNotificationHandler().showNotification("Czas na nauke!","Musisz powtórzyć sobie: "+choosenBook,(time+1)*86400,IdCalculate.calculate(choosenBook)+5);
            game.getNotificationHandler().showNotification("Powiadomienie","Zakończenie nauki: "+choosenBook,(time+2)*86400,IdCalculate.calculate(choosenBook)+10);
            NotificationUpdateService notificationUpdateService = new NotificationUpdateService(game);
            notificationUpdateService.cancel(choosenBook);
            game.getNotificationHandler().showNotification("Czas na nauke!","Musisz powtórzyć sobie: "+choosenBook,time*86400,IdCalculate.calculate(choosenBook)+1);
            game.getNotificationHandler().showNotification("Czas na nauke!","Musisz powtórzyć sobie: "+choosenBook,(time+1)*86400,IdCalculate.calculate(choosenBook)+5);
            game.getNotificationHandler().showNotification("Powiadomienie","Zakończenie nauki: "+choosenBook,(time+2)*86400,IdCalculate.calculate(choosenBook)+10);
        }else{
            game.getNotificationHandler().showNotification("Czas na nauke!","Musisz powtórzyć sobie: "+choosenBook,1,IdCalculate.calculate(choosenBook)+1);
            game.getNotificationHandler().showNotification("Czas na nauke!","Musisz powtórzyć sobie: "+choosenBook,2,IdCalculate.calculate(choosenBook)+5);
            game.getNotificationHandler().showNotification("Powiadomienie","Zakończenie nauki: "+choosenBook,3,IdCalculate.calculate(choosenBook)+10);
            NotificationUpdateService notificationUpdateService = new NotificationUpdateService(game);
            notificationUpdateService.cancel(choosenBook);
        }
    }

    private void wrongAnswer() {
        question.shake();
        float delay;
       // if(game.isTextInput() == true){
            delay = 3;
       // }else{
        //    delay = 1.5f;
        //}
        currentColor.set(0.735f, 0.052f, 0.052f,1);
        answerReaction.setText("WRONG");
        answerReaction.setPosition(question.getX() + question.getWidth()/4,question.getY()-answerReaction.getHeight()-60);
        answerReaction.setColor(0.9f, 0.1f, 0.1f,1);
        if(answerButtonList != null && !answerButtonList.isEmpty()) {
            for (Answer answer : answerButtonList) {
                answer.setTouchable(Touchable.disabled);
            }
        }
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                shuffleColor();
                answerReaction.setText("");
                if(game.isTextInput() == false){
                    createQuestion();
                }
                if(answerButtonList != null && !answerButtonList.isEmpty()) {
                    for (Answer answer : answerButtonList) {
                        answer.setTouchable(Touchable.enabled);
                    }
                }
            }
        }, delay);
    }

    private void correctAnswer() {
        currentColor.set(0.052f, 0.535f, 0.095f,1);
        answerReaction.setText("CORRECT");
        answerReaction.setPosition(question.getX()+ question.getWidth()/6,question.getY()-answerReaction.getHeight()-60);
        answerReaction.setColor(0.1f, 0.7f, 0.1f,1);
        if(answerButtonList != null && !answerButtonList.isEmpty()){
            for(Answer answer: answerButtonList){
                answer.setTouchable(Touchable.disabled);
            }
        }

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                shuffleColor();
                answerReaction.setText("");
                if(answerButtonList != null && !answerButtonList.isEmpty()) {
                    for (Answer answer : answerButtonList) {
                        answer.setTouchable(Touchable.enabled);
                    }
                }
            }
        }, 1f);
    }

    public void input(String text) {
        this.text = text.toLowerCase();
        //question.setTouchable(Touchable.enabled);
        isTextInputOpened = false;
        checkAnswerText();
    }
    @Override
    public void canceled() {
        question.setTouchable(Touchable.enabled);
        isTextInputOpened = false;
    }
}
