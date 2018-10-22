package mainpack;

import java.util.Arrays;
import java.util.Arrays.*;

public class Polybius implements algorithm {
    public String crypt(String word) {
        String wordLC = word.toLowerCase();
        int[] cryptedWord = new int[wordLC.length()];

        for (int i = 0; i < wordLC.length(); ++i) {
            if (wordLC.charAt(i) < 105 && wordLC.charAt(i) > 96) {
                cryptedWord[i] = ((wordLC.charAt(i) - 'a') / 5 + 1) * 10 + (wordLC.charAt(i) - 'a') % 5 + 1;
            } else if (wordLC.charAt(i) < 107 && wordLC.charAt(i) > 104) {
                cryptedWord[i] = 24;
            } else if (wordLC.charAt(i) < 123 && wordLC.charAt(i) > 106) {
                cryptedWord[i] = ((wordLC.charAt(i) - 'a') / 5 + 1) * 10 + (wordLC.charAt(i) - 'a') % 5;
                if (cryptedWord[i] % 10 == 0)
                    cryptedWord[i] -= 5;
            }
        }

        return Arrays.toString(cryptedWord).replaceAll("\\[|\\s", "").replaceAll("\\]|,|\\s", " ");
    }

    public String decrypt(String word) {
        char[] decryptedWord = new char[word.length() /3];

        for(int i = 0 ; i < word.length(); i += 3) {
            int temp =  (word.charAt(i) - '0') * 10 + (word.charAt(i + 1) - '0');

            if(temp < 24)
                decryptedWord[i / 3] = (char) ('a' + (temp / 10 - 1 ) * 5 + temp % 10 - 1);
            else if (temp == 24)
                decryptedWord[i / 3] = 'i';
            else if (temp > 24)
                decryptedWord[i / 3] = (char) ('a' + (temp / 10 - 1 ) * 5 + temp % 10);
        }

        return Arrays.toString(decryptedWord).replaceAll("\\[|\\]|,|\\s", "");
    }
}
