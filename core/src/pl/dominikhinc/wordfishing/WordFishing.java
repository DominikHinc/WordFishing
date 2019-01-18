package pl.dominikhinc.wordfishing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import pl.dominikhinc.wordfishing.screens.SplashScreen;

public class WordFishing extends Game {

	final public static int SCREEN_WIDTH = 1080;
	final public static int SCREEN_HEIGHT = 1920;
	public final static String GAME_TITLE = "WordFishing";



	private BitmapFont font;
	private boolean paused;

	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init(){
		initFont();
	}

	private void initFont() {
		font = new BitmapFont(Gdx.files.internal("Font_2.fnt"));
	}

	@Override
	public void dispose () {

	}

	/*
	**********************
	* Setters and Getters*
	* ********************
	 */
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
}
