// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List
import java.util.*;

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {

        A1List node1 = new A1List(address, size, key);
        if(this.prev==null){
            A1List node2=this.next;
            this.next=node1;
            node1.prev=this;
            node1.next=node2;
            node2.prev=node1;
        }
        else{
            A1List i=this;
            while(i.prev!=null)
                i=i.prev;
            A1List node2=i.next;
            i.next=node1;
            node1.prev=i;
            node1.next=node2;
            node2.prev=node1;
        }
        return node1;
    }

    public boolean Delete(Dictionary d) 
    {

        for (A1List i=this; i.next!=null; i=i.next) {
            if (i.key==d.key && i==d) {
                A1List node1=i.prev;
                A1List node2=i.next;
                node1.next=node2;
                node2.prev=node1;
                return true;
            }
        }
        for (A1List i=this; i.prev!=null; i=i.prev) {
            if (i.key==d.key && d==i) {
                A1List node1=i.prev;
                A1List node2=i.next;
                node1.next=node2;
                node2.prev=node1;
                return true;
            }
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    { 
        if(exact==true){
            for (A1List i=this; i.next!=null; i=i.next) {
                if (i.key==k) {
                    return i;
                }
            }
            for (A1List i=this; i.prev!=null; i=i.prev) {
                if (i.key==k) {
                    return i;
                }
            }
            return null;
        }
        else{
            for (A1List i=this; i.next!=null; i=i.next)
                if (i.key>=k)
                    return i;
            for (A1List i=this; i.prev!=null; i=i.prev)
                if (i.key>=k)
                    return i;
            return null;
        }
    }

    public A1List getFirst(){
        if(this.prev==null && this.next.next==null)
            return null;
        else if(this.prev==null && this.next.next!=null)
            return this.next;
        else{
            A1List i=this;
            while(i.prev!=null)
                i=i.prev;
            if(i.next.next==null)
                return null;
            else
                return this.next;
        }
    }
    
    public A1List getNext() 
    {
        if(this.next.next==null)
            return null;
        else if(this.next==null)
            return null;
        else
            return this.next;
    }

    public boolean sanity()
    {
        if(this.prev==null){
            for(A1List i=this.next;i!=null;i=i.next){
                if(i.prev.next!=i)
                    return false;
            }
            for(A1List i=this;i.next!=null;i=i.next){
                if(i.next.prev!=i)
                    return false;
            }
        }
        else if(this.next==null){
            for(A1List i=this.prev;i!=null;i=i.prev){
                if(i.next.prev!=i)
                    return false;
            }
            for(A1List i=this;i.prev!=null;i=i.prev){
                if(i.prev.next!=i)
                    return false;
            }
        }
        else{
            for(A1List i=this;i!=null && i.next!=null;i=i.next){
                if(i.next.prev!=i)
                    return false;
                if(i.prev.next!=i)
                    return false;
            }
            for(A1List i=this;i!=null && i.prev!=null;i=i.prev){
                if(i.next.prev!=i)
                    return false;
                if(i.prev.next!=i)
                    return false;
            }
        }
        return true;
    }

}


