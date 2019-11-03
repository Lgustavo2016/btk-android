package com.lsandoval.btk_android.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lsandoval.btk_android.Controller.UserController;
import com.lsandoval.btk_android.Model.UserBean;
import com.lsandoval.btk_android.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mViewHolder.txtEmail = findViewById(R.id.editTxtEmail);
        mViewHolder.txtNome = findViewById(R.id.editTxtNome);
        mViewHolder.txtSenha = findViewById(R.id.editTxtSenha);
        mViewHolder.btnLogin = findViewById(R.id.btnLogin);
        mViewHolder.btnCadastrar = findViewById(R.id.btnRegister);

        mViewHolder.btnLogin.setOnClickListener(this);
        mViewHolder.btnCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            if (!this._fieldsFullFilled()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            } else {
                this._registerUser();
            }
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private boolean _fieldsFullFilled() {
        return !("".equals(mViewHolder.txtEmail.getText().toString()) ||
                "".equals(mViewHolder.txtSenha.getText().toString()) ||
                "".equals(mViewHolder.txtNome.getText().toString()));
    }

    private void _registerUser() {
        final UserBean user = new UserBean();
        final UserController userController = new UserController(this);

        user.setEmail(mViewHolder.txtEmail.getText().toString());
        user.setName(mViewHolder.txtNome.getText().toString());
        user.setPassword(mViewHolder.txtSenha.getText().toString());

        if (userController.registerUser(user)) {
            Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            Toast.makeText(this, "Erro ao cadastrar Usuário!", Toast.LENGTH_LONG).show();
        }
    }

    private static class ViewHolder {
        TextView txtNome;
        TextView txtEmail;
        TextView txtSenha;

        Button btnLogin;
        Button btnCadastrar;
    }
}
