package com.practical.aldnoah.lab22profile;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PROFILE_UPDATE = 1;
    public static final String PROFILE_NAME = "com.practical.aldnoah.lab22profile.name";
    public static final String PROFILE_EMAIL = "com.practical.aldnoah.lab22profile.email";
    private TextView textViewName, textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
    }

    public void updateProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivityForResult(intent, REQUEST_PROFILE_UPDATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PROFILE_UPDATE && resultCode == RESULT_OK) {
            String name, email;
            name = data.getStringExtra(PROFILE_NAME);
            email = data.getStringExtra(PROFILE_EMAIL);
            textViewName.setText(getString(R.string.lblName) + " " + name);
            textViewEmail.setText(getString(R.string.lblEmail) + " " + email);
        }
    }

    public void showWeb(View view) {
        String uri = "http://bait2073.000webhostapp.com/welcome/html";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        startActivity(intent);
    }

    public void showDial(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0123456789"));
        startActivity(intent);
    }

    public void sendMail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + "seekt@tarc.edu.my"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "No supported mail app!", Toast.LENGTH_SHORT).show();
        }
    }
}
