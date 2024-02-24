package main.java;
import java.util.HashMap;

public class Analysis {

  static HashMap<Character, Boolean> analysisOfLetters = new HashMap<>();

    public  void compareCharArrays(char[] charArray, char[] decryptedText){

      if (charArray.length != decryptedText.length) {
        throw new IllegalArgumentException("Arrays must have the same length");
      }

      for (int i = 0; i < charArray.length; i++) {
        char charArrayChar = Character.toLowerCase(charArray[i]);
        char decryptedTextChar = Character.toLowerCase(decryptedText[i]);
        int result =  Character.compare(charArrayChar, decryptedTextChar);

        if (Character.isLetter(charArrayChar) && result == 0 )
          analysisOfLetters.put(charArrayChar, true);
        else if (Character.isLetter(charArrayChar))
          analysisOfLetters.put(charArrayChar, false);
      }
    }

  public void printResultAnalysis(){
    System.out.println("Аналіз результатів: ");
    analysisOfLetters.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

    System.out.println( "Кількість використаних значень: " + analysisOfLetters.size());
    int countTrueValues = countValues(true);
    System.out.println("Кількість значень які співпали: " + countTrueValues);

    int countFalseValues = countValues(false);
    System.out.println("Кількість значень, які не співпали: " + countFalseValues);
  }

  public static int countValues(boolean valueToCount) {
    int count = 0;
    for (boolean value : analysisOfLetters.values()) {
      if (value == valueToCount) {
        count++;
      }
    }
    return count;
  }
}

