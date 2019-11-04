package com.lsandoval.btk_android.View.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.lsandoval.btk_android.Controller.UserController;
import com.lsandoval.btk_android.Helper.SessionManager;
import com.lsandoval.btk_android.Model.UserBean;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final ViewHolder mViewHolder = new ViewHolder();
    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mSessionManager = new SessionManager(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mViewHolder.txtEmail = findViewById(R.id.editTxtEmail);
        mViewHolder.txtPassword = findViewById(R.id.editTxtPassword);
        mViewHolder.btnLogin = findViewById(R.id.btnLogin);
        mViewHolder.btnRegister = findViewById(R.id.btnRegister);

        mViewHolder.btnLogin.setOnClickListener(this);
        mViewHolder.btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            if (!fieldsFullfilled()) {
                Snackbar.make(v, "Preencha todos os campos!", Snackbar.LENGTH_LONG).show();
            } else {
                this._handleLogin(v);
            }
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        }
    }

    private boolean fieldsFullfilled() {
        return !("".equals(mViewHolder.txtEmail.getText().toString()) || "".equals(mViewHolder.txtPassword.getText().toString()));
    }

    private void _handleLogin(View v) {
        final UserBean user = new UserBean();
        final UserController userController = new UserController(this);

        user.setEmail(mViewHolder.txtEmail.getText().toString());
        user.setPassword(mViewHolder.txtPassword.getText().toString());

        final UserBean returnedUser = userController.authenticateUser(user);

        if (returnedUser != null) {
            this.mSessionManager.createLoginSession(returnedUser.getName(), returnedUser.getEmail(), returnedUser.getId());

            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Snackbar.make(v, "Usuário ou senha inválidos!", Snackbar.LENGTH_LONG).show();
        }
    }


    private static class ViewHolder {
        TextView txtEmail;
        TextView txtPassword;

        Button btnLogin;
        Button btnRegister;
    }
}
