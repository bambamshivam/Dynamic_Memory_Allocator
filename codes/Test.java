class Test{
	public static void main(String args[]){
		A2DynamicMem obj=new A2DynamicMem(100,3);
		obj.Allocate(10);
		System.out.println(obj.freeBlk.sanity());
		System.out.println(obj.allocBlk.sanity());
		obj.Allocate(10);
		System.out.println(obj.freeBlk.sanity());
		System.out.println(obj.allocBlk.sanity());
		obj.Allocate(10);
		System.out.println(obj.freeBlk.sanity());
		System.out.println(obj.allocBlk.sanity());
		obj.Allocate(10);
		System.out.println(obj.freeBlk.sanity());
		System.out.println(obj.allocBlk.sanity());
		obj.Free(20);
		System.out.println(obj.freeBlk.sanity());
		System.out.println(obj.allocBlk.sanity());
		obj.Free(10);
		System.out.println(obj.freeBlk.sanity());
		System.out.println(obj.allocBlk.sanity());
		obj.Free(0);
		System.out.println(obj.freeBlk.sanity());
		System.out.println(obj.allocBlk.sanity());
		System.out.println("Free Memory Block");
		for(Dictionary i=obj.freeBlk.getFirst();i!=null && i.key!=-1;i=i.getNext())
			System.out.println(i.address+" "+(i.size+i.address));
		System.out.println("Allocated Memory Block");
		for(Dictionary i=obj.allocBlk.getFirst();i!=null && i.key!=-1;i=i.getNext())
			System.out.println(i.address+" "+(i.size+i.address));
	}
}