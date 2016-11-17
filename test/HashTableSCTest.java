import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * JUnit Tests for HashTable with separate chaining.
 * @author Steven
 * @date November 15 2016
 */
public class HashTableSCTest {
	
	private final double EPSILON = 0.001;
	
	//<editor-fold desc="Functionality Tests">
	
		//<editor-fold desc="Constructor Tests">
	@Test
	public void test01construction(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		int tableSize = instance.tableSize();
		int size = instance.size();
		long collisions = (instance.collisions());
		int maxCollisions = instance.maxCollisions();
		
		assertEquals(11, tableSize);
		assertEquals(0, size);
		assertEquals(0,collisions);
		assertEquals(0, maxCollisions);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test02BadConstructor(){
		HashTableSC<Integer> instance = new HashTableSC<>(-5);
	}
	 
	//</editor-fold>
	
		//<editor-fold desc="Tests for isEmpty()">
	@Test
	public void test03IsEmpty_notEmpty() {
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		boolean expResult = true;
		boolean result = instance.isEmpty();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test04isEmpty_empty(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		instance.add(1);
		boolean expResult = false;
		boolean result = instance.isEmpty();
		assertEquals(expResult, result);
	}
	//</editor-fold>	
	
		//<editor-fold desc="Tests for contains(T element)">
	@Test
	public void test05BasicDoesNotContain() {
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		boolean expResult = false;
		boolean result = instance.contains(15);
		assertEquals(expResult, result);
	}
	
	@Test
	public void test06BasicContains(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		instance.add(3);
		boolean expResult = true;
		boolean result = instance.contains(3);
	}

	@Test
	public void test07ContainsWithMultipleElements(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		instance.add(7);
		instance.add(13);
		instance.add(53);
		instance.add(5);
		instance.add(14);
		instance.add(17);
		boolean expResult = true;
		boolean result = instance.contains(53);
		assertEquals(expResult, result);
	}
	
	@Test
	public void test08DoesNotContainWithMultipleElements(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		instance.add(7);
		instance.add(13);
		instance.add(53);
		instance.add(5);
		instance.add(14);
		instance.add(17);
		boolean expResult = false;
		boolean result = instance.contains(37);
		assertEquals(expResult, result);
	}
	
	@Test
	public void test09ContainsWithLongChain(){
		HashTableSC<Integer> instance = new HashTableSC<>(1);
		instance.add(5);
		instance.add(15);
		instance.add(25);
		instance.add(35);
		instance.add(45);
		boolean expResult = true;
		boolean result = instance.contains(35);
		assertEquals(expResult, result);
	}
	
	@Test
	public void test10DoesNotContainPostRemove(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		instance.add(7);
		instance.add(13);
		instance.add(53);
		instance.add(5);
		instance.add(14);
		instance.add(17);
		instance.remove(53);
		boolean expResult = false;
		boolean result = instance.contains(53);
		assertEquals(expResult,result);
	}
	
	@Test
	public void test11ContainsPostRemove(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		instance.add(7);
		instance.add(13);
		instance.add(53);
		instance.add(5);
		instance.add(14);
		instance.add(17);
		instance.remove(53);
		boolean expResult = true;
		boolean result = instance.contains(14);
		assertEquals(expResult,result);
	}
	
	@Test
	public void test12ContainsPostRemoveFromLongChain(){
		HashTableSC<Integer> instance = new HashTableSC<>(1);
		instance.add(5);
		instance.add(15);
		instance.add(25);
		instance.add(35);
		instance.add(45);
		instance.remove(15);
		boolean expResult = true;
		boolean result = instance.contains(35);
		assertEquals(expResult, result);
	}
	
	@Test
	public void test13DoesNotContainPostRemoveFromLongChain(){
		HashTableSC<Integer> instance = new HashTableSC<>(1);
		instance.add(5);
		instance.add(15);
		instance.add(25);
		instance.add(35);
		instance.add(45);
		instance.remove(15);
		boolean expResult = false;
		boolean result = instance.contains(15);
		assertEquals(expResult, result);
	}
	//</editor-fold>
	
		//<editor-fold desc="Tests for add(T element)">
	@Test
	public void test14BasicAdd(){
		HashTableSC<Integer> instance = new HashTableSC<> (10);
		boolean expResult = true;
		boolean result = instance.add(15);
		assertEquals(expResult,result);
	}
	
	@Test
	public void test15Add_elementTwice() {
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		instance.add(15);
		boolean expResult = false;
		boolean result = instance.add(15);
		assertEquals(expResult, result);
	}
	
	@Test
	public void test16Add_toLongChain(){
		HashTableSC<Integer> instance = new HashTableSC<>(1);
		instance.add(5);
		instance.add(14);
		boolean result = instance.add(15);
		instance.add(36);
		boolean expResult = true;
		assertEquals(expResult, result);
	}
	//</editor-fold>
	
		//<editor-fold desc="Tests for remove(T element)">
	@Test
	public void test17BasicRemove_emptyTable(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		boolean expResult = false;
		boolean result = instance.remove(15);
		assertEquals(expResult,result);
	}
	
	@Test
	public void test18BasicRemove_nonEmptyTable(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		instance.add(15);
		boolean expResult = true;
		boolean result = instance.remove(15);
		assertEquals(expResult, result);
	}
	
	@Test
	public void test19RemoveFromLongChain(){
		HashTableSC<Integer> instance = new HashTableSC<>(1);
		for(int i = 0; i < 5; i ++){
			instance.add(i);
		}
		boolean expResult = true;
		boolean result = instance.remove(3);
		assertEquals(expResult, result);
	}
	//</editor-fold>
	
		//<editor-fold desc="Tests for collisions()">
	@Test
	public void test20CollisionsEmptyTree(){
		HashTableSC<Integer> instance = new HashTableSC<>(2);
		long expResult = 0;
		long result = instance.collisions();
		assertEquals(expResult,result);
	}
	
	@Test
	public void test21BasicCollisions(){
		HashTableSC<Integer>  instance = new HashTableSC<>(5);
		for(int i = 0; i < 12; i++){
			instance.add(i);
		}
		long expResult = 9;
		long result = instance.collisions();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test22CollisionsLongChain(){
		HashTableSC<Integer>  instance = new HashTableSC<>(2);
		for(int i = 0; i < 5; i++){
			instance.add(i*2);
		}
		long expResult = 10;
		long result = instance.collisions();
		assertEquals(expResult, result);
	}
	//</editor-fold>
	
		//<editor-fold desc="Tests for maxCollisions()">
	@Test
	public void test23MaxCollisionsEmptyTable(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		int expResult = 0;
		int result = instance.maxCollisions();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test24BasicMaxCollisions1(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		for(int i = 0; i < 5; i++){
			instance.add(i);
		}
		int expResult = 0;
		int result = instance.maxCollisions();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test25BasicMaxCollisions2(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		for(int i = 0; i < 10; i++){
			instance.add(i);
		}
		int expResult = 0;
		int result = instance.maxCollisions();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test26BasicMaxCollisions3(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		for(int i = 0; i < 15; i++){
			instance.add(i);
		}
		int expResult = 1;
		int result = instance.maxCollisions();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test27MaxCollisionsEqualLongChains(){
		HashTableSC<Integer> instance = new HashTableSC<>(2);
		for(int i = 0; i < 10; i++){
			instance.add(i);
		}
		int expResult = 4;
		int result = instance.maxCollisions();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test28MaxCollisionsLongChains(){
		HashTableSC<Integer> instance = new HashTableSC<>(2);
		for(int i = 0; i < 23; i++){
			instance.add(i);
		}
		int expResult = 11;
		int result = instance.maxCollisions();
		assertEquals(expResult, result);
	}
	//</editor-fold>
	
		//<editor-fold desc="Tests for loadFactor()">
	@Test
	public void test29loadFactorEmptyTable(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		double expResult = 0;
		double result = instance.loadFactor();
		assertEquals(expResult, result, EPSILON);
	}
	
	@Test
	public void test30BasicLoadFactor(){
		HashTableSC<Integer> instance = new HashTableSC<>(2);
		instance.add(1);
		instance.add(2);
		double expResult = 1;
		double result = instance.loadFactor();
		assertEquals(expResult, result, EPSILON);
	}
	
	@Test
	public void test31loadFactor_PartialTable(){
		HashTableSC<Integer> instance = new HashTableSC<>(5);
		instance.add(1);
		instance.add(2);
		instance.add(3);
		double expResult = 0.6;
		double result = instance.loadFactor();
		assertEquals(expResult, result, EPSILON);
	}
	
	@Test
	public void test32loadFactorLongChains(){
		HashTableSC<Integer> instance = new HashTableSC<>(3);
		for(int i = 1; i < 13; i*=2){
			instance.add(i);
			instance.add(i/2 + 3);
		}
		double expResult = (double)7/3;
		double result = instance.loadFactor();
		assertEquals(expResult,result,EPSILON);
	}
	//</editor-fold>
	
		//<editor-fold desc="Tests for tableSize()">
	@Test
	public void test33tableSize_zero(){
		HashTableSC<Integer> instance = new HashTableSC<>(0);
		int expResult = 2;
		int result = instance.tableSize();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test34tableSize_one(){
		HashTableSC<Integer> instance = new HashTableSC<>(1);
		int expResult = 2;
		int result = instance.tableSize();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test35tableSize_huge(){
		HashTableSC<Integer> instance = new HashTableSC<>(1000000);
		int expResult = 1000003;
		int result = instance.tableSize();
		assertEquals(expResult, result);
	}
	//</editor-fold>
	
		//<editor-fold desc="Tests for size()">
	@Test
	public void test36SizeEmptyTable(){
		HashTableSC<Integer> instance = new HashTableSC<Integer>(10);
		int expResult = 0;
		int result = instance.size();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test37SizePostAdd(){
		HashTableSC<Integer> instance = new HashTableSC<>(100000);
		for(int i = 0; i < 25000; i++){
			instance.add(i);
		}
		int expResult = 25000;
		int result = instance.size();
		assertEquals(expResult,result);
	}
	
	@Test
	public void test38SizePostRemove(){
		HashTableSC<Integer> instance = new HashTableSC<>(100000);
		for(int i = 0; i < 25000; i++){
			instance.add(i);
		}
		for(int i = 0; i < 12345; i++){
			instance.remove(i);
		}
		int expResult = 12655;
		int result = instance.size();
		assertEquals(expResult,result);
	}
	
	@Test
	public void test39SizeRemoveSameElement(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		for(int i = 0; i < 10; i++){
			instance.add(i);
		}
		assertEquals(10, instance.size());
		instance.remove(7);
		int expResult = 9;
		int result = instance.size();
		assertEquals(expResult,result);
		instance.remove(7);
		result = instance.size();
		assertEquals(expResult,result);
	}
	
	@Test
	public void test40AddSameElement(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		for(int i = 0; i < 5; i++)
			instance.add(i);
		instance.add(2);
		int expResult = 5;
		int result = instance.size();
		assertEquals(expResult, result);
	}
	
	@Test
	public void test41AddToChain(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		for(int i = 0; i < 75; i++)
			instance.add(i);
		
		int expResult = 75;
		int result = instance.size();
		assertEquals(expResult,result);
	}
	
	@Test
	public void test42RemoveFromChain(){
		HashTableSC<Integer> instance = new HashTableSC<>(10);
		for(int i = 0; i < 75; i++)
			instance.add(i);
		
		instance.remove(37);
		int expResult = 74;
		int result = instance.size();
		assertEquals(expResult,result);
	}
	//</editor-fold>
	
	//</editor-fold>
	
	//<editor-fold desc="Efficiency Tests">
      
}
