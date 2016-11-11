package com.example.hsinhung.simpletodo.utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by SymPhoNy on 11/6/15.
 */
public class LocalBroadcastTool {

    private static final String TAG = LocalBroadcastTool.class.getSimpleName();

    public static void send(Context context, Intent intent) {
        Log.w(TAG, "send { act=" + intent.getAction() + ", extras=" + bundleToString(intent.getExtras()) + " }");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public static void register(Context context, BroadcastReceiver receiver, String... actions) {
        IntentFilter filter = new IntentFilter();
        for (String action : actions) filter.addAction(action);
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
    }

    public static void unregister(Context context, BroadcastReceiver receiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }

    public static String bundleToString(Bundle bundle) {
        if (bundle == null) return "";

        String separator = "";
        StringBuilder sb = new StringBuilder("{ ");
        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            sb.append(separator).append(key).append(":");

            if (value == null) {
                sb.append("<null>");
            } else if (value instanceof String) {
                sb.append("\"").append(value).append("\"");
            } else if (value instanceof Boolean || value instanceof Integer || value instanceof Float || value instanceof Double) {
                sb.append(value);
            } else {
                sb.append(value).append(" (").append(value.getClass().getSimpleName()).append(")");
            }
            separator = ", ";
        }
        sb.append(" }");
        return sb.toString();
    }
}
