package com.shul.michalizralevitch.shul2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by michalizralevitch on 24/08/16.
 */
public abstract class FirebaseMessagingService extends Service {

    public FirebaseMessagingService (){

    }

    public abstract void handleIntent (Intent intent);


    public void onDeletedMessages (){

    }

  //  Called when GCM server deletes pending messages due t
  // o exceeded storage limits, for example, when the device cannot be reached for an extended period of time.

//    It is recommended to retrieve any missing messages directly from the app server.

    public void onMessageReceived (RemoteMessage message){

    }
    @Nullable
    @Override


    public IBinder onBind(Intent intent) {
        return null;
    }
}
