package com.lsandoval.btk_android.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lsandoval.btk_android.Helper.SessionManager;
import com.lsandoval.btk_android.R;

public class MainActivity extends AppCompatActivity {

    private SessionManager mSessionManager;

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mSessionManager = new SessionManager(this);
        this.mSessionManager.checkLogin();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottom_nav);

        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        this._setupBottomNavMenu(navController);
        this._setupActionBar(navController);

    }

    private void _setupBottomNavMenu(NavController navController) {
        if (mBottomNavigationView != null) {
            NavigationUI.setupWithNavController(mBottomNavigationView, navController);
        }
    }

    private void _setupActionBar(NavController navController) {
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        final boolean navigated = NavigationUI.onNavDestinationSelected(item, navController);

        return navigated || super.onOptionsItemSelected(item);
    }

}
