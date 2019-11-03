package com.lsandoval.btk_android.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lsandoval.btk_android.Helper.SessionManager;
import com.lsandoval.btk_android.R;

public class MainActivity extends AppCompatActivity {

    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mSessionManager = new SessionManager(this);
        this.mSessionManager.checkLogin();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
