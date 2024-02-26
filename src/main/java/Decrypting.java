package main.java;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Decrypting {
  static private HashMap<Character, Double> StatisticsOfLetters;
  static private HashMap<Character, Integer> letterCount;

  public Decrypting() {
    statisticsOfUkrLetters();
  }

  public void statisticsOfUkrLetters(){

    StatisticsOfLetters = new HashMap<>();
    StatisticsOfLetters.put('а', 0.0807);
    StatisticsOfLetters.put('ї', 0.0065);
    StatisticsOfLetters.put('у', 0.0336);
    StatisticsOfLetters.put('б', 0.0176);
    StatisticsOfLetters.put('й', 0.0138);
    StatisticsOfLetters.put('ф', 0.0028);
    StatisticsOfLetters.put('в', 0.0535);
    StatisticsOfLetters.put('к', 0.0354);
    StatisticsOfLetters.put('х', 0.0119);
    StatisticsOfLetters.put('г', 0.0155);
    StatisticsOfLetters.put('л', 0.0369);
    StatisticsOfLetters.put('ц', 0.0083);
    StatisticsOfLetters.put('д', 0.0338);
    StatisticsOfLetters.put('м', 0.0303);
    StatisticsOfLetters.put('ч', 0.0141);
    StatisticsOfLetters.put('е', 0.0495);
    StatisticsOfLetters.put('н', 0.0681);
    StatisticsOfLetters.put('ш', 0.0076);
    StatisticsOfLetters.put('є', 0.0061);
    StatisticsOfLetters.put('о', 0.0942);
    StatisticsOfLetters.put('щ', 0.0056);
    StatisticsOfLetters.put('ж', 0.0092);
    StatisticsOfLetters.put('п', 0.0290);
    StatisticsOfLetters.put('ю', 0.0093);
    StatisticsOfLetters.put('з', 0.0232);
    StatisticsOfLetters.put('р', 0.0448);
    StatisticsOfLetters.put('я', 0.0248);
    StatisticsOfLetters.put('и', 0.0626);
    StatisticsOfLetters.put('с', 0.0421);
    StatisticsOfLetters.put('ь', 0.0177);
    StatisticsOfLetters.put('і', 0.0575);
    StatisticsOfLetters.put('т', 0.0534);
    StatisticsOfLetters.put(' ', 0.17);

    StatisticsOfLetters = sortByValue(StatisticsOfLetters);

   /* System.out.println();
    for (Entry<Character, Double> entry : StatisticsOfLetters.entrySet()) {
      System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
    }
    System.out.println();*/
  }
  public void countLetters(char[] chars) {

    letterCount = new HashMap<>();

    for (char c : chars) {
      int resultGap = Character.compare(c, ' ');
      if (Character.isLetter(c) && StatisticsOfLetters.containsKey(c)) {
        c = Character.toLowerCase(c);
        letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
      } else if(resultGap == 0){
        letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
      }
    }
    letterCount = sortByValue(letterCount);

    // розрахунок літер у зашифрованому тексті
 /*   System.out.println();
    letterCount.forEach((key, value) -> System.out.print( key + ": " + value + " "));*/
  }
  public static <T extends Comparable<? super T>> LinkedHashMap<Character, T> sortByValue(HashMap<Character, T> map) {
    List<Entry<Character, T>> list = new LinkedList<>(map.entrySet());

    Collections.sort(list, new Comparator<Entry<Character, T>>() {
      @Override
      public int compare(Map.Entry<Character, T> o1, Map.Entry<Character, T> o2) {
        return o2.getValue().compareTo(o1.getValue());
      }
    });

    LinkedHashMap<Character, T> sortedMap = new LinkedHashMap<>();
    for (Map.Entry<Character, T> entry : list) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    return sortedMap;
  }

  public char[] descrint(char[] charArray){

    char[][] resultMatrixLetter = new char[2][StatisticsOfLetters.size()];

    int index = 0;
    for (char letter : StatisticsOfLetters.keySet())
      resultMatrixLetter[0][index++] = letter;

    index = 0;
    for (char letter : letterCount.keySet())
      resultMatrixLetter[1][index++] = letter;

// Виведення матриці
/*    System.out.println();
    for (char[] chars : resultMatrixLetter) {
      for (char aChar : chars) {
        System.out.print(aChar + " ");
      }
      System.out.println();
    }*/

// Дешифрування
    char[] decryptingText = new char[charArray.length];
    int indexDecryptingText = 0;
    for (char c : charArray) {
      c = Character.toLowerCase(c);
      boolean found = false;
      // Пошук літери у матриці
      for (int i = 0; i < letterCount.size(); i++) {
        if ( Character.compare(c, resultMatrixLetter[1][i]) == 0 ){
          decryptingText[indexDecryptingText] = resultMatrixLetter[0][i];
          found = true;
          break;
        }
      }
      if (!found)
        decryptingText[indexDecryptingText] = c;
      indexDecryptingText++;
    }

    return decryptingText;
  }
}
