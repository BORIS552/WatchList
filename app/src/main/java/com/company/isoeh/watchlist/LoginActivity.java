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

import com.company.isoeh.watchlist.sqliteDb.UserRepo;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText email;
    private EditText password;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "a";
    public static final String Email = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.login);
        email = (EditText)findViewById(R.id.loginemail);
        password = (EditText)findViewById(R.id.loginpassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginData();
            }
        });
    }

    private void loginData(){
        UserRepo userRepo = new UserRepo(this);
        if(userRepo.checkUser(email.getText().toString().trim(), password.getText().toString())){
            sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Email, email.getText().toString().trim());
            editor.apply();
            editor.commit();
            Intent i = new Intent(LoginActivity.this, MovieHomeActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Invalid mail ID or Password", Toast.LENGTH_SHORT).show();
        }
    }
}
