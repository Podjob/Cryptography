import java.util.Scanner;

public class VigenereCipher {

// группа ВМ-41
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите текст: ");
        String cipherTextScan = scanner.nextLine();
        System.out.print("Введите ключ: ");
        String keyScan = scanner.nextLine();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        String cipherTextLowerCase = textLowerCase(cipherTextScan).toString();
        String keySequence = keySequenceString(keyScan, alphabet, cipherTextLowerCase).toString();
        String encrypt = encrypt(alphabet, keySequence, cipherTextLowerCase).toString();
        System.out.println(encrypt);
        System.out.println(decrypt(alphabet, keySequence, encrypt));
    }

    public static StringBuilder textLowerCase(String text){
        StringBuilder cipherTextLowerCase = new StringBuilder();
        cipherTextLowerCase.append(text.toLowerCase());
        return cipherTextLowerCase;
    }

    public static StringBuilder keySequenceString(String key, String alphabet, String cipherTextLowerCase){

        StringBuilder keySequence = new StringBuilder();

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

        return keySequence;
    }

    public static StringBuilder encrypt(String alphabet, String keySequence, String cipherTextLowerCase){

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

    public static StringBuilder decrypt(String alphabet, String keySequence, String encrypt){

        StringBuilder decryptText = new StringBuilder();

        for (int i = 0; i < keySequence.length(); i++) {
            if(alphabet.indexOf(keySequence.charAt(i)) > -1){
                int numSymbolAlphabet = alphabet.indexOf(keySequence.charAt(i));
                int numSymbolCipherText = alphabet.indexOf(encrypt.charAt(i));
                int shift = numSymbolCipherText - numSymbolAlphabet;
                if(shift < 0){
                    shift += 26;
                }
                decryptText.append(alphabet.charAt(shift));
            }else if(keySequence.charAt(i) == ' '){
                decryptText.append(' ');
            }else if(keySequence.charAt(i) == '0'){
                decryptText.append(encrypt.charAt(i));
            }
        }
        return decryptText;
    }

//   текст                 : Hey! It's me! Ehehe~ I know you spent a long time trying yo find this huh? Or maybe not!
//   ключ                  : mpkkirby
//   преобразованный текст : tti! sb'j nc! qworm~ z llal iyc jqczi k vweh rubo dzpjls ny pqee rtxc rcy? pp ypilm epr!
    
}
