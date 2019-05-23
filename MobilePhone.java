import java.util.*;
import java.io.*;
import java.lang.*;
/*
Write a java class called MobilePhone, with the following methods:
– MobilePhone(Int number): constructor to create a mobile
phone. Unique identifier for a mobile phone is an integer.
– public Int number(): returns the id of the mobile phone.
– public Boolean status(): returns the status of the phone, i.e.
switched on or switched off.
– public void switchOn(): Changes the status to switched on.
– public void switchOff(): Changes the status to switched off.
– public Exchange location(): returns the base station with which
the phone is registered if the phone is switched on and an excep-
tion if the phone is off. The class Exchange will be described
next.
*/
public class MobilePhone{
    int identity;
    Boolean status=true;
    Exchange tp;//parent
    
    MobilePhone(int number){
        identity = number;
    }
    public int number(){
        return identity;
    }
    public Boolean status(){
        return status;
    }
    public void switchOn(){
        status = true;
    }
    public void switchOff(){
        status = false;
    }
    public Exchange location(){
        //try{if(status==true){
           return tp;           
        //}else{
        //        throw new Exception();
        //      }}catch(Exception e){System.out.println("Phone is switched off");}
        // return null;
    }
}

