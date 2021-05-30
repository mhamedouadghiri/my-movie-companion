package com.mhamed.mymoviecompanion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.mhamed.mymoviecompanion.ui.GenreActivity;
import com.mhamed.mymoviecompanion.ui.ListMoviesFragment;
import com.mhamed.mymoviecompanion.util.BaseActivity;

import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_EMAIL;
import static com.mhamed.mymoviecompanion.util.Constants.PREFERENCES_LOGIN_USERNAME;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MAIN_ACTIVITY";

    private SharedPreferences sharedPreferences;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setupToolbar();

        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListMoviesFragment()).commit();
        }
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setCustomView(R.layout.appbar_view);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        TextView emailTextView = findViewById(R.id.nav_email);
        if (emailTextView != null) {
            emailTextView.setText(sharedPreferences.getString(PREFERENCES_LOGIN_EMAIL, "Email"));
        }
        TextView usernameTextView = findViewById(R.id.nav_username);
        if (usernameTextView != null) {
            usernameTextView.setText(sharedPreferences.getString(PREFERENCES_LOGIN_USERNAME, "Username"));
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            logout();
        } else {
            Intent intent = new Intent(this, GenreActivity.class);
            intent.putExtra("genre", item.getTitle().toString());
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
