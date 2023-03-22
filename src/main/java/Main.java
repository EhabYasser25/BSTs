import CLI.Dictionary;
import CLI.IDictionary;
import Service.FileManager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        IDictionary dictionary = new Dictionary();
//        dictionary.startProgram();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int length = 26, max = 1000000, maxFile = 10000;
        Random random = new Random();

        List<String> base = new ArrayList<>();
        for(int j = 0; j < max; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(alphabet.length());
                sb.append(alphabet.charAt(index));
            }
            base.add(sb.toString());
        }

        try {
            FileWriter writer = new FileWriter("base.txt");
            for (String word : base) {
                writer.write(word + "\n"); // write word followed by newline character
            }
            writer.close();
            System.out.println("Word list written to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }

        List<List<String>> lists = new ArrayList<>();
        for(int i = 0; i < max; i+=maxFile) {
            List<String> subList = new ArrayList<>();
            for(int j = 0; j < maxFile; j++) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < length; k++) {
                    int index = random.nextInt(alphabet.length());
                    sb.append(alphabet.charAt(index));
                }
                subList.add(sb.toString());
            }
            lists.add(subList);
        }

        for(int i = 0; i < lists.size(); i++) {
            for(int j = i; j < max / maxFile; j++) {
                try {
                    String string = "target/batch/" + Integer.toString(j + 1);
                    FileWriter writer = new FileWriter(string + ".txt");
                    for (String word : lists.get(i)) {
                        writer.write(word + "\n"); // write word followed by newline character
                    }
                    writer.close();
                    System.out.println("Word list written to file.");
                } catch (IOException e) {
                    System.out.println("An error occurred while writing the file.");
                    e.printStackTrace();
                }
            }
        }

    }
}