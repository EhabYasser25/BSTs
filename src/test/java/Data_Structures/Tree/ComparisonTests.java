package Data_Structures.Tree;
import Service.*;
import org.junit.jupiter.api.Test;

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
    void compare_height2() {
        AVL<Integer> testAVL = new AVL<>();
        RB<Integer> testRB = new RB<>();

        //inserting linear list of integers and compare between height handling between two trees
        assertTrue(testAVL.insert(1));
        assertTrue(testRB.insert(1));
        assertEquals(testAVL.getHeight(), 0);
        assertEquals(testRB.getHeight(), 0);
        assertTrue(testAVL.insert(2));
        assertTrue(testRB.insert(2));
        assertEquals(testAVL.getHeight(), 1);
        assertEquals(testRB.getHeight(), 1);
        assertTrue(testAVL.insert(3));
        assertTrue(testRB.insert(3));
        assertEquals(testAVL.getHeight(), 1);
        assertEquals(testRB.getHeight(), 1);
        assertTrue(testAVL.insert(4));
        assertTrue(testRB.insert(4));
        assertEquals(testAVL.getHeight(), 2);
        assertEquals(testRB.getHeight(), 2);
        assertTrue(testAVL.insert(5));
        assertTrue(testRB.insert(5));
        assertEquals(testAVL.getHeight(), 2);
        assertEquals(testRB.getHeight(), 2);
        assertTrue(testAVL.insert(6));
        assertTrue(testRB.insert(6));  //the RB tree height increased at this step while avl still have same height
        assertEquals(testAVL.getHeight(), 2);
        assertEquals(testRB.getHeight(), 3);
        assertTrue(testAVL.insert(7));
        assertTrue(testRB.insert(7));
        assertEquals(testAVL.getHeight(), 2);
        assertEquals(testRB.getHeight(), 3);
        assertTrue(testAVL.insert(8));
        assertTrue(testRB.insert(8));
        assertEquals(testAVL.getHeight(), 3);
        assertEquals(testRB.getHeight(), 3);
        assertTrue(testAVL.insert(9));
        assertTrue(testRB.insert(9));
        assertEquals(testAVL.getHeight(), 3);
        assertEquals(testRB.getHeight(), 3);
        assertTrue(testAVL.insert(10));
        assertTrue(testRB.insert(10));  //again only the height of RB increased
        assertEquals(testAVL.getHeight(), 3);
        assertEquals(testRB.getHeight(), 4);
        assertTrue(testAVL.insert(11));
        assertTrue(testRB.insert(11));
        assertEquals(testAVL.getHeight(), 3);
        assertEquals(testRB.getHeight(), 4);
        assertTrue(testAVL.insert(12));
        assertTrue(testRB.insert(12));
        assertEquals(testAVL.getHeight(), 3);
        assertEquals(testRB.getHeight(), 4);
        assertTrue(testAVL.insert(13));
        assertTrue(testRB.insert(13));
        assertEquals(testAVL.getHeight(), 3);
        assertEquals(testRB.getHeight(), 4);
        assertTrue(testAVL.insert(14));
        assertTrue(testRB.insert(14));  //the difference between their heights becomes 2 at this step
        assertEquals(testAVL.getHeight(), 3);
        assertEquals(testRB.getHeight(), 5);
        assertTrue(testAVL.insert(15));
        assertTrue(testRB.insert(15));
        assertEquals(testAVL.getHeight(), 3);
        assertEquals(testRB.getHeight(), 5);
        assertTrue(testAVL.insert(16));
        assertTrue(testRB.insert(16));
        assertEquals(testAVL.getHeight(), 4);
        assertEquals(testRB.getHeight(), 5);
        assertTrue(testAVL.insert(17));
        assertTrue(testRB.insert(17));
        assertEquals(testAVL.getHeight(), 4);
        assertEquals(testRB.getHeight(), 5);
    }

    @Test
    void insertDeleteTimeHeightComparison(){
        AVL<String> avlInsert = new AVL<>();
        RB<String> rbInsert = new RB<>();
        CommandInvoker commands = new CommandInvoker();
        BatchInsert insert = (BatchInsert) commands.invoke(Commands.BATCHINSERT);
        FileManager reader = new FileManager();
        List<String> words = reader.readFile("D:\\CP\\tests\\1000.txt");
        long startTime = System.nanoTime();
        insert.batchInsert(avlInsert, words);
        long endTime = System.nanoTime();
        long timeAvl = endTime - startTime;
        startTime = System.nanoTime();
        insert.batchInsert(rbInsert, words);
        endTime = System.nanoTime();
        long timeRB = endTime - startTime;

        assertTrue(timeAvl > timeRB); // time comparison batch insert
        assertTrue(avlInsert.getHeight() <= rbInsert.getHeight()); // height

        BatchDelete delete = (BatchDelete) commands.invoke(Commands.BATCHDELETE);
        words = reader.readFile("D:\\CP\\tests\\26_1000.txt");

        startTime = System.nanoTime();
        delete.batchDelete(avlInsert, words);
        endTime = System.nanoTime();
        timeAvl = endTime - startTime;

        startTime = System.nanoTime();
        delete.batchDelete(rbInsert, words);
        endTime = System.nanoTime();
        timeRB = endTime - startTime;

        assertTrue(timeAvl > timeRB); // time comparison batch delete
        assertTrue(avlInsert.getHeight() <= rbInsert.getHeight()); // height comparison batch delete
    }

}
