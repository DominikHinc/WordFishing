package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.GoBackButtonCreator;

public class CreditsScreen extends AbstractScreen {
    private GoBackButtonCreator goBackButtonCreator;
    private Button goBackButton;
    private Label credits;
    private ScrollPane scrollPane;
    public CreditsScreen(WordFishing game) {
        super(game);
    }

    @Override
    protected void init() {
        initBackToMenuButton();
        initCredits();

    }

    private void initCredits() {
        credits = new Label("",game.getSkin());
        credits.setWrap(true);
        credits.setText("Aplikacja powstała w ramach spontanicznego projektu pozalekcyjnego w I LO w Bytowie.\n\nProgramista: \nDominik Hinc\n\nPomysłodawca i patron projektu:\nNauczyciel informatyki - Marcin Hesse \noraz klasa o profilu informatycznym rocznik 2017/2020\n\nPomocy w dziedzinie graficznej udzielił:\nIgor Hinc\n\nSpecjalne podziękowania dla:\n\nDyrektora I LO w Bytowie Edwarda Mazura\n\nPrzedstawicielki wydawnictwa Macmillan Katarzynie Lepackiej\n\nKlasy IIID, a w szczególności:\nKajetanowi Cyrze, Oli Dąbek, Marcie Ginter, Kacprowi Jarzembińskiemu, Oktawiuszowi Labudzie, Karolowi Sabiszowi, Julce Sidorko, Kacprowi Stankiewiczowi, Julce Strasser, Igorowi Szemela, Adamowi Tymoszowi, Aaronowi Wantoch Rekowskiemu, Jakubowi Windakowi, Jakubowi Wiśniewskiemu, Sebastianowi Wnuk-Lipińskiemu, Wiktorowi Zygier\n\nGrupy Informatycznej Klasy IID za pomoc w przygotowaniu słowników, do której należeli:\nMarcela Bułak, Filip Jorka, Julia Maj, Paweł Mańczak, Szymon Rzepka, Anna Saldat, Piotr Sierzputowski, Aleks Stachyra, Mikołaj Szczerba, Stanisław Szmidt, Maja Trojańska, Marcelina Typa, Magdalena Wojtczak, Nikodem Ziembowski i Mateusz Hesse\n\nOraz wszystkich tych, którzy przyczynili się do powstania tej aplikacji, a którzy nie zostali wskazani z imienia");
        scrollPane = new ScrollPane(credits);
        scrollPane.setBounds(WordFishing.SCREEN_WIDTH/12,WordFishing.SCREEN_HEIGHT/14,WordFishing.SCREEN_WIDTH - WordFishing.SCREEN_WIDTH/6,WordFishing.SCREEN_HEIGHT-(WordFishing.SCREEN_HEIGHT-goBackButton.getY()-5)-WordFishing.SCREEN_HEIGHT/7);
        stage.addActor(scrollPane);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        scrollPane.act(delta);
        spriteBatch.begin();
        stage.draw();
        stage.act();
        spriteBatch.end();
    }

    private void update() {
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
}
