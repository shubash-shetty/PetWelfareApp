package com.example.pet_welfare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CardView cardView,cardw,cardbuy,cardwalk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((menuItem) -> {
            switch (menuItem.getItemId())
            {
                case R.id.nav_home:

                    Intent intent=new Intent(Dashboard.this,Dashboard.class);
                    startActivity(intent);
                    break;

                case R.id.nav_addpet:
                    Intent intent1=new Intent(Dashboard.this,AddPet.class);
                    startActivity(intent1);
                    break;

                case R.id.nav_logout:
                    Intent intent2=new Intent(Dashboard.this,MainActivity.class);
                    startActivity(intent2);
                    break;

                case R.id.nav_share:
                    try {


                        Intent intent3 = new Intent(Intent.ACTION_SEND);
                        intent3.setType("text/plain");
                        intent3.putExtra(Intent.EXTRA_SUBJECT, "Pet Welfare");
                        intent3.putExtra(Intent.EXTRA_TEXT, "https://play.google,com/store/apps/details?id=" + getApplicationContext().getPackageName());
                        startActivity(Intent.createChooser(intent3, "Share With"));
                        break;
                    } catch (Exception e){
                        Toast.makeText(this,"Unable to Share",Toast.LENGTH_SHORT).show();
                    }

                case R.id.nav_rate:
                    Intent intent4=new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSfkm_uRfnv892tPK0CICuQqGKCgqnl_SnVYIMCVJqqW4g1aIQ/viewform?vc=0&c=0&w=1&flr=0"));
                    startActivity(intent4);
                    break;
            }
            return true;
        });















        cardView=findViewById(R.id.AddPet);
        cardbuy=findViewById(R.id.BuyMe);
        cardw=findViewById(R.id.carww);
        cardwalk=findViewById(R.id.cardpetwalk);




        cardw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }

            private void openActivity() {
                Intent intent=new Intent(Dashboard.this,AdoptPet.class);
                startActivity(intent);
            }
        });








        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }

            private void openActivity() {
                Intent intent=new Intent(Dashboard.this,AddPet.class);
                startActivity(intent);
            }
        });

        cardbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }

            private void openActivity() {
                Intent intent=new Intent(Dashboard.this,BuyMe.class);
                startActivity(intent);
            }
        });


        cardwalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }

            private void openActivity() {
                Intent intent=new Intent(Dashboard.this,petwalk.class);
                startActivity(intent);
            }
        });
























        Toolbar toolbar = findViewById(R.id.toolbbar);

       setSupportActionBar(toolbar);
       navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_cllose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        return true;
    }
}