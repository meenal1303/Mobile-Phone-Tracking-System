import java.util.*;
import java.io.*;
import java.lang.*;
/*
Write a java class Exchange that will form the nodes of the routing
map structure. The class should have the following methods.
– Exchange(Int number): constructor to create an exchange.
Unique identifier for an exchange is an integer.
– All usual Node methods for a general tree like public Exchange
parent(), public Exchange numChildren() (for number of chil-
dren), public Exchange child(int i) (returns the ith child),
public Boolean isRoot(), public RoutingMapTree subtree(int
i) (returns the ith subtree) and any other tree methods you need.
The class definition RoutingMapTree will be defined later.
– public MobilePhoneSet residentSet(): This returns the resi-
dent set of mobile phones of the exchange.
Exchange a;
a.par=new Exchange(5);
Exchange x=a.parent();
int d = <Integer>o;
*/
class Exchange{
    int id;
    //Exchange x;
    MobilePhoneSet mm = new MobilePhoneSet();//residentSet
    //Exchange ex;//child
    Exchange par;  //parent  
    //RoutingMapTree subT;//subtree
    ExchangeList ch = new ExchangeList(); //ll of children
    
    //Myset ms;
    Exchange(int number){
        id = number;
    } 
    public int number(){
        return id;
    }
    public Exchange parent(){
        return par;
    }    
    public int numChildren(){
        return ch.size();
    }
    public Exchange child(int i){
        return (Exchange)ch.get(i);
       
    }
    public Boolean isRoot(){
        if(this.parent()==null){return true;}
        else {return false;}
    }
    /*public int numSubtree(){

    }
    public RoutingMapTree subtree(int i){
        
    }*/
    public MobilePhoneSet residentSet(){
               
        return mm;
    }
    
}












