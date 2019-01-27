package com.company.isoeh.watchlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.isoeh.watchlist.model.User;
import com.company.isoeh.watchlist.sqliteDb.UserRepo;

public class SignUpActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText mobile;
    private Button signup;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "a";
    public static final String Email = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        mobile = (EditText)findViewById(R.id.mobile);
        signup = (Button)findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(name.getText().toString().trim().isEmpty()){
                   Toast.makeText(SignUpActivity.this,"Name cannot be empty", Toast.LENGTH_SHORT).show();
                   return;
               }

               if (email.getText().toString().trim().isEmpty()){
                   Toast.makeText(SignUpActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                   return;
               }

               if (password.getText().toString().trim().isEmpty()){
                   Toast.makeText(SignUpActivity.this, "Please Enter a password", Toast.LENGTH_SHORT).show();
                   return;
               }

               if (mobile.getText().toString().trim().isEmpty()){
                   Toast.makeText(SignUpActivity.this, "Please Enter your mobile number", Toast.LENGTH_SHORT).show();
                   return;
               }
               registerUser();
            }
        });
    }

    private void registerUser(){
        UserRepo userRepo = new UserRepo(this);
        if(!userRepo.checkUser(email.getText().toString().trim())){
            User user = new User();
            user.setName(name.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());
            user.setMobile(mobile.getText().toString().trim());
            userRepo.insert(user);
            sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Email, email.getText().toString().trim());
            editor.apply();
            editor.commit();
            Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpActivity.this, MovieHomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Account Exists, please sign in", Toast.LENGTH_SHORT).show();
        }
    }
}
