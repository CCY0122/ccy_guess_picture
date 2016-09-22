package com.example.guessmusic.com.example.guessmusic;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.guessmusic.com.example.guessmusic.bean.RankBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/9/22.
 */
public class BmobUtil implements BmobInterface {

    public List<RankBean> mList;
    @Override
    public void saveToBmob(RankBean bean) {
        bean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e!=null){
                    Log.d("ccy1","上传失败");
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void  queryFromBmob() {

    }

    public List<RankBean> getList(){

        return mList;
    }
}
