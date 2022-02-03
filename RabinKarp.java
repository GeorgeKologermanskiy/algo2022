import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RabinKarp {

    public static void main(String[] args) throws IOException {

        long p = 29;
        long M = (long) (1e9 + 7);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String text = in.readLine();
        String pattern = in.readLine();

        long pn = 1;

        // calculate pattern hash
        long hashPattern = 0;
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            hashPattern = (hashPattern * p + (c - 'a' + 1)) % M;
            pn = (pn * p) % M;
        }

        // calculate text hashes and compare
        long hashText = 0;
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            hashText = (hashText * p + (c - 'a' + 1)) % M;


            if (i >= pattern.length()) {
                long lastSymbol = ((text.charAt(i - pattern.length()) - 'a' + 1) * pn) % M;
                hashText = (hashText - lastSymbol + M) % M;
            }

            if (hashPattern == hashText) {
                System.out.print(i - pattern.length() + 1);
                System.out.print(" ");
            }
        }


        System.out.println();
    }

}

