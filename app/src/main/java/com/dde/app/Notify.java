package com.dde.app;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class Notify extends Application {
    private  static  Notify mInstance;
    private static final String ONESIGNAL_APP_ID = "a73a6e31-c7cf-4d3b-904d-f15306a3cc78";

    public  Notify(){
        mInstance=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        OneSignal.setNotificationOpenedHandler(
                new OneSignal.OSNotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(OSNotificationOpenedResult result) {
                        String actionId = result.getAction().getActionId();
                        OSNotificationAction.ActionType type = result.getAction().getType(); // "ActionTaken" | "Opened"
                        JSONObject data = result.getNotification().getAdditionalData();
                        if (data != null) {

                            if (data.toString() != ""){



                                try {
                                    JSONObject getData = new JSONObject(data.toString());
                                    MainActivity.Link = getData.getString("link");
//                                    URL url = new URL(Link);
//                                    String host = url.getHost();
//                                    Log.e("AJAY",host+"\n"+MainActivity.PRIVACY_URL);
//
//                                    if(host.equals(MainActivity.PRIVACY_URL)){
//
//                                    }else{
//                                        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Link));
//                                        myIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                                        startActivity(myIntent);
//                                    }

                                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                                    i.setFlags(FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }


                    }
                });
    }


}