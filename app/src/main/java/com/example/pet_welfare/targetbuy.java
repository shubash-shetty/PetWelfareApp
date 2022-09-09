package com.example.pet_welfare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class targetbuy extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{



    private ImageView image;

    private TextView name,breed,weight,age,category,gender,owner,ownerphn,buyprice,address,vacc,novacc;

    DrawerLayout drawerLayout;
    Button btnBUYME;
    Dialog dialog;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_targetbuy);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        name=findViewById(R.id.ReName);
        breed=findViewById(R.id.ReBreed);
        weight=findViewById(R.id.ReWeight);
        age=findViewById(R.id.ReAge);
        category=findViewById(R.id.ReCategory);
        gender=findViewById(R.id.ReGender);
        owner=findViewById(R.id.ReOwner);
        ownerphn=findViewById(R.id.ReOnumb);
        buyprice=findViewById(R.id.ReBP);

        vacc=findViewById(R.id.reVaccine);
        novacc=findViewById(R.id.reVaccineNo);

        address=findViewById(R.id.ReAddr);









        name.setText(getIntent().getStringExtra("dname").toString());
        breed.setText(getIntent().getStringExtra("dbreed").toString());
        weight.setText(getIntent().getStringExtra("dwei").toString());
        age.setText(getIntent().getStringExtra("dage").toString());
        category.setText(getIntent().getStringExtra("dcat").toString());
        gender.setText(getIntent().getStringExtra("dgen").toString());
        owner.setText(getIntent().getStringExtra("downer").toString());
        ownerphn.setText(getIntent().getStringExtra("dphn").toString());
        buyprice.setText(getIntent().getStringExtra("buyp").toString());

        address.setText(getIntent().getStringExtra("daddr").toString());
        vacc.setText(getIntent().getStringExtra("dvacc").toString());
        novacc.setText(getIntent().getStringExtra("dvaccno").toString());





        btnBUYME=findViewById(R.id.ReBuyMe);
        dialog=new Dialog(this);
        btnBUYME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSuccdialog();
            }
        });





        Toolbar toolbar = findViewById(R.id.toolbbar);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_cllose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener((menuItem) -> {
            switch (menuItem.getItemId())
            {
                case R.id.nav_home:
                    Intent intent=new Intent(targetbuy.this,Dashboard.class);
                    startActivity(intent);
                    break;

                case R.id.nav_addpet:
                    Intent intent1=new Intent(targetbuy.this,AddPet.class);
                    startActivity(intent1);
                    break;

                case R.id.nav_logout:
                    Intent intent2=new Intent(targetbuy.this,MainActivity.class);
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
    private void openSuccdialog() {
        dialog.setContentView(R.layout.buysuccess);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnok=dialog.findViewById(R.id.btnDone);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }




}