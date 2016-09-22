package com.example.guessmusic.com.example.guessmusic.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guessmusic.R;

import java.util.Random;

/**
 * Created by Administrator on 2016/9/20.
 */
public class PassStageDialog extends DialogFragment {

    private int count;
    private Button passBtn;
    private TextView moneyText;
    public LinearLayout passDialogView;

    private OnPassClick onPassClick;
    public interface OnPassClick{
        void onPass(View v , int count);
    }
    public void setOnPassClick(OnPassClick onPassClick){
        this.onPassClick = onPassClick;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pass_stage_dialog,container);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        moneyText = (TextView) v.findViewById(R.id.add_money);
        passBtn = (Button) v.findViewById(R.id.pass_btn);
        passDialogView = (LinearLayout) v.findViewById(R.id.pass_dialog);
        count = new Random().nextInt(5)*10;
        moneyText.setText(count+"");
        passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPassClick!=null){
                    onPassClick.onPass(v,count);
                }
            }
        });
        setCancelable(false);

        return v;
    }
}
