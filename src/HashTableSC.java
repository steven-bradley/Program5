/**
 * Implementation of Separate Chaining Hash Table
 * @author Steven Bradley
 * @date November 14 2016
 */
public class HashTableSC<T> implements HashTable<T>, HashMetrics
{
   //Fields
   private Object[] table;
   private int size;
   private int maxCollisions;
   private long collisions;
   //Constructors
   public HashTableSC(int tableSize){
      if(tableSize < 0){
         throw new IllegalArgumentException();
      }
      int primeSize = PrimeTools.nextPrime(tableSize);
      table = new Object[primeSize];
      size = maxCollisions = 0;
      collisions = 0;
   }
   //Methods
	@SuppressWarnings("unchecked")
   public boolean contains(T element){
      int location = Math.abs((element.hashCode())) % table.length; 
      if(table[location] == null){
         return false;
      }
      TableEntry node = (TableEntry)(table[location]);
      while(node != null){
         if(node.element.equals(element)){
            return true;
         }
         else{
            node = node.next;
         }
      }
      return false;
   }
   
   public boolean isEmpty(){
      if(size == 0)
         return true;
      return false;
   }
   @SuppressWarnings("unchecked")
   public boolean add(T element){
      int location = Math.abs((element.hashCode())) % table.length;
      int curCol = 0;
      if(table[location] == null){
         table[location] = new TableEntry(element);
         size++;
         return true;
      }
      curCol++;
      TableEntry node = (TableEntry)(table[location]);
      while(node != null){
			if(node.element.equals(element)){
				return false;
			}
         if(node.next == null){
            node.next = new TableEntry(element);
            size++;
            collisions += curCol;
            if(curCol > maxCollisions){
               maxCollisions = curCol;
            }
            return true;
         }
         node = node.next;
         curCol++;
      }
      return false;
   }
   @SuppressWarnings("unchecked")
   public boolean remove(T element){
      int location = Math.abs(element.hashCode()) % table.length;
		if(table[location] == null){
			return false;
		}
		TableEntry node = ((TableEntry)(table[location]));
		if(node.element.equals(element)){
			table[location] = node.next;
			size--;
			return true;
		}
		while(node.next != null){
			if(node.next.element.equals(element)){
				node.next = node.next.next;
				size--;
				return true; 
			}
			else{
				node = node.next;
			}
		}
		return false;
   }
   
   public int size(){ return size; }
   
   public double loadFactor(){ return (double)(size)/(double)(table.length);}
   
   public int tableSize(){ return table.length; }
   
   public long collisions(){ return collisions;}
   
   public int maxCollisions(){ return maxCollisions; }
   
   //Private inner class
   private class TableEntry{
      private T element;
      private TableEntry next;
      
      public TableEntry(){
         element = null;
         next = null;
      }
      
      public TableEntry(T element){
         this.element = element;
         next = null;
      }
   }
}
