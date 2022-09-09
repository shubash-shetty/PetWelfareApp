package com.example.pet_welfare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    EditText inputEmail,inputPassword,inputConPass,uername,phon;
    Button btnRegister;
    String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser muser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inputEmail=findViewById(R.id.inputEmail);
        uername=findViewById(R.id.Usr);
        phon=findViewById(R.id.editTextPhone);
        inputPassword=findViewById(R.id.inputPassword);
        inputConPass=findViewById(R.id.inputConPas);
        btnRegister=findViewById(R.id.btnRegister);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        muser=mAuth.getCurrentUser();


    btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PerforAuth();
        }

        private void PerforAuth() {
            String email=inputEmail.getText().toString();
            String UerName=uername.getText().toString();
            String Phone=phon.getText().toString();
            String password=inputPassword.getText().toString();
            String confirpass=inputConPass.getText().toString();


            if(!email.matches((emailPattern))){
                inputEmail.setError("enter correct email");
            }
            else if(UerName.isEmpty()){
                uername.setError("Fill it");
            }
            else if(Phone.isEmpty()){
                phon.setError("Fill it");
            }

            else if(password.isEmpty() || password.length()<6){
                inputPassword.setError("enter proper password");
            }else if(!password.equals(confirpass)){
                inputConPass.setError("password not matched");
            }
            else{
                    progressDialog.setMessage("Please Wait While Regitration");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                sendUserToNextActivity();
                                Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                                
                            }
                        }

                        private void sendUserToNextActivity() {
                            Intent intent=new Intent(RegisterActivity.this,Dashboard.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
            }
        }
    });
    }
}