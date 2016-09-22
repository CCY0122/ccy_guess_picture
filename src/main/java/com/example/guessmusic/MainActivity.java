package com.example.guessmusic;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guessmusic.com.example.guessmusic.bean.ImageBean;
import com.example.guessmusic.com.example.guessmusic.bean.ImageDate;
import com.example.guessmusic.com.example.guessmusic.bean.TextBean;
import com.example.guessmusic.com.example.guessmusic.ui.PassStageDialog;
import com.example.guessmusic.com.example.guessmusic.ui.ShowAnswerDialog;
import com.example.guessmusic.com.example.guessmusic.ui.TextSelectGridView;
import com.example.guessmusic.com.example.guessmusic.ui.WarningDialog;
import com.example.guessmusic.com.example.guessmusic.util.ImageDecode;
import com.example.guessmusic.com.example.guessmusic.util.RandomText;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,TextSelectGridView.onItemCheckedListener {

    private Button btn_music_start; //中间音乐播放
    private Button btn_top_back; //左上返回
    private LinearLayout btn_top_coin; //右上钱币
    private ImageView anima_disc; //disc旋转图
    private ImageView anima_disc_2;//disc杆子
    private LinearLayout hasSelectContainer;
    private ImageView centerImg;
    private TextView coin_text;
    private TextView title_text;
    private Button show_answer;

    private ObjectAnimator anim;
    private ObjectAnimator anim2;
    private ObjectAnimator anim3;

    private List<TextBean> mList;
    private TextSelectGridView mGridView;
    private List<TextBean> mHasSelectList;

    private int currentStage;
    private ImageBean currentStageBean;
    private int currentMoney;

    private PassStageDialog passStageDialog;
    private boolean colorFlag;
    private ShowAnswerDialog showAnswerDialog;
    private WarningDialog warningDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        initView();
        initDate();

//        initAnim();
        initCurrentLevelDate();
    }

    private void initDate() {
        SharedPreferences sp = getSharedPreferences("myDate",0);
        currentMoney=sp.getInt("money",200);
        coin_text.setText(currentMoney+"");
        currentStage = sp.getInt("stage",0);
    }

    private void initAnim() {
        anim = ObjectAnimator.ofFloat(anima_disc,"rotation",0f,1080f);
        anim.setDuration(8000);
        anim.setInterpolator(new LinearInterpolator());
        anima_disc_2.setPivotX(135f);
        anima_disc_2.setPivotY(0f); //杆子旋转y中心

        anim2 = ObjectAnimator.ofFloat(anima_disc_2, "rotation", 0, 15f);
        anim2.setDuration(300);

        anim3 = ObjectAnimator.ofFloat(anima_disc_2,"rotation",15f,0f);
        anim3.setDuration(300);

    }

    private void initView() {
//        btn_music_start = (Button) findViewById(R.id.btn_music_start);
        btn_top_back = (Button) findViewById(R.id.btn_back);
        btn_top_coin = (LinearLayout) findViewById(R.id.game_coin);
//        anima_disc = (ImageView) findViewById(R.id.anima_disc);
//        anima_disc_2 = (ImageView) findViewById(R.id.ganme_pin);
        mGridView = (TextSelectGridView) findViewById(R.id.text_select_grid_view);
        hasSelectContainer = (LinearLayout) findViewById(R.id.select_container);
        centerImg = (ImageView) findViewById(R.id.center_image);
        passStageDialog = new PassStageDialog();
        coin_text = (TextView) findViewById(R.id.coin_text_view);
        title_text = (TextView) findViewById(R.id.my_title);
        show_answer = (Button) findViewById(R.id.show_answer);
        showAnswerDialog = new ShowAnswerDialog();
        warningDialog = new WarningDialog();


        mGridView.setOnItemCheckedListener(this);
//        btn_music_start.setOnClickListener(this);
        btn_top_coin.setOnClickListener(this);
        btn_top_back.setOnClickListener(this);
        show_answer.setOnClickListener(this);
        passStageDialog.setOnPassClick(new PassStageDialog.OnPassClick() {
            @Override
            public void onPass(View v, int count) {
                currentMoney+=count;
                coin_text.setText(currentMoney+"");
                passStageDialog.dismiss();
                mHasSelectList.clear();
                currentStage++;

                btn_top_back.setText(currentStage+"");

                initCurrentLevelDate();
            }
        });
        showAnswerDialog.setOnYesNoClick(new ShowAnswerDialog.OnYesNoClick() {
            @Override
            public void clickYes() {
                currentMoney-=50;
                coin_text.setText(currentMoney+"");
                title_text.setText(currentStageBean.getImgName());
                showAnswerDialog.dismiss();
            }

            @Override
            public void clickNo() {
                showAnswerDialog.dismiss();
            }
        });
        warningDialog.setOnYesNoClick(new WarningDialog.OnYesNoClick() {
            @Override
            public void clickYes() {
                getSharedPreferences("myDate",0).edit().clear().commit();
                initDate();
                initCurrentLevelDate();
                btn_top_back.setText(0+"");
                warningDialog.dismiss();
            }

            @Override
            public void clickNo() {
                warningDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.btn_music_start:
//                    startDiscAnimation();
//
//                break;
            case R.id.btn_back:
                warningDialog.show(getFragmentManager(),"3");
                break;
            case R.id.game_coin:

                break;
            case R.id.show_answer:
                if(currentMoney<50){
                    Toast.makeText(this,"金币不够",Toast.LENGTH_SHORT).show();
                    break;
                }
                showAnswerDialog.show(getFragmentManager(),"2");
                break;
        }
    }

    private void startDiscAnimation() {
        if (anim.isRunning()) {
            anim.cancel();
        }
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                anim3.start();
            }


            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                    anim2.start();
            }
        });
        anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor spe = getSharedPreferences("myDate",0).edit();
        spe.putInt("money", currentMoney);
        spe.putInt("stage",currentStage);
        spe.commit();
