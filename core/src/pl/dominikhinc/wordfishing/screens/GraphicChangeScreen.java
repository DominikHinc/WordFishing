package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;
import pl.dominikhinc.wordfishing.service.IdCalculate;

public class GraphicChangeScreen extends AbstractScreen {

    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private Slider sr,sg,sb,sa;
    private Label lr,lg,lb,la,li;
    private CheckBox randomColor;


    public GraphicChangeScreen(WordFishing game) {
        super(game);
    }

    @Override
    protected void init() {
        initBackToMenuButton();
        initSliders();
        initLabels();
        initCheckBox();
    }



    private void initCheckBox() {
        randomColor = new CheckBox("   Losowe Kolory",game.getSkin());
        randomColor.setPosition(game.SCREEN_WIDTH/12,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/4-2.5f*WordFishing.SCREEN_HEIGHT/16);
        randomColor.getImage().setOrigin(randomColor.getImage().getWidth()/2,randomColor.getImage().getHeight()/2);
        randomColor.getImage().setScale(3*WordFishing.SCALE);
        randomColor.setChecked(game.isRandomColor);
        randomColor.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeListener.ChangeEvent event, Actor actor) {
                game.setTextInput(game.isRandomColor = !game.isRandomColor);
                game.getPreferences().putBoolean(WordFishing.randomColor,game.isRandomColor);
                game.getPreferences().flush();
                shuffleColor();

            }
        });
        stage.addActor(randomColor);
    }

    private void initSliders() {
        sr = new Slider(0,1,0.01f,false,game.getSkin());
        sg = new Slider(0,1,0.01f,false,game.getSkin());
        sb = new Slider(0,1,0.01f,false,game.getSkin());
        //sa = new Slider(0,255,0.01f,false,game.getSkin());

        sr.setSize(WordFishing.SCREEN_WIDTH/2,WordFishing.SCREEN_HEIGHT/12);
        sg.setSize(WordFishing.SCREEN_WIDTH/2,WordFishing.SCREEN_HEIGHT/12);
        sb.setSize(WordFishing.SCREEN_WIDTH/2,WordFishing.SCREEN_HEIGHT/12);
        //sa.setSize(WordFishing.SCREEN_WIDTH/2,WordFishing.SCREEN_HEIGHT/12);

        sr.getStyle().knob.setMinHeight(50*WordFishing.SCALE);
        sr.getStyle().knob.setMinWidth(50*WordFishing.SCALE);
        sg.getStyle().knob.setMinHeight(50*WordFishing.SCALE);
        sg.getStyle().knob.setMinWidth(50*WordFishing.SCALE);
        sb.getStyle().knob.setMinHeight(50*WordFishing.SCALE);
        sb.getStyle().knob.setMinWidth(50*WordFishing.SCALE);

        sr.setPosition(WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/4);
        sg.setPosition(WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/4-WordFishing.SCREEN_HEIGHT/16);
        sb.setPosition(WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/4-2*WordFishing.SCREEN_HEIGHT/16);
        //sa.setPosition(WordFishing.SCREEN_WIDTH/8,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/4-3*WordFishing.SCREEN_HEIGHT/16);

        sr.setValue(game.backgroundColor.r);
        sg.setValue(game.backgroundColor.g);
        sb.setValue(game.backgroundColor.b);
        //sa.setValue(game.backgroundColor.a);


        stage.addActor(sr);
        stage.addActor(sg);
        stage.addActor(sb);
        //stage.addActor(sa);
    }

    private void initLabels() {
        lr = new Label("R:",game.getSkin());
        lg = new Label("G:",game.getSkin());
        lb = new Label("B:",game.getSkin());
        //la = new Label("A:",game.getSkin());
        li = new Label("Dostosuj kolor tła:",game.getSkin());

        lr.setPosition(WordFishing.SCREEN_WIDTH/16,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/4+lr.getHeight());
        lg.setPosition(WordFishing.SCREEN_WIDTH/16,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/4-WordFishing.SCREEN_HEIGHT/16+lg.getHeight());
        lb.setPosition(WordFishing.SCREEN_WIDTH/16,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/4-2*WordFishing.SCREEN_HEIGHT/16+lb.getHeight());
        //la.setPosition(WordFishing.SCREEN_WIDTH/16,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/4-3*WordFishing.SCREEN_HEIGHT/16+la.getHeight());
        li.setPosition(WordFishing.SCREEN_WIDTH/16,WordFishing.SCREEN_HEIGHT-WordFishing.SCREEN_HEIGHT/6);


        stage.addActor(lr);
        stage.addActor(lg);
        stage.addActor(lb);
        //stage.addActor(la);
        stage.addActor(li);

    }


    private void initBackToMenuButton() {
        goBackButtonCreator = new GoBackButtonCreator();
        goBackButton = goBackButtonCreator.createButton(game);
        goBackButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MenuScreen(game));
            }
        });
        stage.addActor(goBackButton);

    }



    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        if(game.isRandomColor == false){
            if(sr.getValue() != game.backgroundColor.r || sg.getValue() != game.backgroundColor.g ||sb.getValue() != game.backgroundColor.b){
                game.backgroundColor.r = sr.getValue();
                game.backgroundColor.g = sg.getValue();
                game.backgroundColor.b = sb.getValue();
                //game.backgroundColor.a = sa.getValue();

                game.getPreferences().putFloat(WordFishing.backgroundCol+"r",sr.getValue());
                game.getPreferences().putFloat(WordFishing.backgroundCol+"g",sg.getValue());
                game.getPreferences().putFloat(WordFishing.backgroundCol+"b",sb.getValue());
                //game.getPreferences().putFloat(WordFishing.backgroundCol+"a",sa.getValue());
                game.getPreferences().flush();
                shuffleColor();
            }

        }

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }

    private void update() {
    }
}
