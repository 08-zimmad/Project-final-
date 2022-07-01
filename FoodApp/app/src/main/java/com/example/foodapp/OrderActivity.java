package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {
    String foodName,foodPrice,foodQuantity;
    RecyclerView recyclerView;
    ArrayList<OrderModel> dataholder;
    FirebaseFirestore db;
    OrderAdapter adapter;
    ProgressDialog progressDialog;
    Button confirmOrderBtn;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FloatingActionButton homeBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);


        db=FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();


        Intent intent=getIntent();
        foodName=intent.getStringExtra("name");
        foodPrice=intent.getStringExtra("price");
        foodQuantity=intent.getStringExtra("quantity");



        progressDialog=new ProgressDialog(this);
        EventChangeListener();

        homeBtn=(FloatingActionButton)findViewById(R.id.HomeBtnOrder);
        confirmOrderBtn=findViewById(R.id.orderBtnOrder);
        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete();
                Intent intent=new Intent(OrderActivity.this,FinalActivity.class);
                startActivity(intent);


            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView=(RecyclerView)findViewById(R.id.OrderList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        dataholder=new ArrayList<>();
        adapter=new OrderAdapter(dataholder,OrderActivity.this);
        recyclerView.setAdapter(adapter);






    }

    private void EventChangeListener() {

        db.collection(Objects.requireNonNull(user.getEmail())).orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error!=null) {

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("FireStore Error", error.getMessage());
                            return;
                        }
                       for(DocumentChange dc : value.getDocumentChanges()){
                           if(dc.getType()==DocumentChange.Type.ADDED){

                               dataholder.add(dc.getDocument().toObject(OrderModel.class));
                           }
                           adapter.notifyDataSetChanged();
                           if(progressDialog.isShowing())
                               progressDialog.dismiss();

                       }

                    }
                });


    }

    private void Delete()
    {
        db.collection(Objects.requireNonNull(user.getEmail())).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                WriteBatch b=FirebaseFirestore.getInstance().batch();
                List<DocumentSnapshot> s=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot:s)
                {
                    b.delete(snapshot.getReference());
                }
                b.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(OrderActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }




}