package pl.dominikhinc.wordfishing;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import pl.dominikhinc.wordfishing.screens.SplashScreen;

public class WordFishing extends Game {

	final public static int SCREEN_WIDTH = 1080;
	final public static int SCREEN_HEIGHT = 1920;
	public final static String GAME_TITLE = "WordFishing";



	private BitmapFont font;
	private boolean paused;
	private Skin skin;

	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init(){
		initFont();
		initSkin();
	}

	private void initSkin() {
		skin = new Skin(Gdx.files.internal("glassy-ui.json"));
		skin.add("font",font);
		skin.add("font-big",font);
	}

	private void initFont() {
		font = new BitmapFont(Gdx.files.internal("Test.fnt"));
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
	public Skin getSkin() {
		return skin;
	}
}
