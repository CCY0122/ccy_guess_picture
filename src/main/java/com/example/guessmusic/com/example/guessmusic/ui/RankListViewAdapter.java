package com.example.guessmusic.com.example.guessmusic.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guessmusic.R;
import com.example.guessmusic.com.example.guessmusic.bean.RankBean;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class RankListViewAdapter extends BaseAdapter {

    private Context context;
    private List<RankBean> list;
    private View v;

    public RankListViewAdapter(Context context, List<RankBean> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            v = LayoutInflater.from(context).inflate(R.layout.rank_item,null);
            holder = new Holder();
            holder.name = (TextView) v.findViewById(R.id.rank_name);
            holder.note= (TextView) v.findViewById(R.id.rank_note);
            holder.money = (TextView) v.findViewById(R.id.rank_money);
            v.setTag(holder);
        }else {
            holder = (Holder) v.getTag();
        }
            holder.name.setText(list.get(position).getName());
            holder.note.setText(list.get(position).getNote());
            holder.money.setText(list.get(position).getMoney()+"");
        return v;
    }
    class Holder{
        public TextView name;
        public TextView note;
        public TextView money;
    }
}
