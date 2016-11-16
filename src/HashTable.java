/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** 
 * @author Steven Bradley
 * @date November 14 2016
 * @param <T>
 */
public interface HashTable<T>
{
   boolean add(T element);
   boolean contains(T element);
   boolean isEmpty();
   double loadFactor();
   boolean remove(T element);
   int size();
   int tableSize();
}
