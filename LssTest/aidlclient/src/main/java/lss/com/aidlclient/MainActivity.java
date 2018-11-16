package lss.com.aidlclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
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

import lss.com.aidlservice.IPerson;

public class MainActivity extends AppCompatActivity {

    private String TAG = "LISHUISHENG---" + MainActivity.class.getSimpleName();
    private ServiceConnection serviceConnection;
    private Button button;
    private TextView textView;
    private IPerson person;

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
                person = IPerson.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        Intent intent = new Intent();
        intent.setAction("lss.action.aidl");
        intent.setPackage("lss.com.aidlservice");
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person != null){
                    try {
                        textView.setText(person.getName());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

}
