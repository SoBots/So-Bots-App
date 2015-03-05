package com.mygdx.game;

public class Difficulty {

    //Takes score and computes the number of sprites to create in addition to the correct target
    public static int getDifficulty(int score) {
        double y;
        double x = (double) score;
        if (x>0)
            //log function coefficient determines the maximum numb
            y = 2*Math.log(x);
        else
            y= 1;
        y = Math.ceil(y);
        return (int) y;
    }
}
