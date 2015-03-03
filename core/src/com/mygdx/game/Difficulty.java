package com.mygdx.game;
import java.math.*;
/**
 * Created by kevinjohnston on 03/03/15.
 */
public class Difficulty {

    public static double getDifficulty(int score) {
        //Some code which calculates the number of robots to generate
        double y;
        double x = (double) score;
        if (x>0)
            y = 1 + 2*Math.log(x);
        else
            y= 1;

        y = Math.floor(y);
        //y = (int) y;
        return y;
    }

    public static int getLevel(int score) {
        int level;
        if (score<2)
            level=1;
        else if (score<4)
            level=2;
        else if (score<6)
            level=3;
        else if (score<8)
            level=4;
        else if (score<10)
            level=5;
        else if (score<12)
            level=6;
        else if (score<14)
            level=7;
        else if (score<16)
            level=8;
        else
            level=9;
        return level;
    }
}
