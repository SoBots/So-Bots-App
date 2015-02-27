package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

//-- Generates a set of co-ordinates --//

public class CoordsGen {



    //Generates n sets of x/y co-ordinates.
    public static Vector2[] genCoords(int n,int scrWidth, int scrHeight, int sWidth, int sHeight){
        Vector2[] coords = new Vector2[n];

        int minLeft = 50;  //Tolerance on left of screen
        int maxRight = scrWidth-sWidth-50; //Tolerance on right of screen
        int minTop = 50;   //Tolerance at top of screen
        int maxBottom = scrHeight-sHeight-50;//Tolerance at bottom
        int spaceTol = 50; //Tolerance for space between bot spawns

        Random rn = new Random();

        int i = 1;

        Vector2 loc = new Vector2(); //Vector2 of the coordinates generated
        Vector2 locCheck = new Vector2(); //Placeholder vector for checking coordinate suitability

        boolean success = true; //Determines whether to run loop again to find new coordinates

        coords[0].add((float)rn.nextInt((maxRight - minLeft) + 1) + minLeft, (float)rn.nextInt((maxBottom - minTop) + 1) + minTop);

        while (i<n) {

            //adds new
            loc = loc.add((float)rn.nextInt((maxRight - minLeft) + 1) + minLeft, (float)rn.nextInt((maxBottom - minTop) + 1) + minTop);


            for (int j = 0; j < i; j++) {
                locCheck = coords[j];
                if (locCheck.x>(loc.x-spaceTol) && locCheck.x<(loc.x+sWidth+spaceTol)) {
                    success = false;
                }
                if (locCheck.y>(loc.y-spaceTol) && locCheck.y<(loc.y+sHeight+spaceTol)) {
                    success = false;
                }

            }

            if (success) {
                coords[i].add(loc);
                i++;
            }

        }



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
        return coords;
    }
}
