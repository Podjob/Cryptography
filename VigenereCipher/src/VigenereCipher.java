import java.util.Scanner;

public class VigenereCipher {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите текст: ");
        String cipherTextScan = scanner.nextLine();
        System.out.print("Введите ключ: ");
        String keyScan = scanner.nextLine();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";


        String encrypt = encrypt(cipherTextScan, keyScan, alphabet).toString();
        System.out.println(encrypt);
        System.out.println(decrypt(encrypt, keyScan, alphabet));
    }

    public static StringBuilder encrypt(String cipherText, String key, String alphabet){
        StringBuilder keySequence = new StringBuilder();

        StringBuilder cipherTextLowerCase = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            if((int) cipherText.charAt(i) <= 90){
                cipherTextLowerCase.append(cipherText.toLowerCase().charAt(i));
            }else {
                cipherTextLowerCase.append(cipherText.charAt(i));
            }
        }

        for (int i = 0, numSymbolKey = 0; i < cipherTextLowerCase.length(); i++) {
            if(numSymbolKey == key.length()){
                numSymbolKey = 0;
            }
            if(alphabet.indexOf(cipherTextLowerCase.charAt(i)) > -1){
                keySequence.append(key.charAt(numSymbolKey));
                numSymbolKey++;
            }else if(cipherTextLowerCase.charAt(i) == ' '){
                keySequence.append(' ');
            }else{
                keySequence.append('0');
            }
        }

        StringBuilder encryptText = new StringBuilder();

        for (int i = 0; i < keySequence.length(); i++) {
            if(alphabet.indexOf(keySequence.charAt(i)) > -1){
                int numSymbolAlphabet = alphabet.indexOf(keySequence.charAt(i));
                int numSymbolCipherText = alphabet.indexOf(cipherTextLowerCase.charAt(i));
                int shift = numSymbolCipherText + numSymbolAlphabet;
                if(shift >= 26){
                    shift -= 26;
                }
                encryptText.append(alphabet.charAt(shift));
            }else if(keySequence.charAt(i) == ' '){
                encryptText.append(' ');
            }else if(keySequence.charAt(i) == '0'){
                encryptText.append(cipherTextLowerCase.charAt(i));
            }
        }
        return encryptText;
    }

    public static StringBuilder decrypt(String cipherText, String key, String alphabet){
        StringBuilder keySequence = new StringBuilder();

        StringBuilder cipherTextLowerCase = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            if((int) cipherText.charAt(i) <= 90){
                cipherTextLowerCase.append(cipherText.toLowerCase().charAt(i));
            }else {
                cipherTextLowerCase.append(cipherText.charAt(i));
            }
        }

        for (int i = 0, numSymbolKey = 0; i < cipherTextLowerCase.length(); i++) {
            if(numSymbolKey == key.length()){
                numSymbolKey = 0;
            }
            if(alphabet.indexOf(cipherTextLowerCase.charAt(i)) > -1){
                keySequence.append(key.charAt(numSymbolKey));
                numSymbolKey++;
            }else if(cipherTextLowerCase.charAt(i) == ' '){
                keySequence.append(' ');
            }else{
                keySequence.append('0');
            }
        }

        StringBuilder decryptText = new StringBuilder();

        for (int i = 0; i < keySequence.length(); i++) {
            if(alphabet.indexOf(keySequence.charAt(i)) > -1){
                int numSymbolAlphabet = alphabet.indexOf(keySequence.charAt(i));
                int numSymbolCipherText = alphabet.indexOf(cipherTextLowerCase.charAt(i));
                int shift = numSymbolCipherText - numSymbolAlphabet;
                if(shift < 0){
                    shift += 26;
                }
                decryptText.append(alphabet.charAt(shift));
            }else if(keySequence.charAt(i) == ' '){
                decryptText.append(' ');
            }else if(keySequence.charAt(i) == '0'){
                decryptText.append(cipherTextLowerCase.charAt(i));
            }
        }
        return decryptText;
    }

//    Hey! It's me! Ehehe~ I know you spent a long time trying yo find this huh? Or maybe not!
//    mpkkirby
}