package util;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseTest {
    public static void testFirebaseAuth(Context context){
        FirebaseAuth auth  = FirebaseAuth.getInstance();

            if(auth != null){
                if (auth.getCurrentUser() != null){
                    Toast.makeText(context, "Usuário autenticado:" + auth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Nenhumm usuário autenticado", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(context, "Falha na inicialização do Auth", Toast.LENGTH_SHORT).show();
            }
    }
}
