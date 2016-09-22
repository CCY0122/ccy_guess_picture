package com.example.guessmusic.com.example.guessmusic.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.guessmusic.R;
import com.example.guessmusic.com.example.guessmusic.bean.TextBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 */
public class TextSelectGridView extends GridView {

    private List<TextBean> allText = new ArrayList<>();
    private Context mContext;
    private MyAdapter myAdapter;
    private onItemCheckedListener itemCheckedListener;

    public interface onItemCheckedListener{
        void onChecked(TextBean bean);
    }
    public void setOnItemCheckedListener(onItemCheckedListener i){
        itemCheckedListener = i;
    }

    public TextSelectGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        myAdapter = new MyAdapter();
        setAdapter(myAdapter);
    }
    public void updataDate(List<TextBean> list){
        allText = list;
        //初始化数据
        setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();//是否和上一句效果一样
    }

    class  MyAdapter extends BaseAdapter{

       public MyAdapter() {
           super();
       }

       @Override
       public int getCount() {
           return allText.size();
       }

       @Override
       public Object getItem(int position) {
           return allText.get(position);
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           final TextBean textBean;
           if(convertView == null){
               convertView = LayoutInflater.from(mContext).inflate(R.layout.text_btn_layout,null);
               textBean = allText.get(position);
               if(textBean.button == null) {
                   textBean.button = (Button) convertView.findViewById(R.id.text_btn);
                   textBean.index = position;
                   textBean.button.setOnClickListener(new OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           if (itemCheckedListener != null) {
                               itemCheckedListener.onChecked(textBean);
                           }
                       }
                   });
               }
               loadAnim(textBean.button,textBean.index);
               convertView.setTag(textBean);
           }else{
               textBean = (TextBean) convertView.getTag();
           }
           textBean.button.setText(textBean.text);

           Log.d("ccy",""+position+"==="+textBean.index);


           return convertView;
       }

        private void loadAnim(Button button,int pos) {
            ObjectAnimator o = ObjectAnimator.ofFloat(button,"scaleX",0f,1f);
            o.setDuration(300);
            o.setStartDelay(pos*50);
            o.start();
            ObjectAnimator b = ObjectAnimator.ofFloat(button,"scaleY",0f,1f);
            b.setDuration(300);
            b.setStartDelay(pos*50);
            b.start();
        }
    }

}
