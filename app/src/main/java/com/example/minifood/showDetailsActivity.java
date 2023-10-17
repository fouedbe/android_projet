package com.example.minifood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.minifood.helper.ManagemenTocart;

public class showDetailsActivity extends AppCompatActivity{

    private TextView titletxt,feetxt,descriptiontxt,numberordertxt,addtocart1;
    private ImageView plusbtn,minusbtn,picfood;
    private recommande_domain object;
    private ManagemenTocart managemenTocart;
    int numberor=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        managemenTocart=new ManagemenTocart(this);
        initiew();
        getBundle();
    }

    private void getBundle() {
        object=(recommande_domain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(object.getPic(), "drawable",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picfood);
        titletxt.setText(object.getTitle());
        feetxt.setText("$"+object.getFee());
        descriptiontxt.setText(object.getDescription());
        numberordertxt.setText(String.valueOf(numberor));

        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberor=numberor+1;
                numberordertxt.setText(String.valueOf(numberor));
            }
        });
        minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(numberor>1) {
                   numberor = numberor - 1;
               }
                numberordertxt.setText(String.valueOf(numberor));
            }
        });
        addtocart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumb(numberor);
                managemenTocart.insertfood(object);
            }
        });
    }

    private void initiew() {
        addtocart1=findViewById(R.id.addtocart123);
        titletxt=findViewById(R.id.titletxt);
        feetxt=findViewById(R.id.pricetxt);
        descriptiontxt=findViewById(R.id.description);
        numberordertxt=findViewById(R.id.numorder);
        plusbtn=findViewById(R.id.plusbtn);
        minusbtn=findViewById(R.id.minusbtn);
        picfood=findViewById(R.id.picfood);
    }
}