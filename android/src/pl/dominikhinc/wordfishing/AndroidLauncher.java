package pl.dominikhinc.wordfishing;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		WordFishing game = new WordFishing();
		AndroidAdapter androidAdapter = new AndroidAdapter(this);
		game.setNotificationHandler(androidAdapter);


		initialize(game, config);

	}

}
