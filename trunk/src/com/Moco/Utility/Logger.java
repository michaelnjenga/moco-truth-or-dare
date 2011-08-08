package com.Moco.Utility;

import android.util.Log;
/**
 * The Class Log.
 */
public class Logger {
    /** Turn on the log when it's true. */
    public static final boolean DEBUG = false;

    /**
     * Instantiates a new log util.
     */
    private Logger() {
    }

    /**
     * Print debug log.
     * 
     * @param tag the tag
     * @param msg the msg
     */
    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * Print error log.
     * 
     * @param tag the tag
     * @param msg the msg
     */
    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }
}
