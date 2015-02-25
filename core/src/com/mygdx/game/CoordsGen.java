package com.mygdx.game;

/**
 * Created by samrowlands on 25/02/15.
 *
 * Generates
 */
public class CoordsGen {

    //Generates n sets of x/y co-ordinates.
    public static float[][] genCoords(int n, int sWidth, int sHeight){

        //EX case: Screen height:500, width:200, Sprite height:30, width:30
        //In order to do this, take the width and height of screen minus a tolerance for each, then
        //generate the first set of coordinates. Then attempt to create additional coordinates
        //each time checking that it does not fall into +35(sprite width/height + tolerance), or -5
        // (for the tolerance on the other side of the sprite. This means that not two sprites will
        //appear within 5 pixels of each other. If a set of coordinates violates this, a new set will
        //be generated.
        return null;
    }
}
