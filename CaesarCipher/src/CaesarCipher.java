import java.util.Scanner;

public class CaesarCipher {
    public static void main (String[]args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите текст: ");
        String cipher = scanner.nextLine();
        System.out.print("Введите ключ: ");

        int key = scanner.nextInt();
        while (key > 26){
            key -= 26;
        }

        String encryptedText = encrypt(cipher.toLowerCase(), key);
        System.out.println("Зашифрованный текст:  " + encryptedText);
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Расшифрованный текст: " + decryptedText);

        char[] popularSymbols = {'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd'};
        for (char symbol: popularSymbols) {
            System.out.println("Вариант расшифровки: " + decipher(cipher, symbol));
        }

    }
    public static String encrypt(String text, int key){

        StringBuilder result = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char encryptedChar = (char) (((character - 'a' + key) % 26) + 'a');
                result.append(encryptedChar);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
    public static String decrypt(String encryptedText, int key){
        StringBuilder result = new StringBuilder();
        for (char character : encryptedText.toCharArray()) {
            if (Character.isLetter(character)) {
                char decryptedChar = (char) (((character - 'a' - key + 26) % 26) + 'a');
                result.append(decryptedChar);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public static String decipher(String cipher, char symbol) {

        int[] frequency = new int[26];
        char[] chars = cipher.toCharArray();

        for (char c : chars) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                frequency[c - 'a']++;
            }
        }

        int maxFrequency = 0;
        int maxIndex = 0;

        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > maxFrequency) {
                maxFrequency = frequency[i];
                maxIndex = i;
            }
        }

        int key = maxIndex - (symbol - 'a');

        StringBuilder decryptedText = new StringBuilder();
        for (char c : chars) {
            if (Character.isLetter(c)) {
                boolean isUpperCase = Character.isUpperCase(c);
                c = Character.toLowerCase(c);
                int decryptedChar = c - key - 'a';
                if (decryptedChar < 0) {
                    decryptedChar += 26;
                }
                decryptedChar = decryptedChar % 26 + 'a';
                if (isUpperCase) {
                    decryptedChar = Character.toUpperCase(decryptedChar);
                }
                decryptedText.append((char) decryptedChar);
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

}