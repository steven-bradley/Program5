import java.util.*;
import java.io.*;
/**
 * Spell Checker class that uses HashMap and HashTableSC to spell check txt files.
 * 
 * @author Steven Bradley
 * @date November 17 2016
 * @version Program 5 - Spell Checker
 */
public class SpellChecker
{
   //<editor-fold desc="Private Fields">
      //Fields
      HashTableSC<String> dictionary;
   //</editor-fold>
      
   //<editor-fold desc="Constructors">
      
      public SpellChecker() throws FileNotFoundException{
         File f = new File("dictionary.txt");
         dictionary = new HashTableSC<String>(267119);
         Scanner s = new Scanner(f);
         while(s.hasNextLine()){
            dictionary.add(s.nextLine());
         }
      }
      
      public SpellChecker(String fileName)throws FileNotFoundException{
         File f = new File(fileName);
         int words = 0;
         Scanner s = new Scanner(f);
         while(s.hasNextLine()){
            s.nextLine();
            words++;
         }
         s.reset();
         dictionary = new HashTableSC<>(words);
         while(s.hasNextLine()){
            dictionary.add(s.nextLine());
         }
      }
      
   //</editor-fold>
   
   //<editor-fold desc="Methods">
      
      public boolean isWord(String s){
         String s2 = s.toLowerCase();
         return dictionary.contains(s) || dictionary.contains(s2);
      }
      
      public HashTableSC<String> getDictionary(){ return dictionary; }
      
      public HashMap<String, MyStats> processFile(String fileName) throws FileNotFoundException{
         int numWords = 0;
         int lineNum = 0;
         Scanner s = new Scanner(fileName);
         Scanner s_line = null;
         while(s.hasNextLine()){
            s_line = new Scanner(s.nextLine()); 
            s_line.useDelimiter("[^\\w-']+]");
            while(s_line.hasNext()){
               numWords++;
               s_line.next();
            }
         }
         HashMap<String, MyStats> hm = new HashMap<>(numWords);
         s.reset();
         while(s.hasNextLine()){
            lineNum++;
            s_line = new Scanner(s.nextLine());
            s_line.useDelimiter("[^\\w-']+]");
            while(s_line.hasNext()){
               String word = s_line.next();
               if(hm.containsKey(word)){
                  MyStats stats = hm.get(word);
                  stats.occur++;
                  stats.lineNums.add(lineNum);
                  stats.word = isWord(word);
               }
               else{
                  hm.put(word, new MyStats());
               }
            }
         }
         
         throw new RuntimeException("Finish this method");
      }
      
    //</editor-fold>
      
   //<editor-fold desc="Private Inner Class">
      
      public class MyStats{
         
         //<editor-fold desc="Private Fields">
         private List<Integer> lineNums;
         private int occur;
         boolean word;
         //</editor-fold>
         
         //<editor-fold desc="Constructors">
         public MyStats(){
            lineNums = new ArrayList<Integer>();
            occur = 1;
         }
         //</editor-fold>
         
         //<editor-fold desc="Methods">
         //Methods
         public int getOccurences(){ return occur; }
         
         public List<Integer> getLineNumbers(){ return lineNums;}
         
         public boolean isWord(){
            return word;
         }
         //</editor-fold>
      }
   //</editor-fold>
}
