package pl.dominikhinc.wordfishing.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.dominikhinc.wordfishing.WordFishing;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WordFishing.SCREEN_WIDTH;
		config.height = WordFishing.SCREEN_HEIGHT;
		config.title = WordFishing.GAME_TITLE;
		//config.resizable = false;

		new LwjglApplication(new WordFishing(), config);
	}
}
