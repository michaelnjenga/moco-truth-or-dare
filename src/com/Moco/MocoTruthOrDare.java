package com.Moco;

import com.Moco.R;
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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MocoTruthOrDare extends Activity {

	private String WEIBO_URL = "http://t.sina.com.cn/mocovenwitch";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MobclickAgent.setDefaultReportPolicy(this, ReportPolicy.REALTIME);
        
        setContentView(R.layout.main);
        initControls();
        
        copyDatabase();
    }
    
    //initialize the controls
    public void initControls(){
    	
    	//set listener to play button
    	OnClickListener listener = new Button.OnClickListener(){
		     @Override
		     public void onClick(View v) {
		    	 play();
		    	 finish();
		     }
		};
		findViewById(R.id.btnPlay).setOnClickListener(listener);
    	
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
    public void play(){
    	Intent it = new Intent(this, Play.class);
        startActivity(it);
        
    }
    
    //goto Weibo
    public void gotoWeibo(){
    	
    	Uri uri = Uri.parse(WEIBO_URL);
    	Intent i = new Intent(Intent.ACTION_VIEW);
    	i.setData(uri);
    	startActivity(i);
    	
    }

    public void copyDatabase() {

        SQLiteDatabase db = (new DAOSQLiteHelper(MocoTruthOrDare.this))
              .getReadableDatabase();
        db.close();

        final String file_path = "//data//data//com.Moco//databases//";
        final String file_name = "mocotod.db3";

        // if file is not exist, copy it
        // File f = new File(file_path, file_name);
        // if (!f.exists()) {
        Log.d("T", "copy database from assets to package");

        try {
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
        // }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    
    @Override
    public void onPause() { 
        super.onPause(); 
        MobclickAgent.onPause(this); 
    }
}