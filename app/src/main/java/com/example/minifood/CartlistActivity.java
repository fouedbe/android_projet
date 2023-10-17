package com.example.minifood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.minifood.Adapter.cartlistAdapter;
import com.example.minifood.Interface.ChangeNumberitems;
import com.example.minifood.helper.ManagemenTocart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartlistActivity extends AppCompatActivity {
  private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewlist;
    private ManagemenTocart managemenTocart;
    TextView totalitem,totaldelivery,totaltax,emptytxt,total,totalprix;
    private double tax;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartlist);
        
        managemenTocart=new ManagemenTocart(this);
        initview();
        initlist();
        calcultocart();
        buttonNavigation();
    }
    private void  buttonNavigation(){
          ImageView btncart =findViewById(R.id.btncart1);
         ImageView homebtn=findViewById(R.id.homebtn);
          btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartlistActivity.this,CartlistActivity.class));
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartlistActivity.this,MainActivity.class));
            }
        });
    }
    private void initview() {
        recyclerViewlist=findViewById(R.id.recyclecart);
        totalitem=findViewById(R.id.totalitems);
        totaldelivery=findViewById(R.id.deliveryprix);
        emptytxt=findViewById(R.id.empty);
        totaltax=findViewById(R.id.taxprix);
        total=findViewById(R.id.total);
        scrollView=findViewById(R.id.scrollView3);

    }
    private  void initlist(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewlist.setLayoutManager(linearLayoutManager);
        adapter=new cartlistAdapter(managemenTocart.getlistcart(), this, new ChangeNumberitems() {
            @Override
            public void changed() {
                calcultocart();
            }
        });
        recyclerViewlist.setAdapter(adapter);
        if(managemenTocart.getlistcart().isEmpty()){
            emptytxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptytxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private  void calcultocart(){
        double percentax=0.02;
        double delivery=10;
        tax=Math.round((managemenTocart.getfeetotel()*percentax)*100)/100;
        double totall=Math.round((managemenTocart.getfeetotel()+tax+delivery)*100)/100;
        double itemtotal=Math.round(managemenTocart.getfeetotel()*100)/100;
        totalitem.setText("$"+itemtotal);
        totaltax.setText("$"+tax);
        totaldelivery.setText("$"+delivery);
        total.setText("$"+totall);

    }
}