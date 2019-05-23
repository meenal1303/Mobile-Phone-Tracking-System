import java.util.*;
import java.io.*;
import java.lang.*;

public class RoutingMapTree{
        ExList tt = new ExList();
        Exchange root;
        
        //function to find an Exchange
        public Exchange find(int i){
            Exchange p=(Exchange)tt.bt.get(0);
            int t=0;
            while(p!=null){
                if(p.number()==i){
                    return p;
                }t++;
                p=(Exchange)tt.bt.get(t);
            }
            return null;
        }
        
        //function to find a mobile phone
        public MobilePhone found(int i){            
            int t=0;                            
            MobilePhone w=(MobilePhone)root.residentSet().list.get(0);
            while(w!=null && t<root.residentSet().list.size()){
                if(w.number()==i){
                    return w;
                }
                else{
                t++;
                if(t<root.residentSet().list.size())
                    w=(MobilePhone)root.residentSet().list.get(t);
                
                }
            }
            return null;
        }
        
        public Boolean mphone(int i){
            int t=0;
            if(root.residentSet().list.size()==0){
            return false;}
            
            MobilePhone w=(MobilePhone)root.residentSet().list.get(0);
            while(w!=null ){
                if(w.number()==i)
                return true;
                
                else t++;
                if(t<root.residentSet().list.size()-1){
                //System.out.println("k");
                w=(MobilePhone)root.residentSet().list.get(t);
            }
        }
         return false;
        }
        
	RoutingMapTree() {
            root = new Exchange(0);
            root.par = null;
            tt.bt.addLast(root);
	}
        public Boolean containsNode(Exchange a){
            return tt.bt.contains(a);
        }
        public void switchOn(MobilePhone a, Exchange b){                 
            a.switchOn();
            a.tp=b;
        }
        public void switchOff(MobilePhone a){           
            if(a.status()==true){a.switchOff();}           
        }
        public Exchange findPhone(MobilePhone m){ 
        /*Given a mobile phone m it returns the level 0 area exchange with which it is registered or
        throws an exception if the phone is not found or switched off.*/
                try{
                if(m.status()==true)
                return m.location();
                
                else throw new Exception();
                }catch(Exception e){}
                
                return null;
        }
        public Exchange lowestRouter(Exchange a, Exchange b){
        /*Given two level 0 area exchanges a and b this method returns the level i
        exchange with the smallest possible value of i which contains both a
        and b in its subtree. If a = b then the answer is a itself.*/
            try{if(a==b)
                return a;
            
            else{
                while(a!=b){
                    if(a.parent()!=null && b.parent()!=null){
                        a=a.parent();
                        b=b.parent();
                    }
                }
                return a;
            }}catch(NullPointerException e){System.out.println("Error - Null Pointer Exception");}
            return null;
        }
        
        public void movePhone(MobilePhone a, Exchange b){
            if(a.status()==true && tt.bt.contains(b)==true){
                Exchange pp = a.location();
                Exchange temp1 = pp;
                    if(temp1!=null){
                        while(temp1!=null){
                            temp1.residentSet().list.remove(a);
                            temp1 = temp1.parent();
                        }
                    }
                a.tp = b;
                Exchange temp2 = b;
                if(temp2!=null){
                    while(temp2!=null){
                        temp2.residentSet().list.addLast(a);
                        temp2 = temp2.parent();
                    }
                }
            }
            else
            System.out.println("Map tree not modified.");
        }
        
        public ExchangeList routeCall(MobilePhone a, MobilePhone b){
            //System.out.println("kk");
            ExchangeList list = new ExchangeList();
            
            //System.out.println("kk");
            Exchange parA = a.location();
            Exchange parB = b.location();
            if(parA!=null && parB!=null){
                Exchange common = lowestRouter(parA,parB);
                Exchange temp1 = parA;
                Exchange temp2 = parB;
                
                try{if(temp1!=null && temp2!=null && common!=null){
                    while(temp1!=common){
                        list.addLast(temp1);
                        temp1 = temp1.parent();
                    }
                    list.addLast(common);
                    while(common!=temp2){
                        for(int i=0;i<common.numChildren();){
                            if(common.child(i).residentSet().list.contains(b) == false)
                            i++;
                            
                            else {
                                common = common.child(i);
                                list.addLast(common);
                                break;}                          
                        }
                    }
                    //list.addLast(temp2);
                    return list;
                }}catch(Exception e){System.out.println("Error");}
            }
            return null;
        }
        
