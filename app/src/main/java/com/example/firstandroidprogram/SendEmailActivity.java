package com.example.firstandroidprogram;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SendEmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
    }

    public void sendEmail(View view) {
        EditText txtEmail = findViewById(R.id.emailBody);
        EditText txtSubject = findViewById(R.id.subject);
        String subject = txtSubject.getText().toString();
        String emailBody  = txtEmail.getText().toString();
        String username = this.getIntent().getStringExtra("username");
        String password = this.getIntent().getStringExtra("password");
        if (emailBody.equalsIgnoreCase("")){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("error in email body,do you want to change your email?");
            builder1.setCancelable(true);
            //here is alert
            builder1.setPositiveButton( "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder1.setNegativeButton("No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Intent intent = new Intent(SendEmailActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog alertDialog = builder1.create();
            alertDialog.show();
        }else {
            //this part is entering to email app of mobile phone button
            String[] addresses = new String[1];
            addresses[0] = username;
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
           // intent.putExtra(Intent.EXTRA_EMAIL,emailBody);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }
}