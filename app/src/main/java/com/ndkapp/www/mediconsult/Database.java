package com.ndkapp.www.mediconsult;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 ="create table users(username text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table cart(username text,product text,price float,otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 = "create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry3);

        String qry4 = "create table appointments(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        sqLiteDatabase.execSQL(qry4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
   //register
    public void register(String username,String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }

    //login
    public int login(String username,String password){
        int result =0 ;
        String str[] = new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }

    //addToCart
    public void addCart(String username,String product,float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    //check the cart
    public int checkCart(String username,String product){
        int result = 0;
        String str[] = new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    //add order final
    public void addOrder(String username,String fullname,String address,String contact,int pincode,String date,String time,Float price,String otype){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("fullname",fullname);
        cv.put("address",address);
        cv.put("contact",contact);
        cv.put("pincode",pincode);
        cv.put("date",date);
        cv.put("time",time);
        cv.put("amount",price);
        cv.put("otype",otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.insert("appointments",null,cv);
        db.close();

    }

    //remove from cart
    public void removecart(String username,String otype){
        String str[] = new String[2];
        str[0]=username;
        str[1]=otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart","username=? and otype=?",str);
        db.close();
    }

    //get data of cart
    public ArrayList getCartData(String username,String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0]=username;
        str[1]=otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?",str);
        if(c.moveToFirst()){
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product+"$"+price);

            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }


    //show all booking after check out
    public ArrayList getOrderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0]=username;
        Cursor c = db.rawQuery("select * from orderplace where username = ?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }


}
