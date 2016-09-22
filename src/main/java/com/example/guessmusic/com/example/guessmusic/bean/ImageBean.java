package com.example.guessmusic.com.example.guessmusic.bean;

/**
 * Created by Administrator on 2016/9/20.
 */
public class ImageBean {
    private String imgName;
    private int imgLength;
    private String imgResName;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public char[] getNameCharacter(){
        return imgName.toCharArray();
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
        this.imgLength = imgName.length();
    }

    public int getImgLength() {
        return imgLength;
    }


    public String getImgResName() {
        return imgResName;
    }

    public void setImgResName(String imgResName) {
        this.imgResName = imgResName;
    }
}
