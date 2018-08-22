package fr.monolog.game.utils;

import java.util.Random;

public class Helpers {

    static public float randomFloat(float min, float max){
        Random r = new Random();
        return min + r.nextFloat() * (max-min);
    }

    static public int randomInt(int min, int max){
        Random r= new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
