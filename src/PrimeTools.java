/**
 * A class with various methods dealing with prime numbers.
 *
 *@author Steven Bradley
 *@date November 6 2016
 *
 *@version CPE 103-05 - Paul Hatalsky
 */
 import java.util.*;
 import java.math.BigInteger;
 
 public class PrimeTools{
    //methods
    
    public static boolean isPrime(int n){
       BigInteger ONE = new BigInteger("1");
       BigInteger TWO = new BigInteger("2");
       BigInteger ZERO = new BigInteger("0");
       if(n < 2)
          return false;
       else if(n < 4)
          return true;
       else if(n %2 == 0)
          return false;
       BigInteger n1 = new BigInteger(((Integer)n).toString());
       BigInteger d = n1.subtract(ONE);
       int s = 0;
       Random rand = new Random();
       while(d.mod(TWO).equals(ZERO)){
          s++;
          d = d.divide(TWO);
       }
       for(int i = 0; i < 20; i++){ //witness loop
          int r = rand.nextInt(n-2);
          if(r <2){
             i--;
             continue;
          }
          BigInteger a = new BigInteger(((Integer)(r)).toString());
          BigInteger x = a.modPow(d,n1);
          if(x.equals(ONE) || x.equals(n1.subtract(ONE)))
             continue;
          int j =1;
          for(;j < s; j++){
             x = x.modPow(TWO,n1);
             if(x.equals(ONE)){
                return false;
             }
             if(x.equals(n1.subtract(ONE))){
                break;
             }
          }
          if(j == s){
             return false;
          }
       }
       return true;
    }
    
    public static int nextPrime(int n){
       int possiblePrime;
       if(n < 0)
          throw new IllegalArgumentException();
       if(n < 2)
          return 2;
       if(isPrime(n))
          return n;
       else if(n%2 == 0){
          possiblePrime = n +1;
          if(isPrime(possiblePrime))
             return possiblePrime;
       }
       else
          possiblePrime = n;
       while(possiblePrime <= Integer.MAX_VALUE){
          possiblePrime += 2;
          if(isPrime(possiblePrime))
             return possiblePrime;
       }
       throw new NoSuchElementException();
    }
 }