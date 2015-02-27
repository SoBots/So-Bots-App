package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

//-- Generates a set of co-ordinates --//

public class CoordsGen {

    //Generates n sets of x/y co-ordinates.
    public static Vector2[] genCoords(int n, int sWidth, int sHeight){
        Vector2[] coords = new Vector2[n];

        int maxX = Gdx.graphics.getWidth();

          /*public void getRobotLoc(){
        float minX = robotSprite.getWidth();
        float maxX = Gdx.graphics.getWidth()-robotSprite.getWidth();
        float minY = robotSprite.getHeight();
        float maxY = Gdx.graphics.getHeight()-robotSprite.getHeight();
        float x=random.nextFloat() * (maxX-minX) + minX;
        float y=random.nextFloat() * (maxY - minY) + minY;
        robotPos = new Vector2(x, y);
    }*/
        //EX case: Screen height:500, width:200, Sprite height:30, width:30
        //In order to do this, take the width and height of screen minus a tolerance for each, then
        //generate the first set of coordinates. Then attempt to create additional coordinates
        //each time checking that it does not fall into +35(sprite width/height + tolerance), or -5
        //(for the tolerance on the other side of the sprite. This means that not two sprites will
        //appear within 5 pixels of each other. If a set of coordinates violates this, a new set will
        //be generated.
        return null;
    }
}
