package com.g.cordova.plugin;

import android.content.Context;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationManager extends CordovaPlugin {
    private static final String DURATION_LONG = "long";
    @Override
    public boolean execute(String action, JSONArray args,
                           final CallbackContext callbackContext) {
		boolean result = true;

        if (action.equals("cancelAll")) {
            result = cancelAllNotifications(callbackContext);
        } else {
            callbackContext.error("\"" + action + "\" is not a recognized action.");
            result = false;
        }


        return result;
    }


    public boolean cancelAllNotifications(final CallbackContext callbackContext) {
        Context context = this.cordova.getActivity().getApplicationContext();
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

        // Send a positive result to the callbackContext
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
        callbackContext.sendPluginResult(pluginResult);
        return true;


    }
}