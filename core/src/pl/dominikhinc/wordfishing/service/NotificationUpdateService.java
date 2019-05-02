package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;

import pl.dominikhinc.wordfishing.WordFishing;

public class NotificationUpdateService {

    private WordFishing game;

    public NotificationUpdateService(WordFishing game){
        this.game = game;
    }

    public void update(){
        FileHandle dirHandle = Gdx.files.internal("data/");;
        String l = null;
        ArrayList<String> listOfFileNames = new ArrayList<String>();

        for (FileHandle entry: dirHandle.list()) {
            l = entry.name();
            listOfFileNames.add(l);
        }
        String[] listOfBooks = new String[listOfFileNames.size()];
        int i = 0;

        for(String s: listOfFileNames){
            listOfBooks[i] = s.substring(0,s.length()-4);

            if(game.getPreferences().getInteger(listOfBooks[i]+"days") != 0){
                if(TimeUtils.millis() - game.getPreferences().getLong(listOfBooks[i]+".TimeWhenCompleted") > (game.getPreferences().getInteger(listOfBooks[i]+"days")+2)*86400){
                    game.getPreferences().putInteger(listOfBooks[i]+"days",0);
                    game.getPreferences().putLong(listOfBooks[i]+".TimeWhenCompleted",0);
                    game.getPreferences().flush();
                }
            }
            i++;
        }
    }
    public void cancel(String choosenBook){

        game.getNotificationHandler().cancelNotification(IdCalculate.calculate(choosenBook)+1);
        game.getNotificationHandler().cancelNotification(IdCalculate.calculate(choosenBook)+5);
        game.getNotificationHandler().cancelNotification(IdCalculate.calculate(choosenBook)+10);

    }

}
