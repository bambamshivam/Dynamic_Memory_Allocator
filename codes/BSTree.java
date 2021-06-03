// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        BSTree node1=new BSTree(address,size,key);
        BSTree node2=null;
        for(BSTree i=this;i!=null;){
            node2=i;
            if((key==i.key && address<i.address) || key<i.key)
                i=i.left;
            else
                i=i.right;
        }
        if((key==node2.key && address<node2.address) || key<node2.key)
            node2.left=node1;
        else 
            node2.right=node1;
        node1.parent=node2;
        return node1;
    }

    public boolean Delete(Dictionary e)
    { 
        BSTree node1=null;
        for(BSTree i=this;i!=null;){
            node1=i;
            if(e.key==i.key && e.address==i.address && e.size==i.size)
                break;
            else if(e.key<=i.key)
                i=i.left;
            else 
                i=i.right;
        }
        if(node1.key==e.key && e.address==node1.address && e.size==node1.size){
            BSTree node2=node1.parent;
            if(node1.right==null || node1.left==null){
                if(node2.right==node1){
                    if(node1.right==null){
                        node2.right=node1.left;
                        if(node1.left!=null)
                            node1.left.parent=node2;
                    }
                    else{
                        node2.right=node1.right;
                        if(node2.right!=null)
                            node1.right.parent=node2;
                    }
                }
                else{
                    if(node1.right==null){
                        node2.left=node1.left;
                        if(node1.left!=null)
                            node1.left.parent=node2;
                    }
                    else{
                        node2.left=node1.right;
                        if(node1.right!=null)
                            node1.right.parent=node2;
                    }
                }
            }
            else{
                BSTree node3=node1.right;
                BSTree node4=null;
                while(node3.left!=null){
                    node4=node3;
                    node3=node3.left;
                }
                if(node4!=null){
                    node4.left=node3.right;
                    if(node3.right!=null)
                        node3.right.parent=node4;
                }
                else{
                    node1.right=node3.right;
                    if(node3.right!=null)
                        node3.right.parent=node1;
                }
                node1.address=node3.address;
                node1.size=node3.size;
                node1.key=node3.key;
            }
            return true;
        }
        else
            return false;
    }
      
    public BSTree Find(int key, boolean exact)
    { 
    	int a=Integer.MAX_VALUE;
        if(exact==true){
        	BSTree i=this;
        	if(this.parent!=null){
        		while(i.parent!=null)
        			i=i.parent;
        	}
            BSTree node2=null;
            for(;i!=null;){
                if(key==i.key && i.address<a){
                    node2=i;
                    a=i.address;
                    break;
                }
                else if(key<i.key)
                    i=i.left;
                else 
                    i=i.right;
            }
            if(node2==null)
            	return null;
            BSTree node=node2;
    		BSTree nodef=node2;
    		int k=node2.address;
    		while(node!=null){
    			if(node.key<node2.key)
    				node=node.right;
    			else if(node.key>node2.key)
    				node=node.left;
    			else{
    				if(node.address<node2.address){
    					k=node.address;
    					nodef=node;
    				}
    				node=node.left;
    			}
    		}
    		return nodef;
        }
        else{
        	BSTree i=this;
        	if(this.parent!=null){
        		while(i.parent!=null)
        			i=i.parent;
        	}
        	BSTree node1=null;
        	BSTree node2=null;
        	for(;i!=null;){
        		node1=i;
        		if(key==i.key){
        			node2=i;
        			break;
        		}
        		else if(key<i.key)
        			i=i.left;
        		else
        			i=i.right;
        	}
        	if(node2==null){
        		if(key<node1.key)
        			return node1;
        		else{
        			while(node1!=null && node1.key<key)
        				node1=node1.parent;
        			return node1;
        		}
        	}
        	else{
        		BSTree node=node2;
        		BSTree nodef=node2;
        		int k=node2.address;
        		while(node!=null){
        			if(node.key<node2.key)
        				node=node.right;
        			else if(node.key>node2.key)
        				node=node.left;
        			else{
        				if(node.address<node2.address){
        					k=node.address;
        					nodef=node;
        				}
        				node=node.left;
        			}
        		}
        		return nodef;
        	}
        }
    }

    public BSTree getFirst()
    { 
        BSTree i=this;
    	if(this.parent!=null){
    		while(i.parent!=null)
    			i=i.parent;
    	}
        if(i.parent==null && i.right==null)
            return null;
        else if(i.parent==null && i.right!=null){
            BSTree node=i.right;
            while(node.left!=null)
                node=node.left;
            return node;
        }
        else{
            BSTree node=i;
            while(node.left!=null)
                node=node.left;
            return node;
        }
    }

    public BSTree getNext()
    { 
        if(this.parent==null)
            return null;
        else if(this.right==null){
            if(this.parent.left==this)
                return this.parent;
            else if(this.parent.parent==null)
                return null;
            else{
                BSTree node=this;
                while(node.parent!=null && node.parent.left!=node)
                    node=node.parent;
                return node.parent;
            }
        }
        else{
            BSTree node=this.right;
            while(node.left!=null)
                node=node.left;
            return node;
        }
    }

    public boolean sanity()
    { 
        BSTree node=this;
        while(node.parent!=null && node.parent.key!=-1){
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
        if(node.right==null)
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

	private boolean Sane1(BSTree i){
		if(i==null)
			return true;
		if(i.left!=null && i.left.parent!=i)
			return false;
		if(i.right!=null && i.right.parent!=i)
			return false;
		return Sane1(i.left) && Sane1(i.right);
	}

	private boolean Sane2(BSTree node,int min,int max){
		if(node==null)
			return true;
		if(node.key<min || node.key>max)
			return false;
		return Sane2(node.right,node.key+1,max) && Sane2(node.left,min,node.key);
	}

}


 


