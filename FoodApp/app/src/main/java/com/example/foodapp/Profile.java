package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    TextView name;
    Button logout;
    EditText EmailEdit;
    CircleImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);

        imageView=(CircleImageView)findViewById(R.id.circular_user_image_profile);
        Glide.with(this).load(signInAccount.getPhotoUrl().toString()).into(imageView);

        name=(TextView) findViewById(R.id.profile_name_);
        name.setText(signInAccount.getDisplayName());

        EmailEdit=(EditText) findViewById(R.id.emailEdit);
        EmailEdit.setText(signInAccount.getEmail());

        logout=(Button) findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
            }
        });



    }
}