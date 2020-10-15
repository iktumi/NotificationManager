package com.g.cordova.plugin;
// The native Toast API
import android.content.Context;
import android.widget.Toast;
// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

public class NotificationManager extends CordovaPlugin {
    private static final String DURATION_LONG = "long";
    @Override
    public boolean execute(String action, JSONArray args,
                           final CallbackContext callbackContext) {
		boolean result = true;

        // Verify that the user sent a 'show' action
        if (action.equals("show")) {
            result =  showMessage(args, callbackContext);
        } else if (action.equals("cancelAll")) {
            result = cancelAllNotifications(callbackContext);
        } else {
            callbackContext.error("\"" + action + "\" is not a recognized action.");
            result = false;
        }


        return result;
    }

    public boolean showMessage(JSONArray args, final CallbackContext callbackContext){
        String message;
        String duration;
        try {
            JSONObject options = args.getJSONObject(0);
            message = options.getString("message");
            duration = options.getString("duration");
        } catch (JSONException e) {
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
        }
        // Create the toast
        Toast toast = Toast.makeText(cordova.getActivity(), message,
                DURATION_LONG.equals(duration) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        // Display toast
        toast.show();
        // Send a positive result to the callbackContext
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
        callbackContext.sendPluginResult(pluginResult);
        return true;
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