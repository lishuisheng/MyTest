package lss.com.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class MessengerService extends Service {

    private String TAG = "LISHUISHENG---" + MessengerService.class.getSimpleName();
    private Messenger messenger = new Messenger(new HandlerService());
    private Messenger client_messenger;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if(messenger != null){
            return messenger.getBinder();
        }
        return null;
    }

    private class HandlerService extends Handler{
        @Override
        public void handleMessage(Message msg_from_client) {

            client_messenger = msg_from_client.replyTo;

            switch (msg_from_client.what){
                case 100:
                    sendToClient();
                    Log.i(TAG,"service: receive from client");
                    break;
            }
            super.handleMessage(msg_from_client);
        }
    }

    private void sendToClient(){
        Log.i(TAG,"service: send to client");
        Message message = Message.obtain();
        message.what = 120;
        Bundle bundle = new Bundle();
        bundle.putString("key", "lishuisheng from service");
        message.setData(bundle);
        if (client_messenger != null){
            try {
                client_messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
