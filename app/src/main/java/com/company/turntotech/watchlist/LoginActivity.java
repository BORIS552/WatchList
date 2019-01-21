package com.company.turntotech.watchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.turntotech.watchlist.sqliteDb.UserRepo;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText email;
    private EditText password;

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
            Intent i = new Intent(LoginActivity.this, MovieHomeActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Invalid mail ID or Password", Toast.LENGTH_SHORT).show();
        }
    }
}
