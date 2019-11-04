package com.lsandoval.btk_android.View.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
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
        mViewHolder.txtSenha = findViewById(R.id.editTxtPassword);
        mViewHolder.btnLogin = findViewById(R.id.btnLogin);
        mViewHolder.btnCadastrar = findViewById(R.id.btnRegister);

        mViewHolder.btnLogin.setOnClickListener(this);
        mViewHolder.btnCadastrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            if (!this._fieldsFullFilled()) {
                Snackbar.make(v, "Preencha todos os campos!", Snackbar.LENGTH_LONG).show();
            } else {
                this._registerUser(v);
            }
        } else {
            Snackbar.make(v, "Cadastrado com sucesso!", Snackbar.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private boolean _fieldsFullFilled() {
        return !("".equals(mViewHolder.txtEmail.getText().toString()) ||
                "".equals(mViewHolder.txtSenha.getText().toString()) ||
                "".equals(mViewHolder.txtNome.getText().toString()));
    }

    private void _registerUser(View v) {
        final UserBean user = new UserBean();
        final UserController userController = new UserController(this);

        user.setEmail(mViewHolder.txtEmail.getText().toString());
        user.setName(mViewHolder.txtNome.getText().toString());
        user.setPassword(mViewHolder.txtSenha.getText().toString());

        if (userController.registerUser(user)) {
            Snackbar.make(v, "Cadastrado com sucesso!", Snackbar.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            Snackbar.make(v, "Erro ao cadastrar usuário!", Snackbar.LENGTH_LONG).show();
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
