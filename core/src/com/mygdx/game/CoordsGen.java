package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

//-- Generates a set of co-ordinates --//

public class CoordsGen {



    //Generates n sets of x/y co-ordinates.
    public static Vector2[] genCoords(int n,int scrWidth, int scrHeight, int sWidth, int sHeight){

        //Vector array which will be returned containing the locations to spawn robots
        Vector2[] coords = new Vector2[n];

        //The constraints set by sprite and screen size
        int minLeft = 50;  //Tolerance on left of screen
        int maxRight = scrWidth-sWidth-50; //Tolerance on right of screen
        int minTop = 50;   //Tolerance at top of screen
        int maxBottom = scrHeight-sHeight-50;//Tolerance at bottom
        int spaceTol = 50; //Tolerance for space between bot spawns

        //Obvious-new random
        Random rn = new Random();

        //Creates vectors to hold planned and already committed locations
        Vector2 loc = new Vector2(); //Vector2 of the coordinates generated (not committed)
        Vector2 locCheck = new Vector2(); //Placeholder vector for checking coordinate suitability
                                          //with already comitted coordinates.

        boolean success = true; //Determines whether to run loop again to find new coordinates

        //First set of coordinates cannot clash, no point in doing this inside while loop
        coords[0].add((float)rn.nextInt((maxRight - minLeft) + 1) + minLeft, (float)rn.nextInt((maxBottom - minTop) + 1) + minTop);

        //iterator for the while loop, starts at one because first set already determined
        int i = 1;
        float rand1;
        float rand2;

        while (i<n) {

            //Sets a possible location for the next coordinates
            rand1 = (float)rn.nextInt((maxRight - minLeft) + 1) + minLeft;
            rand2 = (float)rn.nextInt((maxBottom - minTop) + 1) + minTop;
            loc = loc.add(rand1, rand2);

            //Iterates through already committed coordinates to check for clashes
            for (int j = 0; j < i; j++) {
                locCheck = coords[j];
                //If the new coordinates fall within the area of a previous one, the numbers aren't
                //suitable. This part checks both x and y domain.
                if ((loc.x>(locCheck.x-spaceTol) && loc.x<(locCheck.x+sWidth+spaceTol))||(loc.y>(locCheck.y-spaceTol) && loc.y<(locCheck.y+sHeight+spaceTol))) {
                    success = false;
                }
                else success=true;

            }

            //If boolean hasn't been set to false, the coordinates were correct and are therefore
            //committed to the coordinate array for robot generation
            if (success) {
                coords[i].add(loc);
                i++;
            }
            //However if !successful, the coordinates are removed
            else {
                loc.sub((float)rn.nextInt((maxRight - minLeft) + 1) + minLeft, (float)rn.nextInt((maxBottom - minTop) + 1) + minTop);
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
