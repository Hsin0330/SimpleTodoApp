package com.example.hsinhung.simpletodo.utilities;

import android.util.Log;

import com.example.hsinhung.simpletodo.BuildConfig;

/**
 * Created by SymPhoNy on 11/11/2016.
 */

public class TodoLog {

    private static final String DEFAULT_TAG = "SimpleTodo";
    public static boolean ENABLED = BuildConfig.DEBUG;


    private static void log(int priority, String majorTag, String minorTag, String msg) {
        if (!ENABLED) return;
        String log = minorTag != null ? "[" + minorTag + "] " + msg : msg;
        Log.println(priority, majorTag, log);
    }

    public static void captureException(Throwable throwable) {
        if (ENABLED) e(DEFAULT_TAG, Log.getStackTraceString(throwable));
    }

    public static void v(String tag, String msg) {
        log(Log.VERBOSE, tag, null, msg);
    }

    public static void d(String tag, String msg) {
        log(Log.DEBUG, tag, null, msg);
    }

    public static void i(String tag, String msg) {
        log(Log.INFO, tag, null, msg);
    }

    public static void w(String tag, String msg) {
        log(Log.WARN, tag, null, msg);
    }

    public static void e(String tag, String msg) {
        log(Log.ERROR, tag, null, msg);
    }

    public static void wtf(String tag, String msg) {
        log(Log.ASSERT, tag, null, msg);
    }

    public static void v(String tag, String msg, Throwable tr) {
        log(Log.VERBOSE, tag, null, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static void d(String tag, String msg, Throwable tr) {
        log(Log.DEBUG, tag, null, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static void i(String tag, String msg, Throwable tr) {
        log(Log.INFO, tag, null, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static void w(String tag, String msg, Throwable tr) {
        log(Log.WARN, tag, null, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static void e(String tag, String msg, Throwable tr) {
        log(Log.ERROR, tag, null, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        log(Log.ASSERT, tag, null, msg + '\n' + Log.getStackTraceString(tr));
    }

    public static void v(String majorTag, String minorTag, String msg) {
        log(Log.VERBOSE, majorTag, minorTag, msg);
    }

    public static void d(String majorTag, String minorTag, String msg) {
        log(Log.DEBUG, majorTag, minorTag, msg);
    }

    public static void i(String majorTag, String minorTag, String msg) {
        log(Log.INFO, majorTag, minorTag, msg);
    }

    public static void w(String majorTag, String minorTag, String msg) {
        log(Log.WARN, majorTag, minorTag, msg);
    }

    public static void e(String majorTag, String minorTag, String msg) {
        log(Log.ERROR, majorTag, minorTag, msg);
    }

    public static void wtf(String majorTag, String minorTag, String msg) {
        log(Log.ASSERT, majorTag, minorTag, msg);
    }

    public static void v(String msg) {
        log(Log.VERBOSE, DEFAULT_TAG, null, msg);
    }

    public static void d(String msg) {
        log(Log.DEBUG, DEFAULT_TAG, null, msg);
    }

    public static void i(String msg) {
        log(Log.INFO, DEFAULT_TAG, null, msg);
    }

    public static void w(String msg) {
        log(Log.WARN, DEFAULT_TAG, null, msg);
    }

    public static void e(String msg) {
        log(Log.ERROR, DEFAULT_TAG, null, msg);
    }

    public static void wtf(String msg) {
        log(Log.ASSERT, DEFAULT_TAG, null, msg);
    }
}
