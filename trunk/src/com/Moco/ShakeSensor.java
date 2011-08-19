package com.Moco;

import android.content.Context;   
import android.hardware.Sensor;   
import android.hardware.SensorEvent;   
import android.hardware.SensorEventListener;   
import android.hardware.SensorManager;   
import android.widget.Toast;
  
// TODO: Auto-generated Javadoc
/**
 * The Class ShakeSensor.
 */
public class ShakeSensor implements SensorEventListener {   
  
    /** The Constant FORCE_THRESHOLD. */
    private static final int FORCE_THRESHOLD = 350;   
    
    /** The Constant TIME_THRESHOLD. */
    private static final int TIME_THRESHOLD = 100;   
    
    /** The Constant SHAKE_TIMEOUT. */
    private static final int SHAKE_TIMEOUT = 500;   
    
    /** The Constant SHAKE_DURATION. */
    private static final int SHAKE_DURATION = 1000;   
    
    /** The Constant SHAKE_COUNT. */
    private static final int SHAKE_COUNT = 2;   
  
    /** The m sensor mgr. */
    private SensorManager mSensorMgr;   
    
    /** The m last z. */
    private float mLastX = -1.0f, mLastY = -1.0f, mLastZ = -1.0f;   
    
    /** The m last time. */
    private long mLastTime;   
    
    /** The m shake listener. */
    private OnShakeListener mShakeListener;   
    
    /** The m context. */
    private Context mContext;   
    
    /** The m shake count. */
    private int mShakeCount = 0;   
    
    /** The m last shake. */
    private long mLastShake;   
    
    /** The m last force. */
    private long mLastForce;   
  
    public boolean mSupportSensor = true;
    /**
     * The listener interface for receiving onShake events.
     * The class that is interested in processing a onShake
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addOnShakeListener<code> method. When
     * the onShake event occurs, that object's appropriate
     * method is invoked.
     *
     * @see OnShakeEvent
     */
    public interface OnShakeListener {   
        
        /**
         * On shake.
         */
        public void onShake();;   
//       public void onShakeHorizontal();   
//       public void onShakeVertical();   
     }   
  
    /**
     * Instantiates a new shake sensor.
     *
     * @param context the context
     */
    public ShakeSensor(Context context) {   
         mContext = context;   
         resume();   
     }   
  
    /**
     * Sets the on shake listener.
     *
     * @param listener the new on shake listener
     */
    public void setOnShakeListener(OnShakeListener listener) {   
         mShakeListener = listener;   
     }   

    /**
     * Resume.
     */
    public void resume() {   
        
        if(!mSupportSensor)return;
        
         mSensorMgr = (SensorManager) mContext   
                 .getSystemService(Context.SENSOR_SERVICE);   
        
         
        if (mSensorMgr == null) {   
//            throw new UnsupportedOperationException("Sensors not supported");

            mSupportSensor = false;
            return;
//            
        }   
           
        boolean supported = mSensorMgr.registerListener(this, mSensorMgr   
                 .getDefaultSensor(Sensor.TYPE_ACCELEROMETER),   
                 SensorManager.SENSOR_DELAY_UI);   
        if (!supported) {   
             mSensorMgr.unregisterListener(this);   
//            throw new UnsupportedOperationException(   
//                    "Accelerometer not supported");   
             
             mSupportSensor = false;
             return;
         }   
        
     }   
  
    /**
     * Pause.
     */
    public void pause() {   
        if (mSensorMgr != null) {   
             mSensorMgr.unregisterListener(this);   
             mSensorMgr = null;   
         }   
     }   
  
    /* (non-Javadoc)
     * @see android.hardware.SensorEventListener#onAccuracyChanged(android.hardware.Sensor, int)
     */
    @Override  
    public void onAccuracyChanged(Sensor sensor, int accuracy) {   
           
     }   
  
    /* (non-Javadoc)
     * @see android.hardware.SensorEventListener#onSensorChanged(android.hardware.SensorEvent)
     */
    @Override  
    public void onSensorChanged(SensorEvent event) {   
  
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {   
            return;   
         }   
  
        long now = System.currentTimeMillis();   
  
        if ((now - mLastForce) > SHAKE_TIMEOUT) {   
             mShakeCount = 0;   
         }   
  
        if ((now - mLastTime) > TIME_THRESHOLD) {   
            long diff = now - mLastTime;   
            float speed = Math.abs(event.values[SensorManager.DATA_X]   
                     + event.values[SensorManager.DATA_Y]   
                     + event.values[SensorManager.DATA_Z] - mLastX - mLastY - mLastZ)   
                     / diff * 10000;   
            if (speed > FORCE_THRESHOLD) {   
                if ((++mShakeCount >= SHAKE_COUNT)   
                         && (now - mLastShake > SHAKE_DURATION)) {   
                     mLastShake = now;   
                     mShakeCount = 0;   
                    if (mShakeListener != null) {   
                         mShakeListener.onShake();   
                     }   
                 }   
                 mLastForce = now;   
             }   
             mLastTime = now;   
             mLastX = event.values[SensorManager.DATA_X];   
             mLastY = event.values[SensorManager.DATA_Y];   
             mLastZ = event.values[SensorManager.DATA_Z];   
         }   
     }   
} 