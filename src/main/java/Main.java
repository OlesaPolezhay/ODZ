package main.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

  public static void main(String[] args) throws FileNotFoundException  {

    String [] textFileArray = new String[3];

    textFileArray[0] = "C:\\Users\\lesja\\Desktop\\Забезпечення якості програмних продуктів\\untitled\\src\\main\\java\\text.txt";
    textFileArray[1] = "C:\\Users\\lesja\\Desktop\\Забезпечення якості програмних продуктів\\untitled\\src\\main\\java\\text5.txt";
    textFileArray[2] = "C:\\Users\\lesja\\Desktop\\Забезпечення якості програмних продуктів\\untitled\\src\\main\\java\\text25.txt";

    String filename = "C:\\Users\\lesja\\Desktop\\Забезпечення якості програмних продуктів\\untitled\\src\\main\\java\\result.txt";

    try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      for (String word : textFileArray) {
        writer.write("Аналіз результатів для " + word);
        System.out.println("Аналіз результатів для " + word);

        Cryptographer cryptographer = new Cryptographer();
        cryptographer.printAlphabet();
        cryptographer.printMixUkrAlphabet();

        char[] charArray = readFromFile(word);

        writer.write("\nВхідний текст:\n");
        writer.write(charArray );
        System.out.println("\nВхідний текст:");
        System.out.println(charArray);
        char[] encryptedText = cryptographer.textEncryption(charArray);

        writer.write("\nЗашифрований текст:\n");
        System.out.println("\nЗашифрований текст:");
        writer.write(encryptedText);
        System.out.println(encryptedText);

        Decrypting decrypting = new Decrypting();
        decrypting.countLetters(encryptedText);
        char[] decryptedText = decrypting.descrint(encryptedText);

        writer.write("\nРозшифрований текст: \n");
        decrypting.countLetters(decryptedText);
        System.out.println("\nРозшифрований текст:");
        System.out.println(decryptedText);
        writer.write(decryptedText);

        Analysis analysis = new Analysis();
        analysis.compareCharArrays(charArray, decryptedText);
        System.out.println(analysis.printResultAnalysis());
        writer.write(analysis.printResultAnalysis());
        writer.write("\n\n");
        System.out.println();
    }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static char[] readFromFile (String nameFile ) throws FileNotFoundException {
    File file = new File(nameFile);
    Scanner inputFileText = new Scanner(file);

    StringBuilder stringBuilder = new StringBuilder();
    while (inputFileText.hasNextLine()) {
      stringBuilder.append(inputFileText.nextLine());
      stringBuilder.append("\n");
    }

    String text = stringBuilder.toString();
    char[] charArray = text.toCharArray();

    inputFileText.close();
    return charArray;
  }
}
