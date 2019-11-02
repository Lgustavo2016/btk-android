package com.lsandoval.btk_android.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lsandoval.btk_android.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewHolder.txtEmail = findViewById(R.id.editTxtEmail);
        mViewHolder.txtSenha = findViewById(R.id.editTxtSenha);
        mViewHolder.btnLogin = findViewById(R.id.btnLogin);
        mViewHolder.btnCadastrar = findViewById(R.id.btnRegister);

        mViewHolder.btnLogin.setOnClickListener(this);
        mViewHolder.btnCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            if (!fieldsFullfilled()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Usuário ou Senha inválido(s)", Toast.LENGTH_LONG).show();
            }
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    private boolean fieldsFullfilled() {
        return !("".equals(mViewHolder.txtEmail.getText().toString()) || "".equals(mViewHolder.txtSenha.getText().toString()));
    }

    private static class ViewHolder {
        TextView txtEmail;
        TextView txtSenha;

        Button btnLogin;
        Button btnCadastrar;
    }
}