	public String performAction(String actionMessage) {
            String ret="";
                    int i=0;
                    String newline = "" ;
                    while(actionMessage.charAt(i)!= ' '){
                        newline = newline + actionMessage.substring (i,i +1);
                        i++;
                    }
                    i++;
                    if(newline.equals("addExchange")){
                    /*This should create a new Exchange b, and add it to
                    the child list of Exchange a. If node a has n children, b should be its
                    (n + 1) th child. If there is no Exchange with identifier a, then throw an
                    Exception.*/
                        String a="",b="";
                        while(actionMessage.charAt(i)!=' '){
                            a = a + actionMessage.substring (i,i +1);
                            i++;
                        }
                        i++;
                        while(i < actionMessage.length()){
                            b = b + actionMessage.substring (i,i +1);
                            i++;
                        }
                        int x = Integer.parseInt(a);
                        int y = Integer.parseInt(b);
                        
                        try{Exchange t= find(x);
                        if(t!=null){
                            Exchange q = new Exchange(y);
                            tt.bt.addLast(q);
                            t.ch.addLast(q);
                            q.par=t;
                        }
                       ret = "";}catch(Exception e){ret=ret+("Exchange " +y+ " not found");}
                    }
                    
                    if(newline.equals("switchOnMobile")){
                    /*This should switch ON the mobile phone a at
                    Exchange b. If the mobile did not exist earlier, create a new mobile
                    phone with identifier a. If there is no Exchange with an identifier b,
                    throw an exception.*/
                        String a="",b="";
                        while(actionMessage.charAt(i)!=' '){
                            a = a + actionMessage.substring (i,i +1);
                            i++;
                        }
                        i++;
                        while(i < actionMessage.length()){
                            b = b + actionMessage.substring (i,i +1);
                            i++;
                        }
                            int x = Integer.parseInt(a);
                            int y = Integer.parseInt(b);
                        
                        //if(root.residentSet().list!=null && root.residentSet().list.contains(x)==false){
                            MobilePhone n = new MobilePhone(x);
                            Exchange t= find(y); 
                            if (t!=null){
                                n.tp=t;
                            }
                            //else{System.out.println("nice");}
                            Exchange temp=t;
                            try{
                            if(temp!=null){
                                while(temp!=null){
                                    temp.residentSet().list.addLast(n);
                                    temp = temp.parent();
                                }
                            } ret = "";} catch(Exception e){ret=ret+("Error Exchange not found");}                        
                    }
                       
                    if(newline.equals("switchOffMobile")){
                    //System.out.println("pp");
                    /*This should switch OFF the mobile phone a. If
                    there is no mobile phone with identifier a, then throw an Exception.*/
                        String a="";
                        while(i < actionMessage.length()){
                            a = a + actionMessage.substring (i,i +1);
                            i++;
                        }
                        int x = Integer.parseInt(a);
                        //if(root.residentSet().list.contains(x)==true){
                        //System.out.println("pp");
                            try{MobilePhone w = found(x);
                            //System.out.println("pp");
                            if(w!=null){
                            //System.out.println("pp");
                                Exchange pp = w.location();
                                //System.out.println("pp");
                                w.switchOff();
                                Exchange temp1 = pp;
                                if(temp1!=null){
                                    while(temp1!=null){
                                        //ss = found(x,temp1);
                                        //if(ss!=null){
                                            temp1.residentSet().list.remove(w);
                                            //temp1.residentSet().list.remove(s);
                                            
                                            temp1 = temp1.parent();
                                        }
                                }
                            }
                            ret = "";}catch(Exception e){ret=ret+("Mobile Phone Not Found");}
                        }                        
                    
                    if(newline.equals("queryNthChild")){
                    /*This should print the identifier of the Exchange
                    which is the (b)th child of Exchange a.*/
                        String a="",b="";
                        while(actionMessage.charAt(i)!=' '){
                            a = a + actionMessage.substring (i,i +1);
                            i++;
                        }
                        i++;
                        while(i < actionMessage.length()){
                            b = b + actionMessage.substring (i,i +1);
                            i++;
                        }
                        int x = Integer.parseInt(a);
                        int y = Integer.parseInt(b);
                        
                        try{Exchange t= find(x); 
                        if(t!=null){
                            if(t.ch.get(y) != null){
                                ret = actionMessage +": " + ((Exchange)t.ch.get(y)).number();
                                //System.out.print();
                                //System.out.println("13");
                            }
                            //System.out.println("");
                        }
                    }catch(Exception e){ret=ret+("Exchange not found");}
                    
                    } 
                    
                    if(newline.equals("queryMobilePhoneSet")){
                    /*This should print the identifier of all the mo-
                    bile phones which are part of the resident set of the Exchange with
                    identifier a.*/
                        String a="";
                        while(i < actionMessage.length()){
                            a = a + actionMessage.substring (i,i +1);
                            i++;
                        }
                        int x = Integer.parseInt(a);
                        try{Exchange t = find(x);
                        if(t!=null){
                            ret = actionMessage+": ";
                            for(int e=0,count=0;e<t.mm.list.size();e++){
                                ret = ret + ((MobilePhone)t.mm.list.get(e)).number();
                                if(count<t.mm.list.size()-1){
                                    ret = ret + ", ";
                                    count++;
                                }
                            }
                           
                        }
                    }catch(Exception e){ret=ret+("Exchange not found");}}
            
            if(newline.equals("findPhone")){
            /*This should print the identifier of the exchange re-
            turned by the findPhone(MobilePhone m) method. Here, m represents
            the mobile phone whose identifier is a.*/
                String a="";
                while(i < actionMessage.length()){
                    a = a + actionMessage.substring (i,i +1);
                    i++;
                }
                //System.out.println("k");
                int x = Integer.parseInt(a);
                ret=ret+"queryFindPhone "+x+": ";
               //if(root.residentSet().list.contains(found(x))==true)){
               if(mphone(x)==true){
               //System.out.println("k");
                MobilePhone m = found(x);
                if(m!=null){
                    ret=ret+(((Exchange)findPhone(m)).number());
                }}
                else ret=ret+("Error - No mobile phone with identifier "+x+" found in the network");
            }
            
            if(newline.equals("lowestRouter")){
            /*This should print the identifier of the ex-
            change returned by the lowestRouter(Exchange e1, Exchange e2) method.
            Here, e1 and e2 represent exchanges with identifier a and b respectively.*/
                String a="",b="";
                while(actionMessage.charAt(i)!=' '){
                    a = a + actionMessage.substring (i,i +1);
                    i++;
                }
                i++;
                while(i < actionMessage.length()){
                    b = b + actionMessage.substring (i,i +1);
                    i++;
                }
                int x = Integer.parseInt(a);
                int y = Integer.parseInt(b);
                Exchange e1 = find(x);
                Exchange e2 = find(y);
                if(e1!=null && e2!=null){
                    ret=ret+("queryLowestRouter "+x+ " "+y+": ");
                    ret=ret+(lowestRouter(e1,e2).number());
                }
            }
            if(newline.equals("findCallPath")){
            /*This should print the list returned by the
            routeCall(MobilePhone m1, MobilePhone m2) method. Here, m1 and
            m2 represent mobile phones with identifier a and b respectively. Suc-
            cessive entries in the list should be separated by a comma.*/
                String a="",b="";
                while(actionMessage.charAt(i)!=' '){
                    a = a + actionMessage.substring (i,i +1);
                    i++;
                }
                i++;
                while(i < actionMessage.length()){
                    b = b + actionMessage.substring (i,i +1);
                    i++;
                }
                int x = Integer.parseInt(a);
                int y = Integer.parseInt(b);
                 ret=ret+("queryFindCallPath "+x+ " "+y+": ");
                //if(mphone(x)==true && mphone(y)==true){
                MobilePhone m1 = found(x);
                MobilePhone m2 = found(y);
               
                if(m1!=null && m2!=null){
                //System.out.println("ffg");
                ExchangeList l1 = routeCall(m1,m2);
                if(l1!=null){
                //System.out.println(l1.size());
                    for(int e=0,count=0;e<l1.size();e++){
                            ret=ret+(((Exchange)l1.get(e)).number());
                            if(count<l1.size()-1){
                                ret=ret+(", ");
                                count++;
                            }
                    }
                        ret=ret+("");
                  }
                }
                else{
                if(m1==null){
                    ret=ret+("Error - Mobile phone with identifier "+x+" is currently switched off");
                }
                if(m2==null){
                    ret=ret+("Error - Mobile phone with identifier "+y+" is currently switched off");
                }
                }
            }
            
                        
            if(newline.equals("movePhone")){
            /*This action should set the level 0 area exchange of the
            mobile phone with identifier a to exchange with identifier b. Throw
            exception if mobile a is not available, or exchange b does not exist.*/
                String a="",b="";
                while(actionMessage.charAt(i)!=' '){
                    a = a + actionMessage.substring (i,i +1);
                    i++;
                }
                i++;
                while(i < actionMessage.length()){
                    b = b + actionMessage.substring (i,i +1);
                    i++;
                }
                int x = Integer.parseInt(a);
                int y = Integer.parseInt(b);
                MobilePhone move = found(x);
                Exchange moveTo = find(y);
                try{
                    if(move!=null && moveTo!=null)
                    movePhone(move,moveTo);
                    
                    else
                    throw new Exception();}catch(Exception e){ret=ret+("Error in movePhone");}
            }
            return ret;
	}
	
    }


