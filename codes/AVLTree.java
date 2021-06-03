// Class: Height balanced AVL Tree
// Binary Search Tree
import java.util.*;

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.

    public AVLTree Insert(int address, int size, int key){
        AVLTree i=new AVLTree(address,size,key);
        i.height=1;
        this.right=Insert2(this.right, i, address, size, key);
        this.right.parent=this;
        return i;
    }
    
    private AVLTree Insert2(AVLTree node, AVLTree i, int address, int size, int key) 
    {
        if(node==null)
            return i;
        if((key==node.key && address<node.address) || key<node.key){
            node.left=Insert2(node.left,i,address,size,key);
            node.left.parent=node;
        }
        else{
            node.right=Insert2(node.right,i,address,size,key);
            node.right.parent=node;
        }
        node.height=Math.max(Height(node.left),Height(node.right))+1;
        int diff=0;
        if(node!=null)
            diff=Height(node.left)-Height(node.right);
        int diffl=0;
        if(node.left!=null)
            diffl=Height(node.left.left)-Height(node.left.right);
        int diffr=0;
        if(node.right!=null)
            diffr=Height(node.right.left)-Height(node.right.right);
        if(diff>1 && diffl>=0)
            return rrotate(node);
        if(diff>1 && diffl<0){
            node.left=lrotate(node.left);
            return rrotate(node);
        }
        if(diff<-1 && diffr<=0)
            return lrotate(node);
        if(diff<-1 && diffr>0){
            node.right=rrotate(node.right);
            return lrotate(node);
        }
        return node;
    }

    private AVLTree rrotate(AVLTree i){
        AVLTree node=i.parent;
        AVLTree node1=i.left;
        AVLTree node2=node1.right;
        if(node.left==i)
            node.left=node1;
        else
            node.right=node1;
        node1.parent=node;
        node1.right=i;
        i.parent=node1;
        i.left=node2;
        if(node2!=null)
            node2.parent=i;
        i.height=Math.max(Height(i.right),Height(i.left))+1;
        node1.height=Math.max(Height(node1.right),Height(node1.left))+1;
        return node1;
    }

    private AVLTree lrotate(AVLTree i){
        AVLTree node=i.parent;
        AVLTree node1=i.right;
        AVLTree node2=node1.left;
        if(node.left==i)
            node.left=node1;
        else
            node.right=node1;
        node1.parent=node;
        node1.left=i;
        i.parent=node1;
        i.right=node2;
        if(node2!=null)
            node2.parent=i;
        i.height=Math.max(Height(i.right),Height(i.left))+1;
        node1.height=Math.max(Height(node1.right),Height(node1.left))+1;
        return node1;
    }

    private int Height(AVLTree i){
        if(i==null)
            return 0;
        else
            return i.height;
    }

    public boolean Delete(Dictionary e)
    {
        AVLTree n=this;
        if(this.parent==null)
            n=this.right;
        else
            while(n.parent!=null && n.parent.parent!=null)
                n=n.parent;
        Delete2(e, n);
        if(n.height==-1)
            return true;
        return false;
    }

    private AVLTree Delete2(Dictionary e, AVLTree node){
        if(node==null)
            return node;
        if(e.key<node.key || (e.key==node.key && e.address<node.address)){
            node.left=Delete2(e,node.left);
            if(node.left!=null)
                node.left.parent=node;
        }
        else if(e.key>node.key || (e.key==node.key && e.address>node.address)){
            node.right=Delete2(e,node.right);
            if(node.right!=null)
                node.right.parent=node;
        }
        else{
            node.height=-1;
            AVLTree n=null;
            if(node.left==null || node.right==null){
                if(node.left==null)
                    n=node.right;
                else
                    n=node.left;
                if(n==null){
                    if(node.parent.left==node)
                        node.parent.left=null;
                    else
                        node.parent.right=null;
                }
                else{
                    AVLTree node2=node.parent;
                    if(node2.right==node)
                        node2.right=n;
                    else
                        node2.left=n;
                    n.parent=node2;
                }
            }
            else{
                AVLTree node3=node.right;
                while(node3.left!=null)
                    node3=node3.left;
                n=new AVLTree(node3.address,node3.size,node3.key);
                AVLTree n1=node.parent;
                AVLTree n2=node.left;
                AVLTree n3=node.right;
                if(n1.left==node)
                    n1.left=n;
                else
                    n1.right=n;
                n.parent=n1;
                n.left=n2;
                if(n2!=null)
                    n2.parent=n;
                n.right=n3;
                if(n3!=null)
                    n3.parent=n;
                n.right=Delete2(node3,n.right);
                if(n.right!=null)
                    n.right.parent=n;
            }
            if(n==null)
                return null;
            n.height=Math.max(Height(n.left),Height(n.right))+1;
            int diff=0;
            if(n!=null)
                diff=Height(n.left)-Height(n.right);
            int diffl=0;
            if(n.left!=null)
                diffl=Height(n.left.left)-Height(n.left.right);
            int diffr=0;
            if(n.right!=null)
                diffr=Height(n.right.left)-Height(n.right.right);
            if(diff>1 && diffl>=0)
                return rrotate(n);
            if(diff>1 && diffl<0){
                n.left=lrotate(n.left);
                n.left.parent=n;
                return rrotate(n);
            }
            if(diff<-1 && diffr<=0)
                return lrotate(n);
            if(diff<-1 && diffr>0){
                n.right=rrotate(n.right);
                n.right.parent=n;
                return lrotate(n);
            }
            return n;
        }
        if(node==null)
            return null;
        node.height=Math.max(Height(node.left),Height(node.right))+1;
        int diff=0;
        if(node!=null)
            diff=Height(node.left)-Height(node.right);
        int diffl=0;
        if(node.left!=null)
            diffl=Height(node.left.left)-Height(node.left.right);
        int diffr=0;
        if(node.right!=null)
            diffr=Height(node.right.left)-Height(node.right.right);
        if(diff>1 && diffl>=0)
            return rrotate(node);
        if(diff>1 && diffl<0){
            node.left=lrotate(node.left);
            return rrotate(node);
        }
        if(diff<-1 && diffr<=0)
            return lrotate(node);
        if(diff<-1 && diffr>0){
            node.right=rrotate(node.right);
            return lrotate(node);
        }
        return node;
    }
        
    public AVLTree Find(int k, boolean exact)
    {
        if(k<0)
            return null;
        if(exact==true){
            AVLTree i=this;
            if(this.parent!=null){
                while(i.parent!=null)
                    i=i.parent;
            }
            AVLTree node2=null;
            for(;i!=null;){
                if(k==i.key){
                    node2=i;
                    break;
                }
                else if(k<i.key)
                    i=i.left;
                else 
                    i=i.right;
            }
            if(node2==null)
                return null;
            AVLTree node=node2;
            AVLTree nodef=node2;
            int key=node2.address;
            while(node!=null){
                if(node.key<node2.key)
                    node=node.right;
                else if(node.key>node2.key)
                    node=node.left;
                else{
                    if(node.address<node2.address){
                        key=node.address;
                        nodef=node;
                    }
                    node=node.left;
                }
            }
            return nodef;
        }
        else{
            AVLTree i=this;
            if(this.parent!=null){
                while(i.parent!=null)
                    i=i.parent;
            }
            AVLTree node1=null;
            AVLTree node2=null;
            for(;i!=null;){
                node1=i;
                if(k==i.key){
                    node2=i;
                    break;
                }
                else if(k<i.key)
                    i=i.left;
                else
                    i=i.right;
            }
            if(node2==null){
                if(k<node1.key)
                    return node1;
                else{
                    while(node1!=null && node1.key<k)
                        node1=node1.parent;
                    return node1;
                }
            }
            else{
                AVLTree node=node2;
                AVLTree nodef=node2;
                int key=node2.address;
                while(node!=null){
                    if(node.key<node2.key)
                        node=node.right;
                    else if(node.key>node2.key)
                        node=node.left;
                    else{
                        if(node.address<node2.address){
                            key=node.address;
                            nodef=node;
                        }
                        node=node.left;
                    }
                }
                return nodef;
            }
        }
    }

    public AVLTree getFirst()
    { 
        AVLTree i=this;
        if(this.parent!=null){
            while(i.parent!=null)
                i=i.parent;
        }
        if(i.parent==null && i.right==null)
            return null;
        else if(i.parent==null && i.right!=null){
            AVLTree node=i.right;
            while(node.left!=null)
                node=node.left;
            return node;
        }
        else{
            AVLTree node=i;
            while(node.left!=null)
                node=node.left;
            return node;
        }
    }

    public AVLTree getNext()
    {
        if(this.parent==null)
            return null;
        else if(this.right==null){
            if(this.parent.left==this)
                return this.parent;
            else if(this.parent.parent==null)
                return null;
            else{
                AVLTree node=this;
                while(node.parent!=null && node.parent.left!=node)
                    node=node.parent;
                return node.parent;
            }
        }
        else{
            AVLTree node=this.right;
            while(node.left!=null)
                node=node.left;
            return node;
        }
    }

    public boolean sanity()
    { 
        AVLTree node=this;
        while(node.parent!=null && node.parent.key!=-1){
            if(node.height!=Math.max(Height(node.left),Height(node.right))+1)
                return false;
            if((node.left!=null && node.left.parent==node) || (node.right!=null && node.right.parent==node))
                node=node.parent;
            else
                return false;
        }

        if(node.parent==null && (node.key!=-1 || node.left!=null))
            return false;
        if(node.parent!=null && node.parent.key==-1 && (node.parent.parent!=null || node.parent.left!=null))
            return false;
        if(node.parent==null)
            node=node.right;
        if(node==null)
            return true;
        boolean flag=Sane1(node) && Sane2(node,Integer.MIN_VALUE,Integer.MAX_VALUE);
        if(flag==true){
            while(node.getNext()!=null){
                if(node.key>node.getNext().key)
                    return false;
                if(node.key==node.getNext().key && node.address>node.getNext().address)
                    return false;
                node=node.getNext();
            }
            return true;
        }
        else
            return false;
    }

    private boolean Sane1(AVLTree i){
        if(i==null)
            return true;
        if(i.height!=Math.max(Height(i.left),Height(i.right))+1)
            return false;
        if(i.left!=null && i.left.parent!=i)
            return false;
        if(i.right!=null && i.right.parent!=i)
            return false;
        return Sane1(i.left) && Sane1(i.right);
    }

    private boolean Sane2(AVLTree node,int min,int max){
        if(node==null)
            return true;
        if(node.key<min || node.key>max)
            return false;
        return Sane2(node.right,node.key+1,max) && Sane2(node.left,min,node.key);
    }
}


