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

import static Service.FileManager.writeStringToFile;

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
    void comparison_height() throws CloneNotSupportedException, IOException{
        BST<Integer> red_black = new RB<>();
        BST<Integer> AVL = new AVL<>();
        List<Long> rb_heightTimes = new ArrayList<>();
        List<Long> avl_heightTimes = new ArrayList<>();
        int[] inputValues = this.randomInt(1000000);

        long redBlack_HeightTime = 0, AVL_HeightTime = 0, startTime = 0, endTime = 0;

        for (int inputValue : inputValues) {
            redBlack_HeightTime = 0;
            AVL_HeightTime = 0;

            startTime = System.nanoTime();
            red_black.getHeight();
            endTime = System.nanoTime();
            redBlack_HeightTime += endTime - startTime;
            startTime = System.nanoTime();
            AVL.getHeight();
            endTime = System.nanoTime();
            AVL_HeightTime += endTime - startTime;

            rb_heightTimes.add(redBlack_HeightTime);
            avl_heightTimes.add(AVL_HeightTime);
            red_black.insert(inputValue);
            AVL.insert(inputValue);
        }
        FileManager.writeLongToFile(rb_heightTimes, "target/other_analysis_data/Red-Black Height time values.txt");
        FileManager.writeLongToFile(avl_heightTimes, "target/other_analysis_data/AVL Height time values.txt");
    }

    // Generate tree of base baseCount words, difference between each teo consecutive files difference, length of word is length, number of files generated is points
    public void randomWordsGenerator(int baseCount, int difference, int length, int points) throws IOException {
        ArrayList<String> base = randomString(baseCount, length);
        FileManager.writeStringToFile(base, "target/batch/base.txt");
        System.out.println("wawa");

        // 200 points, difference in number of words in each two consecutive files = 500
        for(int i = 0; i < points; i++) {
            ArrayList<String> file = randomString( difference * (i + 1), length);
            FileManager.writeStringToFile(file, "target/batch/" + Integer.toString(i + 1) + ".txt");
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
    void timeTest() throws IOException {
        // called here to control parameters
        randomWordsGenerator(10000, 500, 26, 1000);
    }
   // Only for analysis
    @Test
    void batch() {
        AnalysisClass analysisClass = new AnalysisClass();
        try {
            analysisClass.compare_batch_insert_time(1000, 500);
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            analysisClass.compare_bash_delete_time(1000, 500);
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compare_batch_insert_time(int points, int maxFile) throws CloneNotSupportedException, IOException {
        BST<String> red_black = new RB<>();
        BST<String> avl = new AVL<>();
        CommandInvoker commands = new CommandInvoker();
        BatchInsert insert = (BatchInsert) commands.invoke(Commands.BATCHINSERT);

        FileManager reader = new FileManager();
        List<String> initial_words_in_tree = reader.readFile("target/batch/base.txt");
        //each tree will be initialized by 600 words
        insert.batchInsert(avl, initial_words_in_tree);
        insert.batchInsert(red_black, initial_words_in_tree);
        System.out.println(avl.getSize());

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
            finish_avl_batchInsertTime = 1000 * (end_avl_batchInsertTime - start_avl_batchInsertTime) / ((long) i * maxFile);

            start_rb_batchInsertTime =  System.nanoTime();
            insert.batchInsert(rb_clone,added_words);
            end_rb_batchInsertTime =  System.nanoTime();
            finish_rb_batchInsertTime = 1000 * (end_rb_batchInsertTime - start_rb_batchInsertTime) / ((long) i * maxFile);

            avl_batchInsertTimes.add(finish_avl_batchInsertTime);
            rb_batchInsertTimes.add(finish_rb_batchInsertTime);
            heightAVL.add(avl_clone.getHeight());
            heightRB.add(rb_clone.getHeight());
        }
        String timePathAVL = "target/batch/AVL Batch Insert Time Values.txt";
        String timePathRB = "target/batch/RB Batch Insert Time Values.txt";
        String heightPathAVL = "target/batch/AVL_Height_insert.txt";
        String heightPathRB = "target/batch/RB_Height_insert.txt";
        FileManager.writeLongToFile(avl_batchInsertTimes, timePathAVL);
        FileManager.writeLongToFile(rb_batchInsertTimes, timePathRB);
        FileManager.writeIntToFile(heightAVL, heightPathAVL);
        FileManager.writeIntToFile(heightRB, heightPathRB);
    }

    public void compare_bash_delete_time(int points, int maxFile) throws CloneNotSupportedException, IOException {
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
            finish_avl_batchDeleteTime = 1000 * (end_avl_batchDeleteTime - start_avl_batchDeleteTime) /  ((long) i * maxFile) ;

            start_rb_batchDeleteTime =  System.nanoTime();
            insert.batchInsert(rb_clone, added_words);
            end_rb_batchDeleteTime =  System.nanoTime();
            finish_rb_batchDeleteTime = 1000 * (end_rb_batchDeleteTime - start_rb_batchDeleteTime) / ((long) i * maxFile);

            avl_batchInsertTimes.add(finish_avl_batchDeleteTime);
            rb_batchInsertTimes.add(finish_rb_batchDeleteTime);
            heightAVL.add(avl_clone.getHeight());
            heightRB.add(rb_clone.getHeight());
        }
        String timePathAVL = "target/batch/AVL Batch Delete Time Values.txt";
        String timePathRB = "target/batch/RB Batch Delete Time Values.txt";
        String heightPathAVL = "target/batch/AVL_Height_delete.txt";
        String heightPathRB = "target/batch/RB_Height_delete.txt";
        FileManager.writeLongToFile(avl_batchInsertTimes, timePathAVL);
        FileManager.writeLongToFile(rb_batchInsertTimes, timePathRB);
        FileManager.writeIntToFile(heightAVL, heightPathAVL);
        FileManager.writeIntToFile(heightRB, heightPathRB);
    }
}