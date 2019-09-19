package pl.dominikhinc.wordfishing.frames;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.screens.BookChooseScreen;
import pl.dominikhinc.wordfishing.screens.PersonalListScreen;


public class PersonalListLabel extends Label {
    public int index;
    public boolean checked;
    public PersonalListLabel(String text, Skin skin, final int index, final PersonalListScreen personalListScreen){
        super(text,skin);
        //this.setWrap(true);
        this.index = index;
        checked = false;
        this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
               checked = !checked;
               update();
               personalListScreen.clicked(index);
            }
        });
    }

    public void update(){
        if(checked){
            this.setColor(0.1f, 0.7f, 0.1f,1);
        }else {
            this.setColor(1,1,1,1);
        }
    }
    public void check(){
        checked = true;
    }
    public void uncheck(){
        checked = false;
    }

    public int getIndex() {
        return index;
    }
}
