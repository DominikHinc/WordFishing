package pl.dominikhinc.wordfishing;

import com.badlogic.gdx.Game;

import pl.dominikhinc.wordfishing.screens.SplashScreen;

public class WordFishing extends Game {

	final public static int SCREEN_WIDTH = 1080;
	final public static int SCREEN_HEIGHT = 1920;
	public final static String GAME_TITLE = "WordFishing";

	private boolean paused;
	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this));
	}

	private void init(){

	}
	
	@Override
	public void dispose () {

	}

	public void setPaused(boolean b) {
		this.paused = paused;
	}
	public boolean isPaused() {
		return paused;
	}
}
