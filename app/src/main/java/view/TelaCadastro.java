package view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cliqchat.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TelaCadastro extends AppCompatActivity {

    Button btnCancelarCadastro, btnCriarConta;
    EditText edtNomeCadastro, edtEmailCadastro, edtSenhaCadastro, edtConfirmarSenhaCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro);
        btnCancelarCadastro = findViewById(R.id.btnCancelarCadastro);
        btnCriarConta = findViewById(R.id.btnCriarConta);
        edtNomeCadastro = findViewById(R.id.edtNomeCadastro);
        edtEmailCadastro = findViewById(R.id.edtEmailCadastro);
        edtSenhaCadastro = findViewById(R.id.edtSenhaCadastro);
        edtConfirmarSenhaCadastro = findViewById(R.id.edtConfirmarSenhaCadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnCancelarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivity(intent);
            }
        });
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edtNomeCadastro.getText().toString().trim();
                String senha = edtSenhaCadastro.getText().toString().trim();
                String email = edtEmailCadastro.getText().toString().trim();
                String senhaConfirmar = edtConfirmarSenhaCadastro.getText().toString().trim();

                if (nome.isEmpty() || senha.isEmpty() || email.isEmpty() || senhaConfirmar.isEmpty()) {
                    Toast.makeText(TelaCadastro.this, "Por favor,  preencha todos  os campos!", Toast.LENGTH_LONG).show();
                } else if (!senha.equals(senhaConfirmar)) {
                    Toast.makeText(TelaCadastro.this, "As senhas não coincidem", Toast.LENGTH_LONG).show();
                } else {
                    CriarConta(nome, email, senha);
                }
            }
        });
    }

    private void CriarConta(String nome, String email, String senha) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        user.sendEmailVerification()
                                .addOnCompleteListener(TelaCadastro.this, task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(TelaCadastro.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(TelaCadastro.this, TelaLogin.class));
                                        finish();
                                    } else {
                                        Toast.makeText(TelaCadastro.this, "Erro ao enviar email" + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        Toast.makeText(TelaCadastro.this, "Erro ao criar conta:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
