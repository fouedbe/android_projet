package com.example.minifood.helper;

import android.content.Context;
import android.widget.Toast;

import com.example.minifood.Interface.ChangeNumberitems;
import com.example.minifood.recommande_domain;

import java.util.ArrayList;

public class ManagemenTocart {
    private Context context;
    private TinyDB tinyDB;

    public ManagemenTocart(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);
    }
    public void  insertfood(recommande_domain item){

        ArrayList<recommande_domain> listfood=getlistcart();
        boolean existAlready =false;
        int n=0;
        for (int i=0;i<listfood.size();i++){
            if (listfood.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if (existAlready){
          listfood.get(n).setNumb(item.getNumb());
        }else {
            listfood.add(item);
        }
        tinyDB.putListObject("cartlist",listfood);
        Toast.makeText(context,"add to your cart",Toast.LENGTH_SHORT).show();
    }
    public ArrayList<recommande_domain>getlistcart(){
        return  tinyDB.getListObject("cartlist");
    }
    public void plusNumberfood(ArrayList<recommande_domain>listfood,int position,ChangeNumberitems changeNumberitemss){

        listfood.get(position).setNumb(listfood.get(position).getNumb()+1);
        tinyDB.putListObject("cartlist",listfood);
        changeNumberitemss.changed();

    }
    public void minusNumberfood(ArrayList<recommande_domain>listfood, int position, ChangeNumberitems changeNumberitemss){
         if(listfood.get(position).getNumb()==1){
             listfood.remove(position);
         }else {
             listfood.get(position).setNumb(listfood.get(position).getNumb() - 1);

         }
        tinyDB.putListObject("cartlist", listfood);
        changeNumberitemss.changed();

    }
    public double getfeetotel() {
        ArrayList<recommande_domain> listfood = getlistcart();
        double fee = 0;
        for (int i = 0; i < listfood.size(); i++) {
            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumb());
        }
        return  fee;
    }
}
