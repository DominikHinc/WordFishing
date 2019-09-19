package pl.dominikhinc.wordfishing.service;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;



public class BackgroundTile {
    private Color color;
    private static float min = 0.5f;
    private static float max = 0.65f;
    public BackgroundTile(){
        color = new Color(MathUtils.random(min,0.65f),MathUtils.random(min,max),MathUtils.random(min,max),MathUtils.random(min,max));
    }
    public void shuffleColor(){
        color = new Color(MathUtils.random(min,max),MathUtils.random(min,max),MathUtils.random(min,max),MathUtils.random(min,max));
    }

    public Color getColor(){
        return color;
    }

}
