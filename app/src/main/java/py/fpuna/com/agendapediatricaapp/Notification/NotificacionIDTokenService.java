package py.fpuna.com.agendapediatricaapp.Notification;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


/**
 * Created by root on 29/12/16.
 */

public class NotificacionIDTokenService extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        registrarToken(token);

    }

    public void  registrarToken(String token){

        System.out.println("FIREBASE_TOKEN: " + token);


    }

}
