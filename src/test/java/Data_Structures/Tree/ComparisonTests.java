package Data_Structures.Tree;
import Data_Structures.Tree.HelpingClasses.TreeCloner;
import Service.BatchDelete;
import Service.BatchInsert;
import Service.FileManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonTests {

    @Test
    void compare_height1(){
        RB<Integer> rb_tree = new RB<>();
        AVL<Integer> avl_tree = new AVL<>();

        //This sequence will produce different heights for each tree
        int[] value = {15, 5, 40, 7, 2, 1};

        for(int i : value){
            rb_tree.insert(i);
            avl_tree.insert(i);
        }

        assertEquals(3, rb_tree.getHeight());
        assertEquals(2, avl_tree.getHeight());
    }
    @Test
    void compare_bash_insert_time() throws CloneNotSupportedException, IOException {
        BST<String> red_black = new RB<String>();
        BST<String> avl = new AVL<String>();

        TreeCloner<String> rb_cloner = new TreeCloner<String>(red_black);
        TreeCloner<String> avl_cloner = new TreeCloner<String>(avl);

        BatchInsert insert = new BatchInsert();
        FileManager reader = new FileManager();
        List<String> initial_words_in_tree = reader.readFile("D:\\CP\\tests\\600words.txt");
        //each tree will be initialized by 600 words
        insert.batchInsert(avl,initial_words_in_tree);
        insert.batchInsert(red_black,initial_words_in_tree);

        BST<String> avl_clone = null;
        BST<String> rb_clone = null;

        // to calculate time
        long start_avl_batchInsertTime , end_avl_batchInsertTime, finish_avl_batchInsertTime;
        long start_rb_batchInsertTime , end_rb_batchInsertTime, finish_rb_batchInsertTime;
        List<Long> avl_batchInsertTimes = new ArrayList<>();
        List<Long> rb_batchInsertTimes = new ArrayList<>();

        // Here, we will test the batch insert on the two types of trees by adding
        // word or 2 words or ... to the tree which have 1000 words and the base tree isn't affected by the insertion
        for(int i = 1 ; i <= 60 ; i++){
            List<String> added_words = reader.readFile("D:\\CP\\tests\\"+Integer.toString(i)+"words.txt");
            avl_clone = avl_cloner.getClone();
            rb_clone = rb_cloner.getClone();

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
        }
        FileManager.writeToFile(avl_batchInsertTimes,"AVL Batch Insert Time Values.txt");
        FileManager.writeToFile(rb_batchInsertTimes,"RB Batch Insert Time Values.txt");
    }

    @Test
    void compare_bash_delete_time() throws CloneNotSupportedException, IOException {
        BST<String> red_black = new RB<String>();
        BST<String> avl = new AVL<String>();

        TreeCloner<String> rb_cloner = new TreeCloner<String>(red_black);
        TreeCloner<String> avl_cloner = new TreeCloner<String>(avl);

        BatchInsert insert = new BatchInsert();
        BatchDelete delete = new BatchDelete();
        FileManager reader = new FileManager();
        List<String> initial_words_in_tree = reader.readFile("D:\\CP\\tests\\660words.txt");
        //each tree will be initialized by 660 words
        insert.batchInsert(avl,initial_words_in_tree);
        insert.batchInsert(red_black,initial_words_in_tree);

        BST<String> rb_clone = null;
        BST<String> avl_clone = null;

        // to calculate time
        long start_avl_batchDeleteTime , end_avl_batchDeleteTime, finish_avl_batchDeleteTime;
        long start_rb_batchDeleteTime , end_rb_batchDeleteTime, finish_rb_batchDeleteTime;
        List<Long> avl_batchDeleteTimes = new ArrayList<>();
        List<Long> rb_batchDeleteTimes = new ArrayList<>();

        // Here, we will test the batch insert on the two types of trees by deleting
        // word or 2 words or ... to the tree which have 1000 words and the base tree isn't affected by the insertion
        for(int i = 1 ; i <= 60 ; i++){
            List<String> deleted_words = reader.readFile("D:\\CP\\tests\\"+Integer.toString(i)+"words.txt");
            avl_clone = avl_cloner.getClone();
            rb_clone = rb_cloner.getClone();

            start_avl_batchDeleteTime =  System.nanoTime();
            delete.batchDelete(avl_clone,deleted_words);
            end_avl_batchDeleteTime =  System.nanoTime();
            finish_avl_batchDeleteTime = end_avl_batchDeleteTime - start_avl_batchDeleteTime;

            start_rb_batchDeleteTime =  System.nanoTime();
            delete.batchDelete(rb_clone,deleted_words);
            end_rb_batchDeleteTime =  System.nanoTime();
            finish_rb_batchDeleteTime = end_rb_batchDeleteTime - start_rb_batchDeleteTime;

            avl_batchDeleteTimes.add(finish_avl_batchDeleteTime);
            rb_batchDeleteTimes.add(finish_rb_batchDeleteTime);
        }
        FileManager.writeToFile(avl_batchDeleteTimes,"AVL Batch Delete Time Values.txt");
        FileManager.writeToFile(rb_batchDeleteTimes,"RB Batch Delete Time Values.txt");
    }
}
