package view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cliqchat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaLogin extends AppCompatActivity {
    private EditText edtEmail, edtSenha;
    private Button btnLogar;
    private TextView txtCriarConta;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_login);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogar = findViewById(R.id.btnLogar);
        txtCriarConta = findViewById(R.id.txtCriarConta);
        mAuth = FirebaseAuth.getInstance();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(TelaLogin.this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                            .addOnCompleteListener(TelaLogin.this, task -> {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                    if (user != null && user.isEmailVerified()) {
                                        startActivity(new Intent(TelaLogin.this, TelaChat.class));
                                        finish();
                                    } else {
                                        Toast.makeText(TelaLogin.this, "Por favor, verifique o e-mail de cadastro.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(TelaLogin.this, "Erro ao fazer login: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        txtCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(intent);
            }
        });

    }
    private void loginUsuario(String email, String senha) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if (user != null && user.isEmailVerified()) {
                            startActivity(new Intent(TelaLogin.this, TelaChat.class));
                            finish();
                        } else {
                            Toast.makeText(TelaLogin.this, "Por favor, verifique seu e-mail para ativar a conta.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TelaLogin.this, "Erro ao fazer login: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}



