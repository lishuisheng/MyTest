package lss.com.messengerclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG = "LISHUISHENG---" + MainActivity.class.getSimpleName();
    private ServiceConnection serviceConnection;
    private Messenger service_messenger;
    private Messenger client_messenger;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                service_messenger = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        Intent intent = new Intent();
        intent.setAction("lss.action.messenger");
        intent.setPackage("lss.com.messengerservice");
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"click");
                Message message = Message.obtain();
                message.what = 100;
                message.replyTo = client_messenger;
                if(service_messenger != null){
                    try {
                        Log.i(TAG,"client: send to service");
                        service_messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }else {
                    Log.i(TAG,"service_messenger is null");
                }
            }
        });

        client_messenger = new Messenger(new HandlerClient());
    }

    private class HandlerClient extends Handler{
        @Override
        public void handleMessage(Message msg_from_service) {

            switch (msg_from_service.what){
                case 120:
                    Bundle bundle = msg_from_service.getData();
                    String str = bundle.getString("key");
                    textView.setText(str);
                    break;
            }
            super.handleMessage(msg_from_service);
        }
    }
}
