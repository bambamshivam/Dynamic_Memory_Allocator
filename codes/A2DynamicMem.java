// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() {
        if(this.type==2){
            Dictionary newFreeBlk=new BSTree();
            for(Dictionary i=this.freeBlk.getFirst();i!=null;i=i.getNext())
                newFreeBlk.Insert(i.address,i.size,i.address);
            for(Dictionary i=newFreeBlk.getFirst();i!=null && i.getNext()!=null;){
                if(i.address+i.size==i.getNext().address){
                    Dictionary node1=new BSTree(i.address,i.size,i.size);
                    Dictionary node2=new BSTree(i.getNext().address,i.getNext().size,i.getNext().size);
                    this.freeBlk.Delete(node1);
                    this.freeBlk.Delete(node2);
                    Dictionary node3=i.getNext();
                    newFreeBlk.Delete(i);
                    newFreeBlk.Delete(node3);
                    Dictionary node=newFreeBlk.Insert(i.address,i.size+node3.size,i.address);
                    this.freeBlk.Insert(i.address,i.size+node3.size,i.size+node3.size);
                    i=node;
                }
                else
                    i=i.getNext();
            }
        }
        else if(this.type==3){
            Dictionary newFreeBlk=new AVLTree();
            for(Dictionary i=this.freeBlk.getFirst();i!=null;i=i.getNext())
                newFreeBlk.Insert(i.address,i.size,i.address);
            for(Dictionary i=newFreeBlk.getFirst();i!=null && i.getNext()!=null;){
                if(i.address+i.size==i.getNext().address){
                    Dictionary node1=new AVLTree(i.address,i.size,i.size);
                    Dictionary node2=new AVLTree(i.getNext().address,i.getNext().size,i.getNext().size);
                    this.freeBlk.Delete(node1);
                    this.freeBlk.Delete(node2);
                    Dictionary node3=i.getNext();
                    newFreeBlk.Delete(i);
                    newFreeBlk.Delete(node3);
                    Dictionary node=newFreeBlk.Insert(i.address,i.size+node3.size,i.address);
                    this.freeBlk.Insert(i.address,i.size+node3.size,i.size+node3.size);
                    i=node;
                }
                else
                    i=i.getNext();
            }
        }
    }
}