package com.Moco;

import com.Moco.R;
import com.Moco.ShakeSensor.OnShakeListener;
import com.Moco.data.Question;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout.LayoutParams;

import java.util.List;

public class Play extends Activity{
	
    private static final int NORMAL = 0;
    
    private static final int DIRTY = 1;
    
    private static final int QUESTION = 0;
    
    private static final int DARE = 1;
    
    private static final int QUESTIONS_AND_DARES = 2;
    
    private static final int CHECKED = 1;
    
    private static final int UNCHECKED = 0;
    
	private ShakeSensor mShakeSensor; 

	private int mLastNum;
	
	private int mDare;
	
	private int mQuestion = CHECKED;
	
	private int mDirty;
	
	private int mType;
	
	private QuestionProvider mQuestionProvider;
	
	private List<Question> mQuestionList;
	
	private Vibrator vibrator;
	
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        //set title
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.play);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);

        initControls();
		initSensor();
		initQuestion();
    }
    
    public void initControls() {
      //set the dirty checkbox
        OnCheckedChangeListener lsnCheckDirty = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                mDirty = NORMAL;
                if(isChecked){
                    mDirty = DIRTY;
                }
            }
        };
        ((CompoundButton) findViewById(R.id.checkbox_dirty)).setOnCheckedChangeListener(lsnCheckDirty);
        
        //set the dare checkbox
        OnCheckedChangeListener lsnCheckDare = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDare = UNCHECKED;
                if(isChecked){
                    mDare = CHECKED;
                }
            }
        };
        ((CompoundButton) findViewById(R.id.checkbox_dare)).setOnCheckedChangeListener(lsnCheckDare);
        
        //set the dare checkbox
        OnCheckedChangeListener lsnCheckQuestion = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mQuestion = UNCHECKED;
                if(isChecked){
                    mQuestion = CHECKED;
                }
            }
        };
        ((CompoundButton) findViewById(R.id.checkbox_question)).setOnCheckedChangeListener(lsnCheckQuestion);
        
        //set the dare checkbox
        OnClickListener test = new View.OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                display();
            }
        };
        ((LinearLayout) findViewById(R.id.question_frame)).setOnClickListener(test);
       
        //set about button
        OnClickListener addListener = new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                //test about
                Intent intent = new Intent();
                intent.setClass(Play.this, About.class);
                startActivity(intent);
            }
       };
       findViewById(R.id.add_item).setOnClickListener(addListener);
       
        //vibrator
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);  

    }

    public void initQuestion() {
        
        mQuestionProvider = new QuestionProvider(this);
        
    }
    
    public void getQuestions() {
        if (mDare == CHECKED && mQuestion == CHECKED) {
            mType = QUESTIONS_AND_DARES;
        } else {
            if (mDare == CHECKED) { 
                mType = DARE;
            } else {
                mType = QUESTION;
            }
        }
        mQuestionList = mQuestionProvider.getQuestions( mType, mDirty);
    }
    
    public boolean check() {
        if(mDare == UNCHECKED && mQuestion == UNCHECKED) {

            Toast.makeText(getApplicationContext(), "真心话还是大冒险，你至少选一个吧？！",
                           Toast.LENGTH_SHORT).show();
            
            return false;
        }
        
        return true;
    }
    
    public void initSensor(){
    	
    	OnShakeListener lsn = new OnShakeListener(){
			@Override
			public void onShake() {
			    display();
			}
		};
    	
		mShakeSensor = new ShakeSensor(this);
		mShakeSensor.setOnShakeListener(lsn);
    }
    
    public void display() {
        
        if(!check()) return;
        getQuestions();
        
        int number = 1;
        do{
            number = mQuestionProvider.getNumber();
        }
        while(mLastNum == number);
        mLastNum = number;
        
        String text;
        text = mQuestionList.get(mLastNum).getItemContent();
        if(mQuestionList.get(mLastNum).getItemType() == 1) {
            text = "【大冒险】" + text;
        }
        
        TextView tv = (TextView) findViewById(R.id.question);
        
        tv.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        tv.setText(text);
        tv.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            
        long[] pattern = {800, 50, 400, 30, 800, 50, 400, 30};
        vibrator.vibrate(pattern, -1);
    }
    
    
    @Override
    public void onPause() {
        super.onPause();
        mShakeSensor.pause();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        mShakeSensor.pause();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        mShakeSensor.resume();
    }
    
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        
        int topMargin = 0;
        int bottomMargin = 0;
        
        if (this.getResources().getConfiguration().orientation == 
                    Configuration.ORIENTATION_LANDSCAPE) {

                
        } else if (this.getResources().getConfiguration().orientation == 
                    Configuration.ORIENTATION_PORTRAIT) {   
            topMargin = 30;
            bottomMargin = 30;
        }
        
        
        LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                                               LinearLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = topMargin;
        ((LinearLayout) findViewById(R.id.question_frame)).setLayoutParams(params);
        
        
        params = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                  LinearLayout.LayoutParams.MATCH_PARENT);
        params.bottomMargin = bottomMargin;
        ((LinearLayout) findViewById(R.id.checkboxes_frame)).setLayoutParams(params);
    }
}
