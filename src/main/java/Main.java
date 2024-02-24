package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Main {


  static HashMap<Character, Double> StatisticsOfLetters ;

  public static void main(String[] args) throws FileNotFoundException  {

    Cryptographer cryptographer = new Cryptographer();
    cryptographer.printAlphabet();
    cryptographer.mixUkrAlphabet();


    char [] charArray = readFromFile("C:\\Users\\lesja\\Desktop\\Забезпечення якості програмних продуктів\\"
        + "untitled\\src\\main\\java\\text25.txt");


    System.out.println("\nВхідний текст:");
    System.out.println(charArray);
    char[] encryptedText = cryptographer.textEncryption(charArray);

    System.out.println("\nЗашифрований текст:");
    System.out.println(encryptedText);

    Decrypting decrypting = new Decrypting();
    decrypting.countLetters(encryptedText);
    char[] decryptedText = decrypting.descrint(encryptedText);

    System.out.println("\nРозшифрований текст:");
    System.out.println(decryptedText);

    Analysis analysis = new Analysis();
    analysis.compareCharArrays(charArray, decryptedText);
    analysis.printResultAnalysis();

  }

  public static void printArrayChar(char[] array){
    System.out.println();
    for (char c : array) {
      System.out.print(c);
    }
  }

  static char[] readFromFile (String nameFile ) throws FileNotFoundException {
    File file = new File(nameFile);
    Scanner inputFileText = new Scanner(file);

    StringBuilder stringBuilder = new StringBuilder();
    while (inputFileText.hasNextLine()) {
      stringBuilder.append(inputFileText.nextLine()); // зчитати рядок
      stringBuilder.append("\n"); // додати символ нового рядка
    }

    String text = stringBuilder.toString();
    char[] charArray = text.toCharArray();

    inputFileText.close();
    return charArray;
  }
}
