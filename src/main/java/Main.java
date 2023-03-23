import CLI.Dictionary;
import CLI.IDictionary;
import Service.FileManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Service.FileManager.writeToFile;

public class Main {

    public static void main(String[] args) throws IOException {
//        IDictionary dictionary = new Dictionary();
//        dictionary.startProgram();

        // 100000 words base
        ArrayList<String> base = randomString(100000, 26);
        writeToFile(base, "target/batch/base.txt");

        // 200 points, difference in number of words in each two consecutive files = 500
        for(int i = 0; i < 200; i++) {
            ArrayList<String> file = randomString( 500 * (i + 1), 26);
            writeToFile(file, "target/batch/" + Integer.toString(i + 1) + ".txt");
        }

    }

    public static ArrayList<String> randomString(int max, int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        ArrayList<String> base = new ArrayList<>();
        for(int j = 0; j < max; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(alphabet.length());
                sb.append(alphabet.charAt(index));
            }
            base.add(sb.toString());
        }
        return base;
    }
}