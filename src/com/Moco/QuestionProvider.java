package com.Moco;

import com.Moco.data.Question;
import com.Moco.data.QuestionDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class QuestionProvider {
	
    private static final int QUESTIONS_AND_DARES = 2;
    
    private Context mContext;
	
	private int mType;
    
	private int mDirty;
	
	private List<Question> mQuestionList;
	
	QuestionDao questionDao = null;
	
	public QuestionProvider(Context context){
	    this.mContext = context;

	    questionDao = new QuestionDao(mContext);
        //getQuestions();
	    
	}
	
	public int getNumber(){
		int number;
		number = (int)(Math.random()*mQuestionList.size());
		
		return number;
	}

	public List<Question> getQuestions(int type, int dirty){
	    this.mType = type;
        this.mDirty = dirty;
	    
	    if(mType == QUESTIONS_AND_DARES) {
	        mQuestionList = questionDao.getQuestions(mDirty);
        } else {
            mQuestionList = questionDao.getQuestions(mType, mDirty);
        }
	    
	    return mQuestionList;
	}
	
    


}
