package com.lsandoval.btk_android.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lsandoval.btk_android.Helper.SessionManager;
import com.lsandoval.btk_android.R;

public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    private SessionManager mSessionManager;

    private BottomNavigationView mBottomNavigationView;

    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.mSessionManager = new SessionManager(this);
        this.mSessionManager.checkLogin();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigationView = findViewById(R.id.bottom_nav);

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);

        this._setupBottomNavMenu(mNavController);
        this._setupActionBar(mNavController);
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
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, (DrawerLayout) null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        menu.findItem(R.id.exit_app).setOnMenuItemClickListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        final boolean navigated = NavigationUI.onNavDestinationSelected(item, navController);

        return navigated || super.onOptionsItemSelected(item);
    }

    private boolean _logout() {
        mSessionManager.logoutUser();
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return this._logout();
    }
}
