package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import de.hdodenhof.circleimageview.CircleImageView;

public class Cart extends AppCompatActivity {
        TextView name,price,quantityText;
        CircleImageView img;
        Button addToCartBtn;
        ImageButton  plusBtn,minusBtn;
        long price2;
        FirebaseAuth mAuth;
        FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        name=(TextView) findViewById(R.id.addtocart);
        price=(TextView) findViewById(R.id.totalAmount);
        img=(CircleImageView) findViewById(R.id.cart_food_img);
        plusBtn=(ImageButton) findViewById(R.id.plusBtn);
        minusBtn=(ImageButton) findViewById(R.id.minusBtn);
        addToCartBtn=(Button)findViewById(R.id.cartBtn);
        quantityText=(TextView)findViewById(R.id.quantityTextCart);


        Intent intent=getIntent();
        name.setText(intent.getStringExtra("name"));
        price.setText(intent.getStringExtra("price"));
        Glide.with(img.getContext()).load(intent.getStringExtra("purl")).into(img);




        price2=Long.parseLong(price.getText().toString());


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int integerQuantity=Integer.parseInt(quantityText.getText().toString());
                long priceUpdate=Long.parseLong(price.getText().toString());

                if(integerQuantity<10)
                {
                    integerQuantity = integerQuantity + 1;
                    priceUpdate=price2*integerQuantity;
                    quantityText.setText(String.valueOf(integerQuantity));
                    price.setText(String.valueOf(priceUpdate));
                }
                else{
                    Toast.makeText(Cart.this, "Item Quantity Should be upto 10", Toast.LENGTH_SHORT).show();
                }

            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int integerQuantity=Integer.parseInt(quantityText.getText().toString());
                long priceUpdate=Long.parseLong(price.getText().toString());
                if(integerQuantity==0)
                {
                    Toast.makeText(Cart.this, "No Item should be less than 1", Toast.LENGTH_SHORT).show();
                    priceUpdate=price2;

                }
                else if(integerQuantity>0){
                    integerQuantity = integerQuantity - 1;
                    if(integerQuantity==1)
                    {

                        quantityText.setText(String.valueOf(integerQuantity));
                        price.setText(String.valueOf(priceUpdate));
                    }
                    if(integerQuantity!=1)
                    {
                        try{
                            priceUpdate=price2*integerQuantity;
                            quantityText.setText(String.valueOf(integerQuantity));
                            price.setText(String.valueOf(priceUpdate));
                        }
                        catch (Exception e)
                        {

                        }
                    }

                }

            }
        });


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String intentName=intent.getStringExtra("name");
                Intent intent=new Intent(getApplicationContext(),OrderActivity.class);
                intent.putExtra("name",intentName);
                intent.putExtra("quantity",quantityText.getText().toString());
                intent.putExtra("price",price.getText().toString());

                firestoreInsert(intentName,quantityText.getText().toString(),price.getText().toString());

                startActivity(intent);

            }
        });



    }

    private void firestoreInsert(String n,String q,String p){
        Map<String,String> items=new HashMap<>();
        items.put("name",n);
        items.put("quantity",q);
        items.put("price",p);

        FirebaseFirestore dbRoot=FirebaseFirestore.getInstance();
        dbRoot.collection(Objects.requireNonNull(user.getEmail())).add(items).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(Cart.this, "", Toast.LENGTH_SHORT).show();
            }
        });


    }
}