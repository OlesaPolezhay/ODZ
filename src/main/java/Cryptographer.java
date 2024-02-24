package main.java;

import java.util.Random;

public class Cryptographer {

  public Cryptographer() {
    shuffleArray();
  }

  private static final char[] ukrAlphabet = {
      'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й',
      'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч',
      'ш', 'щ', 'ь', 'ю', 'я', ' '
  };

/*  private static final char[] mixUkrAlphabet = {
      'ф', 'у', 'ї', 'б', 'т', 'д', 'м', 'ж', 'о', 'ч', 'г', 'н', 'п', 'а',
      'и', 'х', 'е', 'в', 'ц', 'я', 'ш', 'ґ', 'щ', 'і', 'ь', 'л', 'з', 'й',
      'с', 'ю', 'є', 'р', 'к'
  };*/
  private static final char[] mixUkrAlphabet = new char[ukrAlphabet.length];

  public char[] textEncryption(char[] charArray ){

    char []encryptedText = new char[charArray.length];

    int indexEncryptedText = 0;
    for (char c : charArray) {
      c = Character.toLowerCase(c);
      boolean found = false;
      for (int i = 0; i < ukrAlphabet.length; i++) {
        if (c == ukrAlphabet[i]) {
          encryptedText[indexEncryptedText] = mixUkrAlphabet[i];
          found = true;
          break;
        }
      }
      if (!found)
        encryptedText[indexEncryptedText] = c;
      indexEncryptedText++;
    }

    return encryptedText;
  }

    public  void printAlphabet(){
    System.out.println("Український алфавіт");
    for (char c : ukrAlphabet)
      System.out.print(c + " ");
    }

  public void mixUkrAlphabet(){
    System.out.println("\nПеремішаний укранський алфавіт для шифру");
    for (char c :mixUkrAlphabet)
      System.out.print(c + " ");
  }

    static void shuffleArray() {
    int index;
    char temp;
    Random random = new Random();
      System.arraycopy(ukrAlphabet, 0, mixUkrAlphabet, 0, ukrAlphabet.length);

      for (int i = mixUkrAlphabet.length - 1; i > 0; i--) {
        index = random.nextInt(i + 1);
        temp = mixUkrAlphabet[index];
        mixUkrAlphabet[index] = mixUkrAlphabet[i];
        mixUkrAlphabet[i] = temp;
      }
  }
}
