package pl.dominikhinc.wordfishing;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import pl.dominikhinc.wordfishing.screens.SplashScreen;
import pl.dominikhinc.wordfishing.service.AssetMenager;
import pl.dominikhinc.wordfishing.service.BackgroundTile;
import pl.dominikhinc.wordfishing.service.NotificationHandler;

public class WordFishing extends Game {

	public static int SCREEN_WIDTH = 1080;
	public static int SCREEN_HEIGHT = 1920;
	public static float SCALE = 1;
	public final static String GAME_TITLE = "WordFishing";
    final public static String textInputPreferences = "textInputPreferences";
	//final public static String questionInEnglishPreferences = "questionInEnglishPreferences";
	final public static String correctAnswersNeeded = "correctAnswersNeeded";
	final public static String personalListUse = "personalListUse";
	final public static String randomColor = "randomColor";
	final public static String backgroundCol = "backgroundColor";

	private Preferences preferences;

	private boolean textInput = false;
	private boolean paused;
	private boolean questionInEnglish = false;

    private boolean complex = false;

	private int correctAnswersNeededInt = 1;

	private Skin skin;

	private AssetMenager assetMenager;
	public AssetManager assetManager;


	private Skin skinSmallFont;

	private Texture logo;
	private Texture goBackButton;

    private Array<BackgroundTile> backgroundTiles;

	public Color backgroundColor;

	private Texture mcmRPG;
	private Texture mcmrok;
	private NotificationHandler notificationHandler;
	public boolean isPersonalList;
	public boolean isRandomColor = true;


	public NotificationHandler getNotificationHandler() {
		return notificationHandler;
	}

	public void setNotificationHandler(NotificationHandler notificationHandler) {
		this.notificationHandler = notificationHandler;
	}


	@Override
	public void create () {
		SCREEN_WIDTH = Gdx.graphics.getWidth();
		SCREEN_HEIGHT = Gdx.graphics.getHeight();
		calculateScale();

		backgroundColor = new Color();
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init(){
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Gdx.gl20.glLineWidth(300);
		initFont();
		initSkin();
		initPreferences();
		backgroundTiles = new Array<>();
		for(int i = 0; i < SCREEN_WIDTH;i+=120){
            for(int j = 0; j < SCREEN_HEIGHT;j+=120){
                backgroundTiles.add(new BackgroundTile());
            }
        }
	}


	private void initPreferences() {
		 preferences = Gdx.app.getPreferences("WordFishing Preferences");
		 if(preferences.contains(textInputPreferences)){
			 textInput = preferences.getBoolean(textInputPreferences);
		 }else{
		 	textInput = false;
		 }
		 if(preferences.contains(personalListUse)){
			 isPersonalList = preferences.getBoolean(personalListUse);
		 }else{
		 	isPersonalList = false;
		 }

		 //questionInEnglish = preferences.getBoolean(questionInEnglishPreferences);
		if(preferences.contains(correctAnswersNeeded)){
			correctAnswersNeededInt = preferences.getInteger(correctAnswersNeeded);
		}else{
			correctAnswersNeededInt = 1;
		}

		 if (preferences.contains(randomColor)){
			 isRandomColor = preferences.getBoolean(randomColor);
		 }else {
		 	isRandomColor = true;
		 }

		 float r = 0.17f;
		 float g = 0.4f;
		 float b = 0.7f;
		 float a = 0.5f;
		 if(preferences.contains(backgroundCol+"r")){
			 r = preferences.getFloat(backgroundCol+"r");
			 g = preferences.getFloat(backgroundCol+"g");
			 b = preferences.getFloat(backgroundCol+"b");
			 //a =preferences.getFloat(backgroundCol+"a");
		 }

		 backgroundColor.set(r,g,b,a);
	}
	public void calculateScale(){
		SCALE = (float)SCREEN_WIDTH/1080;
		if(skin != null){
			skin.getFont("font_primary_128").getData().setScale(SCALE);
			skin.getFont("font_ukon_128").getData().setScale(SCALE);
			skin.getFont("font_gibson_128").getData().setScale(SCALE);
			skin.getFont("font_primary_64").getData().setScale(SCALE);

		}
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

    public Array<BackgroundTile> getBackgroundTiles() {
        return backgroundTiles;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

	public void setAssetMenager(AssetMenager assetMenager) {
		this.assetMenager = assetMenager;
	}

	public Texture getMcmRPG() {
		return mcmRPG;
	}
	public void setMcmRPG(Texture mcmRPG) {
		this.mcmRPG = mcmRPG;
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

	public Skin getSkin() {
		return skin;
	}
	public Texture getLogo() {
		return logo;
	}
	public void setLogo(Texture logo) {
		this.logo = logo;
	}


}
