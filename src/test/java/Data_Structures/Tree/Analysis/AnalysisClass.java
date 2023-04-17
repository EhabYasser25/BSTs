package Data_Structures.Tree.Analysis;

import Data_Structures.Tree.AVL;
import Data_Structures.Tree.BST;
import Data_Structures.Tree.HelpingClasses.TreeCloner;
import Data_Structures.Tree.RB;
import Service.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class AnalysisClass {

    public int[] randomInt(int maxInt) {
        Random rand = new Random();
        int[] randomInts = new int[maxInt];

        for (int i = 0; i < randomInts.length; i++) {
            randomInts[i] = rand.nextInt();
        }

        return randomInts;
    }

    public static <T> long measureRunningTime(Consumer<T> function, T argument) {
        long startTime = System.nanoTime();
        function.accept(argument);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    @Test
    void comparison_singleInsertion() throws CloneNotSupportedException, IOException {
        BST<Integer> red_black = new RB<>();
        BST<Integer> AVL = new AVL<>();
        TreeCloner<Integer> rb_cloner = new TreeCloner<>(red_black);
        TreeCloner<Integer> avl_cloner = new TreeCloner<>(AVL);
        List<Long> rb_insertionTimes = new ArrayList<>();
        List<Long> avl_insertionTimes = new ArrayList<>();

        int[] inputValues = this.randomInt(10000);

        BST<Integer> rb_clone = null;
        BST<Integer> AVL_clone = null;

        long mean_redBlack_InsertTime = 0, mean_AVL_InsertTime = 0;

        for(int j = 0; j < inputValues.length; j++){
            mean_redBlack_InsertTime = 0;
            mean_AVL_InsertTime = 0;

            for(int k = j; k < inputValues.length ; k++){
                rb_clone = rb_cloner.getClone();
                AVL_clone = avl_cloner.getClone();

                mean_redBlack_InsertTime += measureRunningTime(rb_clone :: insert, inputValues[k]);
                mean_AVL_InsertTime += measureRunningTime(AVL_clone :: insert, inputValues[k]);

            }

            mean_redBlack_InsertTime /= inputValues.length - j;
            mean_AVL_InsertTime /= inputValues.length - j;

            rb_insertionTimes.add(mean_redBlack_InsertTime);
            avl_insertionTimes.add(mean_AVL_InsertTime);

            red_black.insert(inputValues[j]);
            AVL.insert(inputValues[j]);
        }

        FileManager.writeLongToFile(rb_insertionTimes, "target/other_analysis_data/Red-Black insertion time values.txt");
        FileManager.writeLongToFile(avl_insertionTimes, "target/other_analysis_data/AVL insertion time values.txt");
    }

    @Test
    void comparison_singleDeletion() throws CloneNotSupportedException, IOException{
        BST<Integer> red_black = new RB<>();
        BST<Integer> AVL = new AVL<>();
        TreeCloner<Integer> rb_cloner = new TreeCloner<>(red_black);
        TreeCloner<Integer> avl_cloner = new TreeCloner<>(AVL);
        List<Long> rb_deletionTimes = new ArrayList<>();
        List<Long> avl_deletionTimes = new ArrayList<>();
        int[] inputValues = this.randomInt(1000000);

        for(int i : inputValues){
            red_black.insert(i);
            AVL.insert(i);
        }

        BST<Integer> rb_clone = null;
        BST<Integer> AVL_clone = null;

        long mean_redBlack_DeleteTime = 0, mean_AVL_DeleteTime = 0;

        for(int j = 0; j < inputValues.length; j++){
            mean_redBlack_DeleteTime = 0;
            mean_AVL_DeleteTime = 0;

            for(int k = j; k < inputValues.length ; k++){
                rb_clone = rb_cloner.getClone();
                AVL_clone = avl_cloner.getClone();

                mean_redBlack_DeleteTime += measureRunningTime(rb_clone :: delete, inputValues[k]);
                mean_AVL_DeleteTime += measureRunningTime(AVL_clone :: delete, inputValues[k]);

            }

            mean_redBlack_DeleteTime /= inputValues.length - j;
            mean_AVL_DeleteTime /= inputValues.length - j;

            rb_deletionTimes.add(mean_redBlack_DeleteTime);
            avl_deletionTimes.add(mean_AVL_DeleteTime);

            red_black.delete(inputValues[j]);
            AVL.delete(inputValues[j]);
        }

        FileManager.writeLongToFile(rb_deletionTimes, "target/other_analysis_data/Red-Black deletion time values.txt");
        FileManager.writeLongToFile(avl_deletionTimes, "target/other_analysis_data/AVL deletion time values.txt");
    }

    @Test
    void comparison_search() throws CloneNotSupportedException, IOException{
        BST<Integer> red_black = new RB<>();
        BST<Integer> AVL = new AVL<>();
        List<Long> rb_searchTimes = new ArrayList<>();
        List<Long> avl_searchTimes = new ArrayList<>();
        int[] inputValues = this.randomInt(1000000);

        red_black.insert(inputValues[0]);
        AVL.insert(inputValues[0]);

        long mean_redBlack_SearchTime = 0, mean_AVL_SearchTime = 0;

        for(int j = 1; j < inputValues.length; j++){
            mean_redBlack_SearchTime = 0;
            mean_AVL_SearchTime = 0;

            for(int k = 0; k < j ; k++){
                mean_redBlack_SearchTime += measureRunningTime(red_black :: search, inputValues[k]);
                mean_AVL_SearchTime += measureRunningTime(AVL :: search, inputValues[k]);
            }

            mean_redBlack_SearchTime /= j;
            mean_AVL_SearchTime /= j;

            rb_searchTimes.add(mean_redBlack_SearchTime);
            avl_searchTimes.add(mean_AVL_SearchTime);

            red_black.insert(inputValues[j]);
            AVL.insert(inputValues[j]);
        }
        FileManager.writeLongToFile(rb_searchTimes, "target/other_analysis_data/Red-Black search time values.txt");
        FileManager.writeLongToFile(avl_searchTimes, "target/other_analysis_data/AVL search time values.txt");
    }

    @Test
    void comparison_size() throws CloneNotSupportedException, IOException{
        BST<Integer> red_black = new RB<>();
        BST<Integer> AVL = new AVL<>();
        List<Long> rb_sizeTimes = new ArrayList<>();
        List<Long> avl_sizeTimes = new ArrayList<>();
        int[] inputValues = this.randomInt(1000000);

        long redBlack_SizeTime = 0, AVL_SizeTime = 0, startTime = 0, endTime = 0;

        int rbSize = 0, avlSize = 0;
        for (int inputValue : inputValues) {
            redBlack_SizeTime = 0;
            AVL_SizeTime = 0;

            startTime = System.nanoTime();
            red_black.getSize();
            endTime = System.nanoTime();
            redBlack_SizeTime += endTime - startTime;
            startTime = System.nanoTime();
            AVL.getSize();
            endTime = System.nanoTime();
            AVL_SizeTime += endTime - startTime;

            rb_sizeTimes.add(redBlack_SizeTime);
            avl_sizeTimes.add(AVL_SizeTime);
            red_black.insert(inputValue);
            AVL.insert(inputValue);
        }
        FileManager.writeLongToFile(rb_sizeTimes, "target/other_analysis_data/Red-Black size time values.txt");
        FileManager.writeLongToFile(avl_sizeTimes, "target/other_analysis_data/AVL size time values.txt");
    }

    @Test
    void height_time_comparison() throws IOException{
        BST<String> rb = new RB<>();
        BST<String> avl = new AVL<>();
        List<Long> rb_heightTimes = new ArrayList<>();
        List<Long> avl_heightTimes = new ArrayList<>();
        BatchInsert insert = new BatchInsert();
        FileManager reader = new FileManager();

        long rbTime, avlTime, start, end;

        for (int i = 0; i < 200; i++) {
            // get the next 500 words
            List<String> added_words = reader.readFile("target/other_analysis_data/" + Integer.toString(i + 1) + ".txt");
            // add them
            insert.batchInsert(avl, added_words);
            insert.batchInsert(rb,added_words);

            // AVL time
            start = System.nanoTime();
            avl.getHeight();
            end = System.nanoTime();
            avlTime = (end - start) / 1000;

            // RB time
            start = System.nanoTime();
            rb.getHeight();
            end = System.nanoTime();
            rbTime = (end - start) / 1000;

            avl_heightTimes.add(avlTime);
            rb_heightTimes.add(rbTime);
        }
        FileManager.writeLongToFile(avl_heightTimes, "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\avl_time_height.txt");
        FileManager.writeLongToFile(rb_heightTimes, "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\rb_time_height.txt");
    }

    // Generate tree of base baseCount words, difference between each teo consecutive files difference, length of word is length, number of files generated is points
    public void randomWordsGenerator(int baseCount, int difference, int length, int points) throws IOException {
        ArrayList<String> base = randomString(baseCount, length);
        FileManager.writeStringToFile(base, "target/deleteData/base.txt");

        // 200 points, difference in number of words in each two consecutive files = 500
        for(int i = 0; i < points; i++) {
            ArrayList<String> file = randomString( difference * (i + 1), length);
            FileManager.writeStringToFile(file, "target/deleteData/" + Integer.toString(i + 1) + ".txt");
        }
    }

    public ArrayList<String> randomString(int max, int length) {
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

    @Test
    void insert_data_formatter() throws IOException {
        // 200 --> number of files created, 26 --> length of one word, 500 --> number of words in each file
       for(int i = 0; i < 200; i++) {
           List<String> list;
           list = randomString(500, 26);
           FileManager.writeStringToFile(list, "target/other_analysis_data/" + Integer.toString(i + 1) + ".txt");
       }
    }

    @Test
    void delete_data_formatter() throws IOException {
        randomWordsGenerator(100000, 500, 26, 200);
    }

    @Test
    void batch_data_formatter() throws IOException {
        // called here to control parameters
        randomWordsGenerator(1000000, 1000, 26, 300);
    }

    @Test
    void auxiliaryData() throws IOException {
        List<Integer> x = new ArrayList<>();
        List<Integer> xD = new ArrayList<>();
        List<Long> log = new ArrayList<>();
        List<Long> logD = new ArrayList<>();
        List<Long> logI = new ArrayList<>();
        List<Integer> H = new ArrayList<>();
        List<Integer> xIsConst = new ArrayList<>();

        // batch operations
        for(int i = 1; i <= 300; i++) {
            x.add(i * 1000);
            // this should be i * 1000 to be exactly m, but it's adjusted like that
            log.add((long) Math.log(1000000) * i * 150);
        }

        // normal operations
        for(int i = 1; i <= 200; i++){
            // It should be log(i * 500) to be log(n), but it's adjusted like that
            logI.add((long) Math.log(i * 500) * 250);
            xD.add(i * 500);
            logD.add((long) Math.log(i * 500) * 175);
            H.add(i * 15);
            xIsConst.add(4);
        }

        FileManager.writeIntToFile(x, "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\x_data.txt");
        FileManager.writeLongToFile(log, "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\insert_complexity.txt");
        FileManager.writeLongToFile(logI, "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\logI.txt");
        FileManager.writeIntToFile(xD, "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\x_normal.txt");
        FileManager.writeLongToFile(logD, "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\logD.txt");
        FileManager.writeIntToFile(H, "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\H.txt");
        FileManager.writeIntToFile(xIsConst, "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\Const.txt");
    }

    @Test
    void batch_insert_delete_analysis() {
        AnalysisClass analysisClass = new AnalysisClass();
        try {
            analysisClass.batch_insert_time_comparison(300, 1000);
            analysisClass.batch_delete_time_comparison(300, 1000);
        } catch (CloneNotSupportedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void normal_insertion_deletion_analysis() {
        AnalysisClass analysisClass = new AnalysisClass();
        try {
            analysisClass.delete_time_comparison(100, 500);
            analysisClass.insert_time_comparison(100, 500);
        } catch (CloneNotSupportedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void batch_insert_time_comparison(int points, int difference) throws CloneNotSupportedException, IOException {
        BST<String> red_black = new RB<>();
        BST<String> avl = new AVL<>();
        CommandInvoker commands = new CommandInvoker();
        BatchInsert insert = (BatchInsert) commands.invoke(Commands.BATCHINSERT);

        FileManager reader = new FileManager();
        List<String> initial_words_in_tree = reader.readFile("target/batch/base.txt");

        //each tree will be initialized by base words
        insert.batchInsert(avl, initial_words_in_tree);
        insert.batchInsert(red_black, initial_words_in_tree);

        TreeCloner<String> rb_cloner = new TreeCloner<>(red_black);
        TreeCloner<String> avl_cloner = new TreeCloner<>(avl);

        AVL<String> avl_clone;
        RB<String> rb_clone;

        // to calculate time
        long start_avl_batchInsertTime , end_avl_batchInsertTime, finish_avl_batchInsertTime;
        long start_rb_batchInsertTime , end_rb_batchInsertTime, finish_rb_batchInsertTime;
        List<Long> avl_batchInsertTimes = new ArrayList<>();
        List<Long> rb_batchInsertTimes = new ArrayList<>();
        List<Integer> heightAVL = new ArrayList<>();
        List<Integer> heightRB = new ArrayList<>();

        // Here, we will test the batch insert on the two types of trees by adding
        // word or 2 words or ... to the tree which have 1000 words and the base tree isn't affected by the insertion
        for(int i = 1 ; i <= points ; i++){
            List<String> added_words = reader.readFile("target/batch/" + Integer.toString(i) + ".txt");
            avl_clone = (AVL<String>) avl_cloner.getClone();
            rb_clone = (RB<String>) rb_cloner.getClone();

            start_avl_batchInsertTime =  System.nanoTime();
            insert.batchInsert(avl_clone,added_words);
            end_avl_batchInsertTime =  System.nanoTime();
            finish_avl_batchInsertTime = end_avl_batchInsertTime - start_avl_batchInsertTime;

            start_rb_batchInsertTime =  System.nanoTime();
            insert.batchInsert(rb_clone,added_words);
            end_rb_batchInsertTime =  System.nanoTime();
            finish_rb_batchInsertTime = end_rb_batchInsertTime - start_rb_batchInsertTime;

            avl_batchInsertTimes.add(finish_avl_batchInsertTime);
            rb_batchInsertTimes.add(finish_rb_batchInsertTime);
            heightAVL.add(avl_clone.getHeight());
            heightRB.add(rb_clone.getHeight());
        }
        String timePathAVL = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\AVL_Batch_Insert_Time.txt";
        String timePathRB = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\RB_Batch_Insert_Time.txt";
        String heightPathAVL = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\AVL_Height_insert.txt";
        String heightPathRB = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\RB_Height_insert.txt";
        FileManager.writeLongToFile(avl_batchInsertTimes, timePathAVL);
        FileManager.writeLongToFile(rb_batchInsertTimes, timePathRB);
        FileManager.writeIntToFile(heightAVL, heightPathAVL);
        FileManager.writeIntToFile(heightRB, heightPathRB);
    }

    public void insert_time_comparison(int points, int difference) throws CloneNotSupportedException, IOException {
        BST<String> rb = new RB<>();
        BST<String> avl = new AVL<>();
        CommandInvoker commands = new CommandInvoker();
        BatchInsert insert = (BatchInsert) commands.invoke(Commands.BATCHINSERT);

        FileManager reader = new FileManager();

        List<Long> avl_insertTimes = new ArrayList<>();
        List<Long> rb_insertTimes = new ArrayList<>();
        List<Integer> heightAVLList = new ArrayList<>();
        List<Integer> heightRBList = new ArrayList<>();

        // words to be inserted for each base
        List<String> insertedWords = reader.readFile("target/batch/" + Integer.toString(1) + ".txt");

        for(int i = 1 ; i <= points ; i++){
            long start, end, avlTime = 0, rbTime = 0;
            int heightAVL = 0, heightRB = 0;

            TreeCloner<String> rb_cloner = new TreeCloner<>(rb);
            TreeCloner<String> avl_cloner = new TreeCloner<>(avl);

            AVL<String> avl_clone = (AVL<String>) avl_cloner.getClone();
            RB<String> rb_clone = (RB<String>) rb_cloner.getClone();

            List<String> added_words = reader.readFile("target/other_analysis_data/" + Integer.toString(i) + ".txt");

            for(int j = 0; j < difference; j++) {
                start =  System.nanoTime();
                avl_clone.insert(insertedWords.get(j));
                end =  System.nanoTime();
                avlTime = avlTime + end - start;
                heightAVL += avl_clone.getHeight();
                avl_clone = (AVL<String>) avl_cloner.getClone();
            }
            // average insertion time
            avlTime = avlTime / 1000;
            heightAVL /= 1000;

            for(int j = 0; j < difference; j++) {
                start =  System.nanoTime();
                rb_clone.insert(insertedWords.get(j));
                end =  System.nanoTime();
                rbTime = rbTime + end - start;
                heightRB += heightRB;
                rb_clone = (RB<String>) rb_cloner.getClone();
            }
            // average insertion time
            rbTime = rbTime /  1000;
            heightRB /= heightRB;

            avl_insertTimes.add(avlTime);
            rb_insertTimes.add(rbTime);
            heightAVLList.add(heightAVL);
            heightRBList.add(heightRB);
            insert.batchInsert(avl, added_words);
            insert.batchInsert(rb, added_words);
        }
        String timePathAVL = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\AVL_Insert_Time.txt";
        String timePathRB = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\RB_Insert_Time.txt";
        String heightPathAVL = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\AVL_Height.insert.txt";
        String heightPathRB = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\RB_Height_insert.txt";
        FileManager.writeLongToFile(avl_insertTimes, timePathAVL);
        FileManager.writeLongToFile(rb_insertTimes, timePathRB);
        FileManager.writeIntToFile(heightAVLList, heightPathAVL);
        FileManager.writeIntToFile(heightRBList, heightPathRB);
    }

    public void delete_time_comparison(int points, int difference) throws CloneNotSupportedException, IOException {
        BST<String> rb = new RB<>();
        BST<String> avl = new AVL<>();
        CommandInvoker commands = new CommandInvoker();
        BatchInsert insert = (BatchInsert) commands.invoke(Commands.BATCHINSERT);

        FileManager reader = new FileManager();


        List<Long> avl_deleteTimes = new ArrayList<>();
        List<Long> rb_deleteTimes = new ArrayList<>();
        List<Integer> heightAVLList = new ArrayList<>();
        List<Integer> heightRBList = new ArrayList<>();

        // words to be inserted for each base
        List<String> insertedWords = reader.readFile("target/deleteData/" + Integer.toString(1) + ".txt");

        for(int i = 1 ; i <= points ; i++){
            long start, end, avlTime = 0, rbTime = 0;
            int rbHeight = 0, avlHeight = 0;
            TreeCloner<String> rb_cloner = new TreeCloner<>(rb);
            TreeCloner<String> avl_cloner = new TreeCloner<>(avl);

            AVL<String> avl_clone = (AVL<String>) avl_cloner.getClone();
            RB<String> rb_clone = (RB<String>) rb_cloner.getClone();

            List<String> added_words = reader.readFile("target/deleteData/" + Integer.toString(i) + ".txt");

            for(int j = 0; j < difference; j++) {
                start =  System.nanoTime();
                avl_clone.delete(insertedWords.get(j));
                end =  System.nanoTime();
                avlTime = avlTime + end - start;
                avlHeight += avl_clone.getHeight();
                avl_clone = (AVL<String>) avl_cloner.getClone();
            }
            // average insertion time
            avlTime = avlTime / 1000;
            avlHeight /= 1000;

            for(int j = 0; j < difference; j++) {
                start =  System.nanoTime();
                rb_clone.delete(insertedWords.get(j));
                end =  System.nanoTime();
                rbTime = rbTime + end - start;
                rbHeight += rb_clone.getHeight();
                rb_clone = (RB<String>) rb_cloner.getClone();
            }
            // average insertion time
            rbTime = rbTime /  1000;
            rbHeight /= 1000;

            avl_deleteTimes.add(avlTime);
            rb_deleteTimes.add(rbTime);
            heightRBList.add(rbHeight);
            heightAVLList.add(avlHeight);
            insert.batchInsert(avl, added_words);
            insert.batchInsert(rb, added_words);
        }

        String timePathAVL = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\AVL_Delete_Time.txt";
        String timePathRB = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\RB_Delete_Time.txt";
        String heightPathAVL = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\AVL_Height_delete.txt";
        String heightPathRB = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\RB_Height_delete.txt";
        FileManager.writeLongToFile(avl_deleteTimes , timePathAVL);
        FileManager.writeLongToFile(rb_deleteTimes, timePathRB);
        FileManager.writeIntToFile(heightAVLList, heightPathAVL);
        FileManager.writeIntToFile(heightRBList, heightPathRB);
    }

    public void batch_delete_time_comparison(int points, int difference) throws CloneNotSupportedException, IOException {
        BST<String> red_black = new RB<String>();
        BST<String> avl = new AVL<String>();

        TreeCloner<String> rb_cloner = new TreeCloner<String>(red_black);
        TreeCloner<String> avl_cloner = new TreeCloner<String>(avl);

        BatchInsert insert = new BatchInsert();
        BatchDelete delete = new BatchDelete();
        FileManager reader = new FileManager();
        List<String> initial_words_in_tree = reader.readFile("target/batch/base.txt");
        //each tree will be initialized by 660 words
        insert.batchInsert(avl,initial_words_in_tree);
        insert.batchInsert(red_black,initial_words_in_tree);

        BST<String> rb_clone;
        BST<String> avl_clone;

        // to calculate time
        long start_avl_batchDeleteTime , end_avl_batchDeleteTime, finish_avl_batchDeleteTime;
        long start_rb_batchDeleteTime , end_rb_batchDeleteTime, finish_rb_batchDeleteTime;
        List<Long> rb_batchInsertTimes = new ArrayList<>();
        List<Long> avl_batchInsertTimes = new ArrayList<>();
        List<Integer> heightAVL = new ArrayList<>();
        List<Integer> heightRB = new ArrayList<>();

        // Here, we will test the batch insert on the two types of trees by deleting
        // word or 2 words or ... to the tree which have 1000 words and the base tree isn't affected by the insertion
        for(int i = 1 ; i <= points ; i++){
            List<String> added_words = reader.readFile("target/batch/" + Integer.toString(i) + ".txt");
            avl_clone = avl_cloner.getClone();
            rb_clone = rb_cloner.getClone();

            start_avl_batchDeleteTime =  System.nanoTime();
            delete.batchDelete(avl_clone, added_words);
            end_avl_batchDeleteTime =  System.nanoTime();
            finish_avl_batchDeleteTime = end_avl_batchDeleteTime - start_avl_batchDeleteTime;

            start_rb_batchDeleteTime =  System.nanoTime();
            delete.batchDelete(rb_clone, added_words);
            end_rb_batchDeleteTime =  System.nanoTime();
            finish_rb_batchDeleteTime = end_rb_batchDeleteTime - start_rb_batchDeleteTime;

            avl_batchInsertTimes.add(finish_avl_batchDeleteTime);
            rb_batchInsertTimes.add(finish_rb_batchDeleteTime);
            heightAVL.add(avl_clone.getHeight());
            heightRB.add(rb_clone.getHeight());
        }
        String timePathAVL = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\AVL_Batch_Delete_Time.txt";
        String timePathRB = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\RB_Batch_Delete_Time.txt";
        String heightPathAVL = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\AVL_Height_delete.txt";
        String heightPathRB = "D:\\CSE FOLDERS AND FILES\\semester 4\\DS 2\\plotter\\RB_Height_delete.txt";
        FileManager.writeLongToFile(avl_batchInsertTimes, timePathAVL);
        FileManager.writeLongToFile(rb_batchInsertTimes, timePathRB);
        FileManager.writeIntToFile(heightAVL, heightPathAVL);
        FileManager.writeIntToFile(heightRB, heightPathRB);
    }
}