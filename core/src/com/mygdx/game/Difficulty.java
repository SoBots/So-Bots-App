package com.mygdx.game;

public class Difficulty {

    public static float getTime(int score) {
        double y;
        double x = (double) score;
        if (x>1)
            //log function coefficient determines the maximum numb
            y = 5 / (2*Math.log(x));
        else
            y= 3.6;
        return (float) y;
    }

    //Takes score and computes the number of sprites to create in addition to the correct target
    public static int getDifficulty(int score) {
        double y;
        double x = (double) score;
        if (x>0)
            //log function coefficient determines the maximum numb
            y = 1 + 2*Math.log(x);
        else
            y= 1;
        return (int) y;
    }
}
