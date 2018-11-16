package lss.com.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyAidlService extends Service {

    private IPerson.Stub person;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        person = new Person();
        if(person != null){
            return person;
        }
        return null;
    }
}
