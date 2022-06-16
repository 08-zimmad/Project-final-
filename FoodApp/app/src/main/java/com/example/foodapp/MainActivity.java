package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    RecyclerView recview, recview2;
    Recycler_Adapter adapter, adapter2;
    TextView uName;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uName=(TextView) findViewById(R.id.usernameText);

        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null){
            uName.setText("Hi, "+signInAccount.getDisplayName());
        }


        recview =(RecyclerView) findViewById(R.id.category_recycle);
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("/Food"), model.class)
                        .build();
        adapter = new Recycler_Adapter(options);
        recview.setAdapter(adapter);


//        recview2 =(RecyclerView) findViewById(R.id.popular_recycle);
//        recview2.setLayoutManager(new LinearLayoutManager(this));
//        FirebaseRecyclerOptions<model> options2 =
//                new FirebaseRecyclerOptions.Builder<model>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("/Popular"), model.class)
//                        .build();
//        adapter2 = new Recycler_Adapter(options2);
//        recview2.setAdapter(adapter2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
//        adapter2.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
//        adapter2.stopListening();
    }
}

