package Data_Structures.Tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AVLTest extends BST {


    @Test
    void mixed() {
        AVL<Integer> testTree = new AVL<Integer>(9);

        /** INSERTION */
        assertFalse(testTree.insert(9)); // insert a node has the same data
        assertTrue(testTree.insert(5));
        assertTrue(testTree.insert(2));  // test LL

        assertEquals(testTree.getRoot().getData(),5);

        assertTrue(testTree.insert(20));
        assertTrue(testTree.insert(15)); // test RL
        assertTrue(testTree.insert(10)); // test RL

        assertEquals(testTree.getRoot().getData(),9);

        assertTrue(testTree.insert(1));  // test LL

        assertEquals(testTree.getHeight(),2); //height of the tree = 2 (check height)

        assertEquals(7, testTree.getSize());

        assertTrue(testTree.delete(9));  // delete the root

        assertEquals(testTree.getRoot().getData(),10);

        assertEquals(new ArrayList<>(Arrays.asList(1,2,5,10,15,20)), testTree.visit(VisitType.DFS));
        assertTrue(testTree.insert(12));
        assertTrue(testTree.delete(15)); // delete node with two children then it will take the successor

        assertEquals(new ArrayList<>(Arrays.asList(1,2,5,10,12,20)), testTree.visit(VisitType.DFS));

        assertTrue(testTree.insert(30)); // test RR

        assertEquals(new ArrayList<>(Arrays.asList(1,2,5,10,12,20,30)), testTree.visit(VisitType.DFS));

        assertTrue(testTree.insert(40));
        assertTrue(testTree.insert(50)); // test RR

        assertEquals(new ArrayList<>(Arrays.asList(1,2,5,10,12,20,30,40,50)), testTree.visit(VisitType.DFS));

        assertTrue(testTree.insert(0));
        assertTrue(testTree.insert(-5)); // test LL

        assertEquals(new ArrayList<>(Arrays.asList(-5,0,1,2,5,10,12,20,30,40,50)), testTree.visit(VisitType.DFS));

        assertTrue(testTree.delete(-5)); // delete leaf

        assertEquals(new ArrayList<>(Arrays.asList(0,1,2,5,10,12,20,30,40,50)), testTree.visit(VisitType.DFS));

        assertEquals(testTree.getHeight(),3);

    }

    @Test
    void normalDeletion() {
        AVL<Integer> testTree = new AVL<Integer>();
        testTree.insert(20);
        testTree.insert(8);
        testTree.insert(10);
        testTree.insert(12);
        testTree.insert(2);

        assertEquals(testTree.getHeight(),2); //height of the tree = 2 (check height)
        assertEquals(testTree.getRoot().getData(),10);
        assertEquals(5, testTree.getSize());

        assertTrue(testTree.delete(12)); // delete leaf node
        assertEquals(4, testTree.getSize()); // size updated on delete
        assertEquals(2, testTree.getHeight());

        testTree.insert(30);
        testTree.insert(40);
        testTree.insert(50);
        testTree.insert(14);
        testTree.insert(11);

        assertTrue(testTree.delete(30)); // delete internal node with two children
        assertEquals(3, testTree.getHeight()); //test height
        assertEquals(8, testTree.getSize()); // test size

        assertTrue(testTree.delete(10)); // delete root having two children
        assertEquals(11, testTree.getRoot().getData());
        assertEquals(3, testTree.getHeight());

        assertTrue(testTree.delete(40)); // one right
        assertTrue(testTree.delete(8)); // one left
        assertEquals(2, testTree.getHeight()); // height after deletion
        assertEquals(5, testTree.getSize()); // size after deletion
    }

    @Test
    void cornerDeletion() {
        // 3 corner cases
        AVL<Integer> testTree = new AVL<Integer>();

        assertFalse(testTree.delete(5)); // delete with no insertions
        assertEquals(0, testTree.getSize());

        testTree.insert(4);
        testTree.insert(9);
        testTree.delete(4);

        assertFalse(testTree.delete(4)); // delete deleted word, tree size > 1

        testTree.delete(9);
        testTree.delete(5);

        assertFalse(testTree.delete(5)); // delete inserted word, tree size = 0
        assertEquals(0, testTree.getSize());
    }

    @Test
    void normalInsertion(){
        // Test rotations and height after each rotation
        AVL<Integer> testTree = new AVL<Integer>();
        testTree.insert(20);
        testTree.insert(5);
        testTree.insert(10); // LR

        assertEquals(1, testTree.getHeight()); // test height
        assertEquals(10, testTree.getRoot().getData()); // test root

        testTree.insert(2);
        testTree.insert(1); // LL

        assertEquals(2, testTree.getHeight()); // test height
        assertEquals(10, testTree.getRoot().getData()); // test root

        testTree.insert(30);
        testTree.insert(40); // RR

        assertEquals(2, testTree.getHeight()); // test height
        assertEquals(10, testTree.getRoot().getData()); // test root

        testTree.insert(50);
        testTree.insert(45); // RL

        assertEquals(3, testTree.getHeight());
        assertEquals(10, testTree.getRoot().getData()); // test root
    }

    @Test
    void cornerInsert(){

    }

    @Test
    void search() {
        // Repeated
        AVL<Integer> testTree = new AVL<Integer>();
        testTree.insert(20);
        testTree.insert(8);

        assertEquals(2, testTree.getSize());

        assertTrue(testTree.search(20)); // found

        assertTrue(testTree.insert(10)); // test LR

        assertEquals(3, testTree.getSize());

        assertFalse(testTree.search(90)); // not found

        assertTrue(testTree.insert(12));
        assertTrue(testTree.insert(2));

        assertEquals(5, testTree.getSize());

        assertTrue(testTree.search(12)); // found

    }

    @Test
    void test3(){
        // Repeated
        AVL<Integer> testTree = new AVL<Integer>();
        assertTrue(testTree.insert(20));
        assertTrue(testTree.insert(15));
        assertTrue(testTree.insert(10)); // test RR

        assertEquals(3, testTree.getSize());
        assertFalse(testTree.delete(5)); // delete for non existing data
        assertEquals(3, testTree.getSize());
    }

    @Test
    void test8(){
    }

    @Test
    void test9() {
    }

    @Test
    void test10() {
    }

}