package com.example.guessmusic.com.example.guessmusic.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by Administrator on 2016/9/20.
 */
public class RandomText {
    public static char getRandomChar(){
        String str = "";
        int highPos;
        int lowPos;
        Random random = new Random();
        highPos = (176 +Math.abs(random.nextInt(36)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b =new byte[2];
        b[0] = (Integer.valueOf(highPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try{
            str = new String(b,"GBK");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return str.charAt(0);
    }
}