//        anima_disc.clearAnimation();
//        anima_disc_2.clearAnimation();
    }
    public void initCurrentLevelDate(){
        mList = getDate(currentStage);//初始化选择的文字
        mHasSelectList = getHasSelectList();
        updataHasSelectNum(mHasSelectList);
        title_text.setText(currentStageBean.getTitle());
        mGridView.updataDate(mList);
    }

    private boolean checkAnswer(final List<TextBean> mHasSelectList) {
        StringBuffer sb = new StringBuffer();
        Log.d("ccy","1");
        for (int i = 0; i < mHasSelectList.size(); i++) {
            if (mHasSelectList.get(i).text.length()== 0 ){return false;}
        }
        Log.d("ccy","2");
        for (int i = 0; i < mHasSelectList.size(); i++){
            sb.append(mHasSelectList.get(i).text);
        }
        Log.d("ccy",sb.toString());
        if (currentStageBean.getImgName().equals(sb.toString())) {
            Log.d("ccy","3");
            return true;
        }else {
           startWrongAnswerAnim();
        }
        return false;
    }

    private void startWrongAnswerAnim() {
        final Timer timer =new Timer("wrong_answer");
        final TimerTask timerTask = new TimerTask() {
            int num = 0;
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(num++ > 5){timer.cancel();}
                            for (int z = 0; z < mHasSelectList.size(); z++) {
                                if(colorFlag) {
                                    mHasSelectList.get(z).button.setTextColor(Color.RED);
                                }else {
                                    mHasSelectList.get(z).button.setTextColor(Color.WHITE);
                                }
                        }
                        colorFlag = !colorFlag;
                    }
                });
            }
        };
        timer.schedule(timerTask,1,150);
    }

    private void updataHasSelectNum(List<TextBean> mHasSelectList) {
        hasSelectContainer.removeAllViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150,150);
        for (int i = 0; i <mHasSelectList.size() ; i++) {
            hasSelectContainer.addView(mHasSelectList.get(i).button,params);
        }
    }

    private List<TextBean> getHasSelectList() {
        List<TextBean> list = new ArrayList<>();
        for (int i = 0; i <currentStageBean.getImgLength() ; i++) {
            final TextBean bean = new TextBean();
            View v = LayoutInflater.from(this).inflate(R.layout.text_btn_layout,null);
            bean.button = (Button) v.findViewById(R.id.text_btn);
            bean.index = i;
            bean.button.setTextColor(Color.BLACK);
            bean.button.setText("");
            bean.isVisible = false;
            bean.button.setBackgroundResource(R.drawable.text_btn);
            final int finalI = i;
            bean.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setVisible(mList.get(bean.index),View.VISIBLE);
                    bean.button.setText("");
                    bean.text="";
                    bean.isVisible = false;
                }
            });
            list.add(bean);
        }
        return list;
    }

    public List<TextBean> getDate(int mCurrentStage) {
        currentStageBean = new ImageBean();
        currentStageBean.setImgResName(ImageDate.imageDate[mCurrentStage][0]);
        currentStageBean.setImgName(ImageDate.imageDate[mCurrentStage][1]);
        currentStageBean.setTitle(ImageDate.imageDate[mCurrentStage][2]);
        int resId = getImgResource(currentStageBean.getImgResName());
        Log.d("ccy1",currentStageBean.getImgResName()+"---"+resId+"");
        Bitmap bm = ImageDecode.decodeBitmap(getResources(),resId,dp2px(180),dp2px(180));
        centerImg.setImageBitmap(bm);
        String[] text = generateText();
        mList = new ArrayList<>();
        TextBean bean;
        for (int i = 0; i <24 ; i++) {
            bean = new TextBean();
            bean.text =text[i];
            mList.add(bean);
        }
        return mList;
    }

    private String[] generateText() {
        String[] text = new String[24];

        for (int i= 0; i < currentStageBean.getImgLength(); i++) {
            text[i] = currentStageBean.getNameCharacter()[i]+"";
        }
        for(int i = currentStageBean.getImgLength();i<24;i++){
            text[i] = RandomText.getRandomChar()+"";
        }

        for (int i = 0; i <currentStageBean.getImgLength() ; i++) {
            Random random = new Random();
            int a=random.nextInt(24);
            String b = text[i];
            text[i] = text[a];
            text[a] = b;

        }
        return text;
    }
    public void setVisible(TextBean bean , int visible){
        bean.button.setVisibility(visible);
        bean.isVisible = (visible==View.VISIBLE? true:false);
    }
    private void setSelectText(TextBean bean){
        for (int i = 0; i < mHasSelectList.size(); i++) {
            if(mHasSelectList.get(i).text.length() == 0){
                mHasSelectList.get(i).button.setText(bean.text);
                mHasSelectList.get(i).isVisible = true;
                mHasSelectList.get(i).text = bean.text;
                //记录索引
                mHasSelectList.get(i).index = bean.index;
                Log.d("ccy","out");
                setVisible(bean , View.INVISIBLE);
                break;
            }
        }
    }

    @Override
    public void onChecked(TextBean bean) {
        setSelectText(bean);
        if(checkAnswer(mHasSelectList)){
            Log.d("ccy","in");
            if(currentStage>=ImageDate.imageDate.length-1){
                //通关逻辑
                Toast.makeText(this,"通关",Toast.LENGTH_SHORT).show();
                return;
            }
            passStageDialog.show(getFragmentManager(),"1");
//            passDialogAnim(); //无效。。。why
        }
    }

    private void passDialogAnim() {
        LinearLayout v = passStageDialog.passDialogView;
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(v,"scaleX",0f,1f).setDuration(700);
        anim1.setInterpolator(new AnticipateOvershootInterpolator());
        anim1.start();
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(v,"scaleY",0f,1f).setDuration(700);
        anim2.setInterpolator(new AnticipateOvershootInterpolator());
        anim2.start();
    }

    public int  getImgResource(String imageName) {
        int resId = getResources().getIdentifier(imageName, "drawable", this.getPackageName());
        return resId;
//        R.drawable drawable = new R.drawable();
//        int resId = 0;
//        try{
//            Field field = R.drawable.class.getField(imageName);
//            resId = (Integer)field.get(drawable);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return resId;
    }

    protected int dp2px(int dp){
        return (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics());
    }
}
