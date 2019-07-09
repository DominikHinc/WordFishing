package pl.dominikhinc.wordfishing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import pl.dominikhinc.wordfishing.screens.SplashScreen;
import pl.dominikhinc.wordfishing.service.NotificationHandler;

public class WordFishing extends Game {

	public static int SCREEN_WIDTH = 1080;
	public static int SCREEN_HEIGHT = 1920;
	public final static String GAME_TITLE = "WordFishing";
    final public static String textInputPreferences = "textInputPreferences";
	//final public static String questionInEnglishPreferences = "questionInEnglishPreferences";
	final public static String correctAnswersNeeded = "correctAnswersNeeded";

	private BitmapFont font;
    private BitmapFont fontRed;

	private Preferences preferences;

	private boolean textInput = false;
	private boolean paused;
	private boolean questionInEnglish = false;

    private boolean complex = false;

	private int correctAnswersNeededInt = 1;

	private Skin skin;
	private Skin skin2;



	private Skin skinSmallFont;

    private Texture defaultBg;
    private Texture correctAnswer;
    private Texture wrongAnswer;
	private Texture logo;
	private Texture goBackButton;



	private Texture tempToDelete;



	private Texture goBackerButton;



	private Texture mcmrok;
	private NotificationHandler notificationHandler;

	public NotificationHandler getNotificationHandler() {
		return notificationHandler;
	}

	public void setNotificationHandler(NotificationHandler notificationHandler) {
		this.notificationHandler = notificationHandler;
	}


	@Override
	public void create () {
		init();
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		this.setScreen(new SplashScreen(this));
	}

	private void init(){
		initFont();
		initSkin();
		initPreferences();
	}


	private void initPreferences() {
		 preferences = Gdx.app.getPreferences("WordFishing Preferences");
		 textInput = preferences.getBoolean(textInputPreferences);
		 //questionInEnglish = preferences.getBoolean(questionInEnglishPreferences);
		 correctAnswersNeededInt = preferences.getInteger(correctAnswersNeeded);
	}

	private void initSkin() {
		//skin = new Skin(Gdx.files.internal("glassy-ui.json"));
		/*skin.add("font",font);
		skin.add("font-big",font);*/
		//skin2 = new Skin(Gdx.files.internal("questionSkin/glassy-ui.json"));
	}

	private void initFont() {
		//font = new BitmapFont(Gdx.files.internal("Calibri.fnt"));
		//fontRed = new BitmapFont(Gdx.files.internal("redFont/Calibri.fnt"));
	}

	@Override
	public void dispose () {
		this.dispose();
	}

	/*
	**********************
	* Setters and Getters*
	* ********************
	 */
	public Texture getTempToDelete() {
		return tempToDelete;
	}
	public void setTempToDelete(Texture tempToDelete) {
		this.tempToDelete = tempToDelete;
	}
	public Texture getGoBackerButton() {
		return goBackerButton;
	}
	public void setGoBackerButton(Texture goBackerButton) {
		this.goBackerButton = goBackerButton;
	}
	public Texture getMcmrok() {
		return mcmrok;
	}
	public void setMcmrok(Texture mcmrok) {
		this.mcmrok = mcmrok;
	}
	public boolean isComplex() {
        return complex;
    }
    public void setComplex(boolean complex) {
        this.complex = complex;
    }
	public Skin getSkinSmallFont() {
		return skinSmallFont;
	}
	public void setSkinSmallFont(Skin skinSmallFont) {
		this.skinSmallFont = skinSmallFont;
	}
	public int getCorrectAnswersNeededInt() {
		return correctAnswersNeededInt;
	}
	public void setCorrectAnswersNeededInt(int correctAnswersNeededInt) {
		this.correctAnswersNeededInt = correctAnswersNeededInt;
	}
	public Texture getGoBackButton() {
		return goBackButton;
	}
	public void setGoBackButton(Texture goBackButton) {
		this.goBackButton = goBackButton;
	}
	public BitmapFont getFontRed() {
		return fontRed;
	}
	public boolean isQuestionInEnglish() {
		return questionInEnglish;
	}
	public void setQuestionInEnglish(boolean questionInEnglish) {
		this.questionInEnglish = questionInEnglish;
	}
	public Preferences getPreferences() {
		return preferences;
	}
	public boolean isTextInput() {
		return textInput;
	}
	public void setTextInput(boolean textInput) {
		this.textInput = textInput;
	}
	public void setPaused(boolean b) {
		this.paused = paused;
	}
	public boolean isPaused() {
		return paused;
	}
	public BitmapFont getFont() {
		return font;
	}
	public Skin getSkin() {
		return skin;
	}
	public Skin getSkin2() {
		return skin2;
	}
    public Texture getDefaultBg() {
        return defaultBg;
    }
    public void setDefaultBg(Texture defaultBg) {
        this.defaultBg = defaultBg;
    }
    public Texture getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(Texture correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public Texture getWrongAnswer() {
        return wrongAnswer;
    }
    public void setWrongAnswer(Texture wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }
    public void setSkin(Skin skin) {
        this.skin = skin;
    }
    public void setSkin2(Skin skin2) {
        this.skin2 = skin2;
    }
    public void setFontRed(BitmapFont fontRed) {
        this.fontRed = fontRed;
    }
    public void setFont(BitmapFont font) {
        this.font = font;
    }
	public Texture getLogo() {
		return logo;
	}
	public void setLogo(Texture logo) {
		this.logo = logo;
	}


}
