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
  static private HashMap<Character, Double> statisticsOfLetters;
  static private HashMap<Character, Integer> letterCount;

  public Decrypting() {
    statisticsOfUkrLetters();
  }

  public void statisticsOfUkrLetters(){

    statisticsOfLetters = new HashMap<>();
    statisticsOfLetters.put('e', 11.1607);
    statisticsOfLetters.put('a', 8.4966);
    statisticsOfLetters.put('r', 7.5809);
    statisticsOfLetters.put('i', 7.5448);
    statisticsOfLetters.put('o', 7.1635);
    statisticsOfLetters.put('t', 6.9509);
    statisticsOfLetters.put('n', 6.6544);
    statisticsOfLetters.put('s', 5.7351);
    statisticsOfLetters.put('l', 5.4893);
    statisticsOfLetters.put('c', 4.5388);
    statisticsOfLetters.put('u', 3.6308);
    statisticsOfLetters.put('d', 3.3844);
    statisticsOfLetters.put('p', 3.1671);
    statisticsOfLetters.put('m', 3.0129);
    statisticsOfLetters.put('h', 3.0034);
    statisticsOfLetters.put('g', 2.4705);
    statisticsOfLetters.put('b', 2.0720);
    statisticsOfLetters.put('f', 1.8121);
    statisticsOfLetters.put('y', 1.7779);
    statisticsOfLetters.put('w', 1.2899);
    statisticsOfLetters.put('k', 1.1016);
    statisticsOfLetters.put('v', 1.0074);
    statisticsOfLetters.put('x', 0.2902);
    statisticsOfLetters.put('z', 0.2722);
    statisticsOfLetters.put('j', 0.1965);
    statisticsOfLetters.put('q', 0.1962);
    statisticsOfLetters.put(' ', 100.17);


    statisticsOfLetters = sortByValue(statisticsOfLetters);

    System.out.println();
    for (Entry<Character, Double> entry : statisticsOfLetters.entrySet()) {
      System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
    }
    System.out.println();
  }
  public void countLetters(char[] chars) {

    letterCount = new HashMap<>();

    for (char c : chars) {
      int resultGap = Character.compare(c, ' ');
      if (Character.isLetter(c)) {
        c = Character.toLowerCase(c);
        letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
      } else if(resultGap == 0){
        letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
      }
    }
    letterCount = sortByValue(letterCount);

    // розрахунок літер у зашифрованому тексті
    System.out.println();
    letterCount.forEach((key, value) -> System.out.print( key + ": " + value + " "));
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

    char[][] resultMatrixLetter = new char[2][statisticsOfLetters.size()];

    int index = 0;
    for (char letter : statisticsOfLetters.keySet())
      resultMatrixLetter[0][index++] = letter;

    index = 0;
    for (char letter : letterCount.keySet())
      resultMatrixLetter[1][index++] = letter;

// Виведення матриці
    System.out.println();
    for (char[] chars : resultMatrixLetter) {
      for (char aChar : chars) {
        System.out.print(aChar + " ");
      }
      System.out.println();
    }

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
