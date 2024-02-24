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
    StatisticsOfLetters.put('а', 0.072);
    StatisticsOfLetters.put('ї', 0.006);
    StatisticsOfLetters.put('у', 0.04);
    StatisticsOfLetters.put('б', 0.017);
    StatisticsOfLetters.put('й', 0.008);
    StatisticsOfLetters.put('ф', 0.001);
    StatisticsOfLetters.put('в', 0.052);
    StatisticsOfLetters.put('к', 0.035);
    StatisticsOfLetters.put('х', 0.012);
    StatisticsOfLetters.put('г', 0.016);
    StatisticsOfLetters.put('л', 0.036);
    StatisticsOfLetters.put('ц', 0.006);
    StatisticsOfLetters.put('д', 0.035);
    StatisticsOfLetters.put('м', 0.031);
    StatisticsOfLetters.put('ч', 0.018);
    StatisticsOfLetters.put('е', 0.017);
    StatisticsOfLetters.put('н', 0.065);
    StatisticsOfLetters.put('ш', 0.012);
    StatisticsOfLetters.put('є', 0.008);
    StatisticsOfLetters.put('о', 0.094);
    StatisticsOfLetters.put('щ', 0.001);
    StatisticsOfLetters.put('ж', 0.009);
    StatisticsOfLetters.put('п', 0.029);
    StatisticsOfLetters.put('ю', 0.004);
    StatisticsOfLetters.put('з', 0.023);
    StatisticsOfLetters.put('р', 0.047);
    StatisticsOfLetters.put('я', 0.029);
    StatisticsOfLetters.put('и', 0.061);
    StatisticsOfLetters.put('с', 0.041);
    StatisticsOfLetters.put('ь', 0.029);
    StatisticsOfLetters.put('і', 0.057);
    StatisticsOfLetters.put('т', 0.055);
    //StatisticsOfLetters.put('-', 0.17);

    StatisticsOfLetters = sortByValue(StatisticsOfLetters);

    System.out.println();
    for (Entry<Character, Double> entry : StatisticsOfLetters.entrySet()) {
      System.out.print(entry.getKey() + ": " + entry.getValue() + " ");
    }
    System.out.println();
  }
  public void countLetters(char[] chars) {

    letterCount = new HashMap<>();

    for (char c : chars) {
      if (Character.isLetter(c)) {
        c = Character.toLowerCase(c);
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

    char[][] resultMatrixLetter = new char[2][StatisticsOfLetters.size()];

    int index = 0;
    for (char letter : StatisticsOfLetters.keySet())
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
