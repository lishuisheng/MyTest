package lss.com.aidlservice;

import android.os.IBinder;
import android.os.RemoteException;

public class Person extends IPerson.Stub {
    @Override
    public String getName() throws RemoteException {
        return "name from service";
    }
}
