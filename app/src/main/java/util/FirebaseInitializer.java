package util;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class FirebaseInitializer {

    private static  FirebaseInitializer instance;
    private FirebaseAuth auth;
    private FirebaseFirestore firebaseStore;
    private FirebaseMessaging firebaseMessaging;


    //Construtor do firebase para implementar o padrão Singleton (padrão de  projeto de software onde uma classe tenha apenas uma insntancia)
    private FirebaseInitializer(){}
        public static FirebaseInitializer getInstance(){
            if (instance == null){
                instance = new FirebaseInitializer();
            }
            return instance;
        }

        // Método que usaremmos para inicializar o Firebase
    public void initialize(Context context){
            if (FirebaseApp.getApps(context).isEmpty()){
                FirebaseApp.initializeApp(context);
            }
            //Aqui nós iremos iniciar os serviços do Firebase
            auth  = FirebaseAuth.getInstance();
            firebaseStore = FirebaseFirestore.getInstance();
            firebaseMessaging  = FirebaseMessaging.getInstance();
        }

        //Gerar getters e setters

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getFirebaseStore() {
        return firebaseStore;
    }

    public FirebaseMessaging getFirebaseMessaging() {
        return firebaseMessaging;
    }
}