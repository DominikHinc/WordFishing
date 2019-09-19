package pl.dominikhinc.wordfishing.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import pl.dominikhinc.wordfishing.WordFishing;
import pl.dominikhinc.wordfishing.service.BackgroundTile;

public abstract class AbstractScreen implements Screen {

    protected WordFishing game;

    protected Stage stage;
    protected OrthographicCamera camera;
    protected SpriteBatch spriteBatch;
    protected ShapeRenderer shapeRenderer;
    protected Color currentColor;
    private boolean initialResize = true;

    public AbstractScreen(WordFishing game){
        this.game = game;
        createCamera();
        stage = new Stage(new FitViewport(WordFishing.SCREEN_WIDTH, WordFishing.SCREEN_HEIGHT, camera));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        shapeRenderer = new ShapeRenderer();
        if(game.isRandomColor){
            currentColor = new Color(MathUtils.random(0.65f),MathUtils.random(0.65f),MathUtils.random(0.65f),MathUtils.random(0.65f));
        }else {
            currentColor = new Color(game.backgroundColor);
        }

        for(BackgroundTile backgroundTile: game.getBackgroundTiles()){
            backgroundTile.shuffleColor();
        }
        init();
    }
    protected void shuffleColor(){
        if(game.isRandomColor){
            currentColor.set(MathUtils.random(0.65f),MathUtils.random(0.65f),MathUtils.random(0.65f),MathUtils.random(0.65f));
        }else {
            currentColor.set(game.backgroundColor);
        }

    }
    protected abstract void init();

    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WordFishing.SCREEN_WIDTH, WordFishing.SCREEN_HEIGHT);
        camera.update();
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if(game.getScreen() instanceof MenuScreen){

            }else{
                game.setScreen(new MenuScreen(game));
            }

        }
        shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(currentColor);

        shapeRenderer.rect(0,0,WordFishing.SCREEN_WIDTH,WordFishing.SCREEN_HEIGHT);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        int i = 0;
        int j = 0;
        for(BackgroundTile backgroundTile:game.getBackgroundTiles()){
            shapeRenderer.setColor(backgroundTile.getColor());
            shapeRenderer.rect(i,j,120,120);
            i+=120;
            if(i >= WordFishing.SCREEN_WIDTH){
                j+=120;
                i = 0;
            }
        }
        shapeRenderer.end();
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        game.dispose();
    }

    @Override
    public void resize(int width, int height) {
        WordFishing.SCREEN_WIDTH = width;
        WordFishing.SCREEN_HEIGHT = height;
        game.calculateScale();
        if(initialResize){
            initialResize = false;
        }else {
            if(game.getScreen() instanceof SplashScreen){

            }else{
                game.setScreen(new MenuScreen(game));
            };
        }


        //game.getScreen().resize(width,height);
    }

}
