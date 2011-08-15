package com.Moco;

import com.Moco.data.Question;
import com.Moco.data.QuestionDao;

import android.content.Context;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class QuestionProvider.
 */
public class QuestionProvider {
	
    /** The Constant QUESTIONS_AND_DARES. */
    private static final int QUESTIONS_AND_DARES = 2;
    
    /** The m context. */
    private Context mContext;
	
	/** The m type. */
	private int mType;
    
	/** The m dirty. */
	private int mDirty;
	
	/** The m question list. */
	private List<Question> mQuestionList;
	
	/** The question dao. */
	QuestionDao questionDao;
	
	/**
	 * Instantiates a new question provider.
	 *
	 * @param context the context
	 */
	public QuestionProvider(Context context){
	    this.mContext = context;
        questionDao = new QuestionDao(mContext);
	    
	}
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber(){
		int number;
		number = (int)(Math.random()*mQuestionList.size());
		
		return number;
	}

	/**
	 * Gets the questions.
	 *
	 * @param type the type
	 * @param dirty the dirty
	 * @return the questions
	 */
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
