// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
    	if(blockSize>0){
            Dictionary node2=this.freeBlk.Find(blockSize,false);
            if(node2==null)
                return -1;
            else{
                if(node2.key!=blockSize){                
                    this.allocBlk.Insert(node2.address,blockSize,node2.address);
                    node2.Delete(node2);
                    this.freeBlk.Insert(node2.address+blockSize,node2.size-blockSize,node2.size-blockSize);
                    return node2.address;
                }
                else{
                    node2.Delete(node2);
                    this.allocBlk.Insert(node2.address,node2.size,node2.address);
                    return node2.address;
                }
            }
        }
        else
    	return -1;
    } 
    
    public int Free(int startAddr) {
        Dictionary node1=this.allocBlk.getFirst();
        if(node1==null)
            return -1;
        else{
            Dictionary node2=node1.Find(startAddr,true);
            if(node2==null)
                return -1;
            else{
                this.freeBlk.Insert(node2.address,node2.size,node2.size);
                node2.Delete(node2);
                return 0;
            }
        }
    }
}