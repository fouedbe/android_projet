package com.example.minifood;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.minifood.Adapter.RecommandAdapter;
import com.example.minifood.Adapter.categoryAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends  AppCompatActivity {
  private RecyclerView.Adapter adapter,adapter2;
  private RecyclerView recyclerViewcategories,recyclerViewrecommend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewcateg();
        recyclerViewrecom();
        buttonNavigation();


    }
    private void  buttonNavigation(){
         ImageView homebtn=findViewById(R.id.homebtn);
        ImageView btncart=findViewById(R.id.btncart);
        ImageView btncomm=findViewById(R.id.support);
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartlistActivity.class));
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
        btncomm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,activitycommuntaire.class));
            }
        });
    }
    private void recyclerViewrecom() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewrecommend=findViewById(R.id.view2);
        recyclerViewrecommend.setLayoutManager(linearLayoutManager);
        ArrayList<recommande_domain>recomdlist=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("categorie");
        adapter2=new RecommandAdapter(recomdlist);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                recomdlist.clear();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    recommande_domain user = userSnapshot.getValue(recommande_domain.class);
                    recomdlist.add(user);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        recomdlist.add(new recommande_domain("pepperoni pizza","pizza1","slice, peperoni, mozarella cheese ,fresh oregena, pizza sauce",9.97));
        recomdlist.add(new recommande_domain("cheese burger","burger","beef, Gouda cheese,special sauce,lettuce,tomato",8.6));
        recomdlist.add(new recommande_domain("vegetable pizza","pizza1","olive oil,vegetable oil,pitted kalamata,cherrytomate,fresh oregena",10.2));

        recyclerViewrecommend.setAdapter(adapter2);
    }

    private void recyclerViewcateg() {

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewcategories=findViewById(R.id.recyclerView);
        recyclerViewcategories.setLayoutManager(linearLayoutManager);
        ArrayList<category_domain>categorylist=new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("categorie");
        adapter=new categoryAdapter(categorylist);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
               categorylist.clear();

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                   category_domain user = userSnapshot.getValue(category_domain.class);
                    categorylist.add(user);
                }

              adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        categorylist.add(new category_domain("Pizza","cat_1"));
        categorylist.add(new category_domain("Burgur","cat_2"));
        categorylist.add(new category_domain("Hotdog","cat_3"));
        categorylist.add(new category_domain("Drink","cat_4"));
        categorylist.add(new category_domain("Dount","cat_5"));



        recyclerViewcategories.setAdapter(adapter);

    }


}