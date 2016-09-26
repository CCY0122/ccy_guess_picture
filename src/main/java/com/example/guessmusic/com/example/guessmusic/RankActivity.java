package com.example.guessmusic.com.example.guessmusic;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.guessmusic.R;
import com.example.guessmusic.com.example.guessmusic.bean.RankBean;
import com.example.guessmusic.com.example.guessmusic.ui.RankListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/9/22.
 */
public class RankActivity extends AppCompatActivity {

    private ListView listView;
    private List<RankBean> mList;
    private RankListViewAdapter rankListViewAdapter;
    private TextView name,note,money;
    private RankBean rankBean;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rank_layout);
        Log.d("ccy1","in");
        listView = (ListView) findViewById(R.id.rank_list_view);
        name = (TextView) findViewById(R.id.rank_name);
        note = (TextView) findViewById(R.id.rank_note);
        money = (TextView) findViewById(R.id.rank_money);
        updateList();
//        text();

    }

    private void text() {
        mList = new ArrayList<>();
        RankBean r1 = new RankBean("123",123,"123");
        RankBean r2 = new RankBean("asd",234,"asd");
        for (int i = 0; i < 8; i++) {
            mList.add(r1);
            mList.add(r2);
        }
        rankListViewAdapter = new RankListViewAdapter(this,mList);
        listView.setAdapter(rankListViewAdapter);
    }

    private void updateList() {
        mList = new ArrayList<>();
        BmobQuery<RankBean> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("id",0);
        bmobQuery.order("-money");
        bmobQuery.setLimit(30);
        bmobQuery.findObjects(new FindListener<RankBean>() {
            @Override
            public void done(List<RankBean> list, BmobException e) {
                if(e == null){
                    for (RankBean bean : list){
                        mList.add(bean);
                    }
                    Log.d("ccy1","查询成功，list size="+mList.size());
//                    mList.add(new RankBean("测试",0,"测试"));
//                    mList.add(new RankBean("测试",1,"测试"));
                    rankListViewAdapter = new RankListViewAdapter(getApplicationContext(),mList);
                    listView.setAdapter(rankListViewAdapter);
//                    rankListViewAdapter.notifyDataSetChanged();
                }else {
                    Log.d("ccy1","查询失败");
                }
            }
        });

        Log.d("ccy1","listView size="+mList.size()+"");
    }
}


