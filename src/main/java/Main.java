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
        IDictionary dictionary = new Dictionary();
        dictionary.startProgram();
    }

}