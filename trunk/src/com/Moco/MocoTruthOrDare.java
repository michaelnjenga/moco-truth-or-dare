package com.Moco;

import com.Moco.data.DAOSQLiteHelper;
import com.mobclick.android.MobclickAgent;
import com.mobclick.android.ReportPolicy;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

// TODO: Auto-generated Javadoc
/**
 * The Class MocoTruthOrDare.
 */
public class MocoTruthOrDare extends Activity {

	/** The WEIB o_ url. */
	private String WEIBO_URL = "http://t.sina.com.cn/mocovenwitch";
	
	/** The SPLAS h_ time. */
	private final long SPLASH_TIME = 3000L;
	
	/** The m daosq lite helper. */
	public static DAOSQLiteHelper mDAOSQLiteHelper;
	
    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobclickAgent.setDefaultReportPolicy(this, ReportPolicy.REALTIME);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        initControls();
        
        copyDatabase();
        
        //start timer
        Timer t = new Timer();
        t.schedule(new Task(), SPLASH_TIME);
    }
    
    //initialize the controls
    /**
     * Inits the controls.
     */
    public void initControls(){ 	
		//set listener to Weibo button
    	OnClickListener lsnWeibo = new ImageView.OnClickListener(){
		     @Override
		     public void onClick(View v) {
		    	 gotoWeibo();
		     }
		};
		findViewById(R.id.imgLogo).setOnClickListener(lsnWeibo);

    }
    
    //goto play
    /**
     * Play.
     */
    public void play(){
    	Intent it = new Intent(this, Play.class);
        startActivity(it);
        
    }
    
    //goto Weibo
    /**
     * Goto weibo.
     */
    public void gotoWeibo(){
    	
    	Uri uri = Uri.parse(WEIBO_URL);
    	Intent i = new Intent(Intent.ACTION_VIEW);
    	i.setData(uri);
    	startActivity(i);
    	
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    
    /* (non-Javadoc)
     * @see android.app.Activity#onPause()
     */
    @Override
    public void onPause() { 
        super.onPause(); 
        MobclickAgent.onPause(this); 
    }

    /**
     * The Class Task.
     */
    class Task extends TimerTask {
        
        /* (non-Javadoc)
         * @see java.util.TimerTask#run()
         */
        @Override
        public void run() {
            Intent intent = new Intent();
            intent.setClass(MocoTruthOrDare.this, Play.class);
            startActivity(intent);
            finish();
        }
    }
    
    public void copyDatabase() {

      final String file_path = "//data//data//com.Moco//databases//";
      final String file_name = "mocotod.db3";

      // if file is not exist, copy it
       File f = new File(file_path, file_name);
       if (!f.exists()) {
          f.mkdir();
          Log.d("T", "copy database from assets to package");
  
          try {
              getDAOSQLiteHelper();
              
              InputStream myInput = getAssets().open(file_name);
              OutputStream myOutput = new FileOutputStream(file_path + file_name);
  
              byte[] buffer = new byte[1024];
              int length;
              while ((length = myInput.read(buffer)) > 0) {
                  myOutput.write(buffer, 0, length);
              }
  
              myOutput.flush();
              myOutput.close();
              myInput.close();
  
          } catch (IOException e) {
              e.printStackTrace();
          }
       }
       getDAOSQLiteHelper();
    }
    

    /**
     * Gets the dAOSQ lite helper.
     *
     * @return the dAOSQ lite helper
     */
    private void getDAOSQLiteHelper() {
      SQLiteDatabase db = (new DAOSQLiteHelper(MocoTruthOrDare.this)).getReadableDatabase();
      db.close();
    
    }
    
}