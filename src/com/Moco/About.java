package com.Moco;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class About.
 * @author Mocoven mocovenwitch@gmail.com
 * @date 2011-08-08
 * @copyright 9tcatҹè All right reserved.
 * @description: Describe the App version and 9tcat.
 */
public class About extends Activity {

    /** The Constant SITE_URL. */
    private final static String SITE_URL = "http://www.9tcat.com"; 

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //set titlebar
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.about);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        
        initControls();
    }

    /**
     * Inits the controls.
     */
    private void initControls() {
        findViewById(R.id.button_about).setVisibility(View.GONE);
        
        TextView titlebarText = (TextView) findViewById(R.id.titlebar_text);
        titlebarText.setText(R.string.about_titlebar_text);
        
        //Register logo to visit site
        OnClickListener siteListener = new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(SITE_URL);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
            }
       };
       findViewById(R.id.about_logo).setOnClickListener(siteListener);
    }

}
