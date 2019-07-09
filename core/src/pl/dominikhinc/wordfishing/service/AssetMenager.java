package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import pl.dominikhinc.wordfishing.WordFishing;

public class AssetMenager {


    public AssetMenager(WordFishing game) {
        initFonts(game);
        initSkins(game);
        initTextures(game);
    }

    private void initTextures(WordFishing game) {
        game.setDefaultBg(new Texture("Temporary.Menu.Background.png"));
        game.setCorrectAnswer(new Texture("Correct_Answer.png"));
        game.setWrongAnswer(new Texture("Wrong_Answer.png"));
        game.setLogo(new Texture("Logo.png"));
        game.setGoBackButton(new Texture("SYMB_LEFTARROW.png"));
        game.setMcmrok(new Texture("macmillan repetytorium osmoklasisty.png"));
        game.setGoBackerButton(new Texture("GoForward.png"));
        game.setTempToDelete(new Texture("repetytorium-do-szkol-ponadgimnazjalnych.jpg"));
    }

    private void initSkins(WordFishing game) {
        game.setSkin(new Skin(Gdx.files.internal("glassy-ui.json")));
        game.setSkinSmallFont(new Skin(Gdx.files.internal("glassy-ui.json")));
        game.getSkinSmallFont().getFont("font").getData().setScale(0.7f);
        game.getSkinSmallFont().getFont("font-big").getData().setScale(0.7f);
        game.setSkin2(new Skin(Gdx.files.internal("questionSkin/glassy-ui.json")));

    }

    private void initFonts(WordFishing game) {
        game.setFont(new BitmapFont(Gdx.files.internal("Calibri.fnt")));
        game.setFontRed(new BitmapFont(Gdx.files.internal("redFont/Calibri.fnt")));


    }
}
