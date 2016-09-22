package com.example.guessmusic.com.example.guessmusic.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.example.guessmusic.R;

/**
 * Created by Administrator on 2016/9/21.
 */
public class ShowAnswerDialog extends DialogFragment {

    private Button no;
    private Button yes;
    private View v;
    private OnYesNoClick onYesNoClick;
    public interface OnYesNoClick{
        void clickYes();
        void clickNo();
    }
    public void setOnYesNoClick(OnYesNoClick o){
        onYesNoClick = o;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.show_answer_dialog,container);
        no = (Button) v.findViewById(R.id.no_show_answer);
        yes = (Button)v.findViewById(R.id.yes_show_answer);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onYesNoClick!=null){
                    onYesNoClick.clickNo();
                }
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onYesNoClick!=null){
                    onYesNoClick.clickYes();
                }
            }
        });
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return v;
    }
}
