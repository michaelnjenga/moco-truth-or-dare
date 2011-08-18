package com.Moco;

import com.Moco.R;
import com.Moco.ShakeSensor.OnShakeListener;
import com.Moco.data.Question;
import com.adview.AdViewLayout;
import com.adview.AdViewTargeting;
import com.adview.AdViewTargeting.RunMode;
import com.adview.AdViewTargeting.UpdateMode;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout.LayoutParams;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Play.
 */
public class Play extends Activity{
	
    /** The Constant NORMAL. */
    private static final int NORMAL = 0;
    
    /** The Constant DIRTY. */
    private static final int DIRTY = 1;
    
    /** The Constant QUESTION. */
    private static final int QUESTION = 0;
    
    /** The Constant DARE. */
    private static final int DARE = 1;
    
    /** The Constant QUESTIONS_AND_DARES. */
    private static final int QUESTIONS_AND_DARES = 2;
    
    /** The Constant CHECKED. */
    private static final int CHECKED = 1;
    
    /** The Constant UNCHECKED. */
    private static final int UNCHECKED = 0;
    
	/** The m shake sensor. */
	private ShakeSensor mShakeSensor; 

	/** The m last num. */
	private int mLastNum;
	
	/** The m dare. */
	private int mDare;
	
	/** The m question. */
	private int mQuestion = CHECKED;
	
	/** The m dirty. */
	private int mDirty;
	
	/** The m type. */
	private int mType;
	
	/** The mb get. */
	private boolean mbGet = true;
	
	/** The m question provider. */
	private QuestionProvider mQuestionProvider;
	
	/** The m question list. */
	private List<Question> mQuestionList;
	
	/** The vibrator. */
	private Vibrator vibrator;
	
	
	 /**
 	 * Called when the activity is first created.
 	 *
 	 * @param savedInstanceState the saved instance state
 	 */
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
		
		initAd();
    }
    
    private void initAd() {
        LinearLayout layout = (LinearLayout)findViewById(R.id.adLayout);
        if (layout == null) 
            return;
        /*delete these two line when release*/
//        AdViewTargeting.setUpdateMode(UpdateMode.EVERYTIME); 
//        AdViewTargeting.setRunMode(RunMode.TEST);  
//        
        AdViewLayout adViewLayout = new AdViewLayout(this, "SDK2011101800085100npnbvlcmo7knk");
        RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
        layout.addView(adViewLayout, adViewLayoutParams);
        
        layout.invalidate();
       
    }
    
    /**
     * Inits the controls.
     */
    public void initControls() {
      //set the dirty checkbox
        OnCheckedChangeListener lsnCheckDirty = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mDirty = NORMAL;
                mbGet = true;
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
                mbGet = true;
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
                mbGet = true;
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
       findViewById(R.id.button_about).setOnClickListener(addListener);
       
        //vibrator
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);  

    }

    /**
     * Inits the question.
     */
    public void initQuestion() {
        mQuestionProvider = new QuestionProvider(this);
        
    }
    
    /**
     * Gets the questions.
     *
     * @return the questions
     */
    public void getQuestions() {
        if(!mbGet)return;
        
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
        mbGet = false;
    }
    
    /**
     * Check.
     *
     * @return true, if successful
     */
    public boolean check() {
        if(mDare == UNCHECKED && mQuestion == UNCHECKED) {

            Toast.makeText(getApplicationContext(), "真心话还是大冒险？至少�?�?��吧！",
                           Toast.LENGTH_SHORT).show();
            
            return false;
        }
        
        return true;
    }
    
    /**
     * Inits the sensor.
     */
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
    
    /**
     * Display.
     */
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
            text = "[大冒险]" + text;
        }
        
        TextView tv = (TextView) findViewById(R.id.question);
        
        tv.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
        tv.setText(text);
        tv.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
            
        long[] pattern = {800, 50, 400, 30, 800, 50, 400, 30};
        vibrator.vibrate(pattern, -1);
    }
    
    
    /* (non-Javadoc)
     * @see android.app.Activity#onPause()
     */
    @Override
    public void onPause() {
        super.onPause();
        if(mShakeSensor != null)mShakeSensor.pause();
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mShakeSensor != null)mShakeSensor.pause();
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    public void onResume() {
        super.onResume();
        if(mShakeSensor != null)mShakeSensor.resume();
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
     */
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
