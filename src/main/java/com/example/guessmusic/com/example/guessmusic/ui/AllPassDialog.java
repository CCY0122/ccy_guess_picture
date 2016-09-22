package com.example.guessmusic.com.example.guessmusic.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.guessmusic.R;

/**
 * Created by Administrator on 2016/9/22.
 */
public class AllPassDialog extends DialogFragment {
    private EditText name;
    private EditText note;
    private Button check;
    private LinearLayout v;
    private OnCheckListener onCheckListener;
    public String mname,mnote;

    public interface OnCheckListener{
        void onCheck(String name , String note);
    }
    public void setOnCheckListener(OnCheckListener o){
        onCheckListener = o;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = (LinearLayout) inflater.inflate(R.layout.all_pass_dialog,container);
        name = (EditText) v.findViewById(R.id.all_pass_name);
        note = (EditText) v.findViewById(R.id.all_pass_note);
        check = (Button) v.findViewById(R.id.all_pass_btn);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onCheckListener != null){
                    mname = name.getText().toString();
                    mnote = note.getText().toString();
                    Log.d("ccy1","clickOnCheck");
                    onCheckListener.onCheck(mname,mnote);
                }
            }
        });
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        return v;
    }
}
