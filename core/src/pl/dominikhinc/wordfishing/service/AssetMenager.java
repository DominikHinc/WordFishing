package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;

import pl.dominikhinc.wordfishing.WordFishing;

public class AssetMenager {

    public AssetManager assetManager;
    public AssetMenager(WordFishing game) {
        assetManager = new AssetManager();
        game.assetManager = assetManager;
    }
    public void load(){
        assetManager.load("Logo.png",Texture.class);
        assetManager.load("SYMB_LEFTARROW.png",Texture.class);
        assetManager.load("macmillan repetytorium osmoklasisty.png",Texture.class);
        assetManager.load("repetytorium-do-szkol-ponadgimnazjalnych.jpg",Texture.class);
        assetManager.load("gatewayplus1.jpg",Texture.class);
        assetManager.load("gatewayplus2.jpg",Texture.class);
        assetManager.load("gatewayplus3.jpg",Texture.class);
        assetManager.load("gatewayplus4.jpg",Texture.class);
        assetManager.load("gatewayplus5.jpg",Texture.class);
        assetManager.load("arrow-alt-right.png",Texture.class);
    }
    public void setTextures(WordFishing game) {
        game.setLogo(assetManager.get("Logo.png",Texture.class));
        game.setGoBackButton(assetManager.get("SYMB_LEFTARROW.png",Texture.class));
        game.setMcmrok(assetManager.get("macmillan repetytorium osmoklasisty.png",Texture.class));
        game.setMcmRPG(assetManager.get("repetytorium-do-szkol-ponadgimnazjalnych.jpg",Texture.class));
    }

    public void setSkins(WordFishing game) {
        //Generate TTF
        ObjectMap<String,Object> resources = new ObjectMap<String, Object>();
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Gisbon.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "ąśęńółĄŚĘÓŃŁćĆŻżŹź";
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;

        float[] sizesFontF = {16*WordFishing.SCALE,24*WordFishing.SCALE,32*WordFishing.SCALE,48*WordFishing.SCALE,64*WordFishing.SCALE,96*WordFishing.SCALE,100*WordFishing.SCALE,128*WordFishing.SCALE,148*WordFishing.SCALE,169*WordFishing.SCALE};
        int[] namesize = {16,24,32,48,64,96,100,128,148,169};
        int sizesFont[] = new int[sizesFontF.length];
        int i =0;
        for(float f: sizesFontF){
            sizesFont[i] = (int)sizesFontF[i];
            i++;
        }
        i = 0;
        for (int size:sizesFont){
            fontParameter.size = size;
            final BitmapFont bitmapFont = fontGenerator.generateFont(fontParameter);
            bitmapFont.getData().markupEnabled = true;
            resources.put("font_gibson_" + namesize[i],bitmapFont);
            i++;
        }

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Amatic-Bold.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        fontParameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "ąśęńółĄŚĘÓŃŁćĆŻżŹź";
        i = 0;
        for (int size:sizesFont){
            fontParameter.size = size;
            final BitmapFont bitmapFont = fontGenerator.generateFont(fontParameter);
            bitmapFont.getData().markupEnabled = true;
            resources.put("font_primary_" + namesize[i],bitmapFont);
            i++;
        }
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("berkshireswash-regular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.minFilter = Texture.TextureFilter.Linear;
        fontParameter.magFilter = Texture.TextureFilter.Linear;
        fontParameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + "ąśęńółĄŚĘÓŃŁćĆŻżŹźaAbBcCdDeEfFgGhHiIjJkKlLmMnNoOuUpPrRsStTwWxXyYzZ";
        i = 0;
        for (int size:sizesFont){
            fontParameter.size = size;
            final BitmapFont bitmapFont = fontGenerator.generateFont(fontParameter);
            bitmapFont.getData().markupEnabled = true;
            resources.put("font_ukon_" + namesize[i],bitmapFont);
            i++;
        }

        fontGenerator.dispose();

        //load Skin
        final SkinLoader.SkinParameter skinParameter = new SkinLoader.SkinParameter("flat-earth-ui.atlas",resources);
        assetManager.load("flat-earth-ui.json",Skin.class,skinParameter);
        assetManager.finishLoading();
        game.setSkin(assetManager.get("flat-earth-ui.json",Skin.class));
        /*
        final SkinLoader.SkinParameter skinParameter = new SkinLoader.SkinParameter("glassy-ui.atlas",resources);
        assetManager.load("glassy-ui.json",Skin.class,skinParameter);
        assetManager.finishLoading();
        game.setSkin(assetManager.get("glassy-ui.json",Skin.class));*/

    }

}
