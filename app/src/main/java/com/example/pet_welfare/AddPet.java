package com.example.pet_welfare;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddPet extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    FirebaseDatabase mDatabase;
    DatabaseReference mref;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    FirebaseStorage mStorage;
    ImageButton imageButton;
    EditText name,breed,gender,age,weight,category,owner,ownerphn,perprice,buyprice,address,vacc,novacc;
    Button btnIns;
    private static final int Gallery_Code=1;
    Uri imageurl=null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        imageButton=findViewById(R.id.ImgAdd);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
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
                    Intent intent=new Intent(AddPet.this,Dashboard.class);
                    startActivity(intent);
                    break;

                case R.id.nav_addpet:
                    Intent intent1=new Intent(AddPet.this,AddPet.class);
                    startActivity(intent1);
                    break;

                case R.id.nav_logout:
                    Intent intent2=new Intent(AddPet.this,MainActivity.class);
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














        name=findViewById(R.id.Dname);
        breed=findViewById(R.id.Dbreed);
        gender=findViewById(R.id.Dgender);
        age=findViewById(R.id.Dage);
        weight=findViewById(R.id.Dweight);
        owner=findViewById(R.id.Downer);
        category=findViewById(R.id.Dcategory);
        perprice=findViewById(R.id.Dadop);
        buyprice=findViewById(R.id.Dprice);
        ownerphn=findViewById(R.id.Dphn);
        address=findViewById(R.id.Daddress);
        btnIns=findViewById(R.id.Btnins);
        vacc=findViewById(R.id.vacinated);
        novacc=findViewById(R.id.vacinatedNo);

        mDatabase=FirebaseDatabase.getInstance();
        mref= mDatabase.getReference().child("Student");
        mStorage=FirebaseStorage.getInstance();
        progressDialog=new ProgressDialog(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
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






    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && resultCode==RESULT_OK){
            imageurl=data.getData();
            imageButton.setImageURI(imageurl);
        }
        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fn=name.getText().toString().trim();
                String Db=breed.getText().toString().trim();
                String Da=age.getText().toString().trim();
                String Dw=weight.getText().toString().trim();
                String Dg=gender.getText().toString().trim();
                String Dow=owner.getText().toString().trim();
                String Dop=ownerphn.getText().toString().trim();
                String Dca=category.getText().toString().trim();
                String Dpp=perprice.getText().toString().trim();
                String Dbp=buyprice.getText().toString().trim();
                String Dadr=address.getText().toString().trim();
                String Dvacc=vacc.getText().toString().trim();
                String Dvaccno=novacc.getText().toString().trim();

                if(!(fn.isEmpty() && Db.isEmpty() && Da.isEmpty() && Dw.isEmpty() && Dg.isEmpty() && Dow.isEmpty() && Dop.isEmpty() && Dca.isEmpty() && Dadr.isEmpty() && Dvacc.isEmpty() && Dvaccno.isEmpty() && imageurl!=null))
                {
                            progressDialog.setTitle("uploading....");
                            progressDialog.show();

                    StorageReference filepath=mStorage.getReference().child("imagepost").child(imageurl.getLastPathSegment());
                    filepath.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUri=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t=task.getResult().toString();
                                    DatabaseReference newPost= mref.push();

                                    newPost.child("Name").setValue(fn);
                                    newPost.child("Breed").setValue(Db);
                                    newPost.child("Age").setValue(Da);
                                    newPost.child("Weight").setValue(Dw);
                                    newPost.child("Gender").setValue(Dg);
                                    newPost.child("Category").setValue(Dca);
                                    newPost.child("OwnerName").setValue(Dow);
                                    newPost.child("OwnerPhoneNumber").setValue(Dop);
                                    newPost.child("PricePerYear").setValue(Dpp);
                                    newPost.child("BuyingPrice").setValue(Dbp);
                                    newPost.child("Address").setValue(Dadr);
                                    newPost.child("Vaccinated").setValue(Dvacc);
                                    newPost.child("VaccinatedNo").setValue(Dvaccno);


                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                    Intent intent=new Intent(AddPet.this,AdoptPet.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                }

            }
        });
    }







}