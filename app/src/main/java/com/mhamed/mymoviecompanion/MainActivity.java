package com.mhamed.mymoviecompanion;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.mhamed.mymoviecompanion.entity.User;
import com.mhamed.mymoviecompanion.remote.api.ApiClient;
import com.mhamed.mymoviecompanion.remote.api.MovieService;
import com.mhamed.mymoviecompanion.ui.ListMoviesFragment;
import com.mhamed.mymoviecompanion.util.BaseActivity;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MAIN_ACTIVITY";
    private final MovieService movieService = ApiClient.getInstance();

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        user = (User) getIntent().getSerializableExtra("User");

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
//        getMenuInflater().inflate(R.menu.menubar, menu);
//        MenuItem menuItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint(getString(R.string.search_movies));
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                movieService.searchMoviesByTitle(query).enqueue((SimpleCallback<MoviesResponse>) (call, response) -> {
//                    if (response.isSuccessful() && response.body() != null) {
//                        response.body().getMovies().forEach(movie -> Log.i(TAG, movie.getTitle()));
//                    } else {
//                        Toast.makeText(getApplicationContext(), "An error has occurred", Toast.LENGTH_SHORT).show();
//                        Log.e(TAG, "Error on successful query submit.");
//                    }
//                });
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//        });
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Romantic:
                // display romantic movie
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ListMoviesFragment()).commit();
                break;
            case R.id.Action:
                // display Action movie
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new ListeMovie_Fragment()).commit();
                break;
            case R.id.Cartoon:
                // display Cartoon movie
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new ListeMovie_Fragment()).commit();
                break;
            case R.id.War:
                // display War movie
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new ListeMovie_Fragment()).commit();
                break;
            case R.id.horror:
                // display horror movie
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new ListeMovie_Fragment()).commit();
                break;
            case R.id.Favoris:
                // display Favoris movie
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new ListeMovie_Fragment()).commit();
                break;
            case R.id.History:
                // display History movie
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new ListeMovie_Fragment()).commit();
                break;
            case R.id.logout:
                logout();
                break;
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
