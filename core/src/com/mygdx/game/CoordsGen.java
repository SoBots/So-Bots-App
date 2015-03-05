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

        int border = 50;
        //The constraints set by sprite and screen size
        int minLeft = border;  //Tolerance on left of screen
        int maxLeft = scrWidth - (sWidth + border); //Tolerance on right of screen
        int maxHeight = border + sHeight;   //Tolerance at top of screen
        int minHeight = scrHeight - border;//Tolerance at bottom of screen
        int spaceTol = 4; //Tolerance for space between bot spawns

        //Obvious-new random
        Random rn = new Random();

        //First set of coordinates cannot clash, no point in doing this inside while loop
        float x = (float) rn.nextInt(maxLeft - minLeft + 1) + minLeft;
        float y = (float) rn.nextInt(minHeight - maxHeight + 1) + maxHeight;
        coords[0]= new Vector2(x,y);

        //iterator for the while loop, starts at one because first set already determined
        int i = 1;

        //Have to define these seperately
        float rand1;
        float rand2;
        Vector2 oldVect;
        boolean success = true;

        while (i<n) {

            //Sets a possible location for the next coordinates
            rand1 = (float) rn.nextInt(maxLeft - minLeft + 1) + minLeft;
            rand2 = (float) rn.nextInt(minHeight - maxHeight + 1) + maxHeight;
            Vector2 newVect = new Vector2(rand1, rand2);

            //Iterates through already committed coordinates to check for clashes
            for (int j = 0; j < i; j++) {

                //Coordinate vector
                oldVect = coords[j];

                //If the new coordinates fall within the area of a previous one, the numbers aren't
                //suitable. This part checks both x and y domain.
                if (((newVect.x > (oldVect.x - (sWidth + spaceTol)) && newVect.x < (oldVect.x + (sWidth + spaceTol))) &&
                     (newVect.y > (oldVect.y - (sHeight + spaceTol)) && newVect.y < (oldVect.y + (sHeight + spaceTol) )))) {
                    success = false;
                    break;
                }
                else success = true;
            }

            //If boolean hasn't been set to false, the coordinates were correct and are therefore
            //committed to the coordinate array for robot generation
            if (success) {
                coords[i] = newVect;
                i++;
            }
        }

        return coords;
    }
}
