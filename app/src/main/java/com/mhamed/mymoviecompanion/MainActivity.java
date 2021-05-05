package com.mhamed.mymoviecompanion;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.mhamed.mymoviecompanion.ui.ListeMovie_Fragment;


public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(savedInstanceState==null) {
            //default movie categorie
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new ListeMovie_Fragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search by title");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("press Enter",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("changing",newText);
                return true;
            }

        });
        return true;
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.Romantic:
                // display romantic movie
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new ListeMovie_Fragment()).commit();
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
                //
            case  R.id.LogOut:
                // display LogOut movie
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_Container,new ListeMovie_Fragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item )){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
