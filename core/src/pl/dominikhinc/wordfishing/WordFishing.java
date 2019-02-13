package pl.dominikhinc.wordfishing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import pl.dominikhinc.wordfishing.screens.SplashScreen;

public class WordFishing extends Game {

	final public static int SCREEN_WIDTH = 1080;
	final public static int SCREEN_HEIGHT = 1920;
	public final static String GAME_TITLE = "WordFishing";
    final public static String textInputPreferences = "textInputPreferences";
	final public static String questionInEnglishPreferences = "questionInEnglishPreferences";

	private BitmapFont font;
	private BitmapFont fontRed;

	private Preferences preferences;

	private boolean textInput = false;
	private boolean paused;
	private boolean questionInEnglish = false;

	private Skin skin;
	private Skin skin2;

	@Override
	public void create () {
		init();
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
		 questionInEnglish = preferences.getBoolean(questionInEnglishPreferences);
	}

	private void initSkin() {
		skin = new Skin(Gdx.files.internal("glassy-ui.json"));
		skin.add("font",font);
		skin.add("font-big",font);
		skin2 = new Skin(Gdx.files.internal("questionSkin/glassy-ui.json"));
	}

	private void initFont() {
		font = new BitmapFont(Gdx.files.internal("Calibri.fnt"));
		fontRed = new BitmapFont(Gdx.files.internal("redFont/Calibri.fnt"));
	}

	@Override
	public void dispose () {

	}

	/*
	**********************
	* Setters and Getters*
	* ********************
	 */

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
	public void setFont(BitmapFont font) {
		this.font = font;
	}
	public Skin getSkin() {
		return skin;
	}
	public Skin getSkin2() {
		return skin2;
	}
}
