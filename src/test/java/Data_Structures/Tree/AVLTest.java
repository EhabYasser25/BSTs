package Data_Structures.Tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AVLTest extends BST {


    @Test
    void test1() {
        AVL<Integer> testTree = new AVL<Integer>(9);

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

        assertTrue(testTree.delete(9));  // delete the root
        assertEquals(testTree.getRoot().getData(),10);
        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2,5,10,15,20)), testTree.visit(VisitType.DFS));
        assertTrue(testTree.insert(12));
        assertTrue(testTree.delete(15)); // delete node with two children then it will take the successor

        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2,5,10,12,20)), testTree.visit(VisitType.DFS));

        assertTrue(testTree.insert(30)); // test RR

        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2,5,10,12,20,30)), testTree.visit(VisitType.DFS));

        assertTrue(testTree.insert(40));
        assertTrue(testTree.insert(50)); // test RR

        assertEquals(new ArrayList<Integer>(Arrays.asList(1,2,5,10,12,20,30,40,50)), testTree.visit(VisitType.DFS));

        assertTrue(testTree.insert(0));
        assertTrue(testTree.insert(-5)); // test LL

        assertEquals(new ArrayList<Integer>(Arrays.asList(-5,0,1,2,5,10,12,20,30,40,50)), testTree.visit(VisitType.DFS));

        assertTrue(testTree.delete(-5)); // delete leaf

        assertEquals(new ArrayList<Integer>(Arrays.asList(0,1,2,5,10,12,20,30,40,50)), testTree.visit(VisitType.DFS));

        assertEquals(testTree.getHeight(),3);

    }

    @Test
    void test2() {
        AVL<Integer> testTree = new AVL<Integer>();
        assertFalse(testTree.delete(5));

    }

    @Test
    void test3(){
        AVL<Integer> testTree = new AVL<Integer>();
        assertTrue(testTree.insert(20));
        assertTrue(testTree.insert(15));
        assertTrue(testTree.insert(10)); // test RR

        assertFalse(testTree.delete(5)); // delete for non existing data
    }

    @Test
    void test4(){
        AVL<Integer> testTree = new AVL<Integer>();
        assertTrue(testTree.insert(20));
        assertTrue(testTree.insert(5));
        assertTrue(testTree.insert(10)); // test LR
        assertTrue(testTree.insert(12));
        assertTrue(testTree.insert(1));

        assertFalse(testTree.insert(12)); // inserting existing data

    }

    @Test
    void test5() {
        AVL<Integer> testTree = new AVL<Integer>();
        assertTrue(testTree.insert(20));
        assertTrue(testTree.insert(8));

        assertTrue(testTree.search(20)); // found

        assertTrue(testTree.insert(10)); // test LR

        assertFalse(testTree.search(90)); // not found

        assertTrue(testTree.insert(12));
        assertTrue(testTree.insert(2));

        assertTrue(testTree.search(12)); // found

    }

    @Test
    void test6() {
        AVL<Integer> testTree = new AVL<Integer>();
        assertTrue(testTree.insert(20));
        assertTrue(testTree.insert(8));
        assertTrue(testTree.insert(10)); // test LR
        assertTrue(testTree.insert(12));
        assertTrue(testTree.insert(2));

        assertEquals(testTree.getHeight(),2); //height of the tree = 2 (check height)

        assertEquals(testTree.getRoot().getData(),10);
        assertTrue(testTree.delete(12)); // delete leaf node
        assertFalse(testTree.search(12)); // not found
    }

    @Test
    void test7(){

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