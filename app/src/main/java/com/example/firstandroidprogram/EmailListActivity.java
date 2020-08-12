package com.example.firstandroidprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class EmailListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_email_list);
        String username = this.getIntent().getStringExtra("username");

        Toast.makeText(this,username,Toast.LENGTH_LONG).show();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmailAdaptor adapter = new EmailAdaptor(this,Email.getAllEmails());

        recyclerView.setAdapter(adapter);
    }
}