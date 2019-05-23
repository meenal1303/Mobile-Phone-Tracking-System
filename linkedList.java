public class linkedList{
    class node{
        private Object data;
        private node next;
        node(){
            data=null;
            next=null;
        }
        public node(Object a){
            data=a;
        }
        public Object value(){
            return data;}
        
        public node successor(){
            return next;}
        
        public void setnext(node a){
            next=a;}
    }
    
    node object=new node();
    private int size;
    linkedList(){
        object=null;
        size=0;
    }
    
    
    public void addLast(Object a){
        if(size==0){
            object = new node(a);
            object.setnext(null);
        }
        else{
            node temp = object;
            while(temp.successor()!=null){
                temp=temp.successor();
            }
            node b = new node(a);
            temp.setnext(b);
            b.setnext(null);
        }
        size++;
    }
    
    public int size(){
        return size;
    }
    
    public boolean isEmpty(){
        if(size()==0)
            return true;
        else
            return false;
    }
    
    public boolean contains(Object a){
        if(object==null)
            return false;
        else{
        node temp = object;
        while(temp!=null){
            if(temp.data.equals(a))
                return true;
            temp=temp.successor();
        }
        return false;
        }
    }
    
    public void remove(Object a){
    node temp = object;
        if(contains(a)==false)
            System.out.println(a+ " is not present.");
        
        else if(object.data.equals(a)){
            object=object.successor();
            size--;
        }
        else{
            while(temp.successor().data!=a){
                temp=temp.successor();
            }
            temp.setnext(temp.successor().successor());
            size-- ;
            }   
    }
    public Object get(int i){
        if(i>=size)
            System.out.println("Index Out Of Bound error ");
        else{
        int t=0;
        node temp = object;
        while(temp.successor()!=null && t<i){
            temp=temp.successor();
            t++;
        }
        if(temp!=null){
            return temp.value();
        }
        }
        return null;
        
    }
    

}