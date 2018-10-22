package mainpack;

import java.util.Arrays;
import java.util.Arrays.*;

public class ROT11 implements algorithm {
    public String crypt(String word) {
        char[] cryptedWord = new char[word.length()];

        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) > 96 && word.charAt(i) < 123)
                cryptedWord[i] = (char) (((word.charAt(i) + 11) % 'a') % 26 + 'a');
            else if (word.charAt(i) > 64 && word.charAt(i) < 91)
                cryptedWord[i] = (char) (((word.charAt(i) + 11) % 'A') % 26 + 'A');
            else
                cryptedWord[i] = word.charAt(i);
        }

       return Arrays.toString(cryptedWord).replaceAll("\\[|\\]|,|\\s", "");
    }

    public String decrypt(String word) {
        char[] decryptedWord = new char[word.length()];

        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) > 96 && word.charAt(i) < 123)
                decryptedWord[i] = (char) (((word.charAt(i) - 11) % 'a') % 26 + 'a');
            else if (word.charAt(i) > 64 && word.charAt(i) < 91)
                decryptedWord[i] = (char) (((word.charAt(i) - 11) % 'A') % 26 + 'A');
            else
                decryptedWord[i] = word.charAt(i);
        }

        return Arrays.toString(decryptedWord).replaceAll("\\[|\\]|,|\\s", "");
    }
}
