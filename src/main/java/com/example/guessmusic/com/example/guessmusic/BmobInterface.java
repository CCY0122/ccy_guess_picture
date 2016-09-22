package com.example.guessmusic.com.example.guessmusic;

import com.example.guessmusic.com.example.guessmusic.bean.RankBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public interface BmobInterface {
    void saveToBmob(RankBean bean);
    void queryFromBmob();
}
