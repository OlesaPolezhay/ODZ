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
        int resultGap = Character.compare(charArrayChar, ' ');

        if (resultGap == 0 || ( result == 0  && isUkrainianLetter(charArrayChar)))
          analysisOfLetters.put(charArrayChar, true);
        else if (isUkrainianLetter(charArrayChar))
          analysisOfLetters.put(charArrayChar, false);
      }
    }

  public String printResultAnalysis(){
    /*System.out.println("Аналіз результатів: ");
    analysisOfLetters.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

    System.out.println( "Кількість використаних значень: " + analysisOfLetters.size());
    int countTrueValues = countValues(true);
    System.out.println("Кількість значень які співпали: " + countTrueValues);

    int countFalseValues = countValues(false);
    System.out.println("Кількість значень, які не співпали: " + countFalseValues);*/

    StringBuilder result = new StringBuilder();
    result.append("Аналіз результатів: \n");
    analysisOfLetters.forEach((key, value) -> result.append("Key: ").append(key).append(", Value: ").append(value).append("\n"));

    result.append("Кількість використаних значень: ").append(analysisOfLetters.size()).append("\n");
    int countTrueValues = countValues(true);
    result.append("Кількість значень які співпали: ").append(countTrueValues).append("\n");

    int countFalseValues = countValues(false);
    result.append("Кількість значень, які не співпали: ").append(countFalseValues);

    String finalResult = result.toString();
    return finalResult;
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

  public static boolean isUkrainianLetter(char c) {
    return ((c >= '\u0410' && c <= '\u042F') || // прописні українські літери
        (c >= '\u0430' && c <= '\u044F') || // строчні українські літери
        c == 'Ї' || c == 'ї' ||            // українська літера Ї (Ї)
        c == 'І' || c == 'і' ||            // українська літера І (I)
        c == 'Є' || c == 'є' ||            // українська літера Є (Є)
        c == 'Ґ' || c == 'ґ');            // українська літера Ґ (Ґ)
  }
}

