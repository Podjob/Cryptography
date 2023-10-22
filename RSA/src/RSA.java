import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;

    public static void main(String[] args) {
        RSA rsa = new RSA(1024);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите сообщение: ");
        BigInteger message = scanner.nextBigInteger();

        System.out.println("Исходное сообщение      : " + message);

        BigInteger encryptedMessage = rsa.encrypt(message);
        System.out.println("Зашифрованное сообщение : " + encryptedMessage);

        BigInteger decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Расшифрованное сообщение: " + decryptedMessage);
    }

    public RSA(int bitLength) {
        generateKeys(bitLength);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    public BigInteger decrypt(BigInteger encryptedMessage) {
        return encryptedMessage.modPow(d, n);
    }

    public void generateKeys(int bitLength) {
        Random random = new Random();
        p = BigInteger.probablePrime(bitLength / 2, random);
        q = BigInteger.probablePrime(bitLength / 2, random);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        do {
            e = new BigInteger(phi.bitLength(), random);
        } while (e.compareTo(BigInteger.ONE) <= 0 || e.compareTo(phi) >= 0 || !e.gcd(phi).equals(BigInteger.ONE));

        d = e.modInverse(phi);
    }
}

