package com.company.turntotech.watchlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText mobile;
    private Button signup;
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
                String str_name = name.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();
                String str_mobile = mobile.getText().toString();
                //register data
                Intent i = new Intent(SignUpActivity.this, MovieHomeActivity.class);
                startActivity(i);
            }
        });
    }
}
