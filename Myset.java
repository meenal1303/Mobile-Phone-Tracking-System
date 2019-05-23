import java.lang.*;
import java.io.*;
import java.util.*;

class Myset extends linkedList{
/*
– public Boolean IsEmpty(): returns true if the set is empty.
– public Boolean IsMember(Object o): Returns true if o is in
the set, false otherwise.
– public void Insert(Object o): Inserts o into the set.
– public void Delete(Object o): Deletes o from the set, throws
exception if o is not in the set.
– public Myset Union(Myset a): Returns a set which is the union
of the current set with the set a.
– public Myset Intersection(Myset a): Returns a set which is
the intersection of the current set with the set a.
*/
    linkedList list = new linkedList();
    
    public Boolean IsEmpty(){
        if(list.size()==0){
            return true;
        }else {return false;}
    }
    public Boolean IsMember(Object o){
        return list.contains(o);
    }
    public void Insert(Object o){
        list.addLast(o);
    }
    public void Delete(Object o){
        list.remove(o);
    }
    public Myset Union(Myset a){
        Myset x = new Myset();
        int i=0;
        while(i<list.size()){
            x.Insert(list.get(i));
            i++;
        }
        int k=0;
        int j = a.list.size();
        while(k<j){
            if(!(x.list.contains(a.list.get(k)))){
                x.Insert(a.list.get(k));
            }
            k++;
        }
        return x;
    }
    public Myset Intersection(Myset a){
        Myset x = new Myset();
        int m=0;
        int i = list.size();
        while(m<i){
           if(a.list.contains(list.get(m))){
                x.Insert(list.get(m));
           }
           m++;
        }
        return x;
    }
}