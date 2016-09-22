package com.example.guessmusic.com.example.guessmusic.bean;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/9/21.
 */
public class RankBean extends BmobObject {
    private int id;
    private String name;
    private String note;
    private int money;

    public RankBean(String name, int money, String note) {
        this.id=0;
        this.name = name;
        this.money = money;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
