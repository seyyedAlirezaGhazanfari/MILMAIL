package com.example.firstandroidprogram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonTapped(View view) {
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        String username = txtUsername.getText().toString();

        String password = txtPassword.getText().toString();
        Intent intent = new Intent(this,EmailListActivity.class);
        intent.putExtra("username",username);
        startActivity(intent);
        Gonnect.getData("http://spneshaei.com/mil/getEmails.php?username="+username+"&password="+password, this, new Gonnect.ResponseSuccessListener() {
            @Override
            public void responseRecieved(String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.equalsIgnoreCase("invalid-user")){{
                            Toast.makeText(MainActivity.this,"error invalid user",Toast.LENGTH_LONG).show();
                        }}else {
                            ArrayList<Email> newEmails = new Gson().fromJson(response,new TypeToken<ArrayList<Email>>(){}.getType());
                            Email.getAllEmails().clear();
                            Email.getAllEmails().addAll(newEmails);
                            Intent intent1 = new Intent(MainActivity.this,EmailListActivity.class);
                            startActivity(intent1);
                        }
                    }
                });
            }
        }, new Gonnect.ResponseFailureListener() {
            @Override
            public void responseFailed(IOException exception) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }


    // create mail button activity is added

    public void createEmail(View view) {
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();
        Intent intent = new Intent(this,SendEmailActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        startActivity(intent);
    }
}