package Data_Structures.Tree;

import Service.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class AVLTest extends BST {


    @Test
    void mixed() {
        AVL<Integer> testTree = new AVL<>(9);

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
        // 5 cases
        AVL<Integer> testTree = new AVL<>();
        testTree.insert(20);
        testTree.insert(8);
        testTree.insert(10);
        testTree.insert(12);
        testTree.insert(2);

        assertTrue(testTree.delete(12)); // delete leaf node

        testTree.insert(30);
        testTree.insert(40);
        testTree.insert(50);
        testTree.insert(14);
        testTree.insert(11);

        assertTrue(testTree.delete(30)); // delete internal node with two children
        assertTrue(testTree.delete(10)); // delete root having two children
        assertTrue(testTree.delete(14)); // one right
        assertTrue(testTree.delete(8)); // one left

        assertEquals(11, testTree.getRoot().getData()); // test root after deletions
        assertEquals(5, testTree.getSize()); // test total size after deletions
        assertEquals(2, testTree.getHeight()); // test tree height after deletions
    }

    @Test
    void cornerDeletion() {
        // 3 corner cases
        AVL<Integer> testTree = new AVL<>();

        assertFalse(testTree.delete(5)); // delete with no insertions

        testTree.insert(4);

        assertTrue(testTree.delete(4)); // delete root no right no left

        testTree.insert(4);
        testTree.insert(9);

        assertTrue(testTree.delete(4)); // delete root no left

        testTree.insert(5);

        assertTrue(testTree.delete(9)); // delete root no right

        testTree.insert(10);
        testTree.insert(4);
        testTree.insert(9);
        testTree.insert(15);
        testTree.insert(2);

        assertTrue(testTree.delete(5)); // delete root with two children

        assertFalse(testTree.delete(5)); // delete deleted node to check the deleted node has no side effects
        assertFalse(testTree.delete(100)); // delete node not inserted before
    }

    @Test
    void normalInsertion(){
        // Test rotations and height after each rotation in insertion
        AVL<Integer> testTree = new AVL<>();
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
        // 3 corner cases
        AVL<Integer> testTree = new AVL<>();

        testTree.insert(10);
        testTree.insert(20);
        testTree.insert(30);
        testTree.insert(40);
        testTree.insert(50);
        testTree.insert(60);
        testTree.insert(70);
        assertEquals(2, testTree.getHeight()); // ascending order insertion tree height

        assertFalse(testTree.insert(10)); // already exist normal case
        assertFalse(testTree.insert(40)); // already exist root case
    }
    @Test
    void search() {
        // 3 cases
        // TODO find cases
        AVL<Integer> testTree = new AVL<>();

        testTree.insert(20);
        testTree.insert(8);
        testTree.insert(50);
        testTree.insert(60);
        testTree.insert(70);
        testTree.insert(80);
        testTree.insert(12);
        testTree.insert(14);
        testTree.insert(17);
        testTree.insert(26);
        testTree.insert(55);
        testTree.insert(74);

        assertEquals(12, testTree.getSize());

        assertTrue(testTree.search(20)); // found

        assertFalse(testTree.search(90)); // not found

        testTree.delete(80);

        assertFalse(testTree.search(80)); // search deleted word
    }

    @Test
    void batchDelete() {
        AVL<String> avl = new AVL<>();
        RB<String> rb = new RB<>();
        CommandInvoker commands = new CommandInvoker();
        BatchDelete delete = (BatchDelete) commands.invoke(Commands.BATCHDELETE);
        BatchInsert insert = (BatchInsert) commands.invoke(Commands.BATCHINSERT);
        FileManager reader = new FileManager();

        List<String>    A = reader.readFile("target/batch_cases/30A.txt"),
                        B = reader.readFile("target/batch_cases/30B.txt"),
                        ABCD = reader.readFile("target/batch_cases/100A.txt"),
                        fiveWords = reader.readFile("target/batch_cases/5.txt"),
                        notFound = reader.readFile("target/batch_cases/10.txt"),
                        empty = reader.readFile("target/batch_cases/empty.txt");

        // insert the whole 100 words sample
        insert.batchInsert(avl, ABCD);
        insert.batchInsert(rb, ABCD);

        // delete 30 existing words
        Point AP = delete.batchDelete(avl, A);
        Point ARBP = delete.batchDelete(rb, A);

        // AVL
        assertEquals(30, AP.x); // 30 found so deleted
        assertEquals(0, AP.y); // 0 not found
        assertEquals(70, avl.getSize()); // check size after batch insertion

        // RB
        assertEquals(30, ARBP.x);
        assertEquals(0, ARBP.y);
        assertEquals(70, rb.getSize());

        // ensuring operation can be done many times
        Point BP = delete.batchDelete(avl, B);
        Point BRBP = delete.batchDelete(rb, B);

        // AVL
        assertEquals(30, BP.x); // 30 found so deleted
        assertEquals(0, BP.y); // 0 not found
        assertEquals(40, avl.getSize()); // check size after batch insertion

        // RB
        assertEquals(30, BRBP.x);
        assertEquals(0, BRBP.y);
        assertEquals(40, rb.getSize());

        // delete non existing elements
        Point notFoundP = delete.batchDelete(avl, notFound);
        Point notFoundRBP = delete.batchDelete(rb, notFound);

        // AVL
        assertEquals(0, notFoundP.x); // 0 found so deleted
        assertEquals(10, notFoundP.y); // 10 not found
        assertEquals(40, avl.getSize()); // check size after batch insertion

        // RB
        assertEquals(0, notFoundRBP.x);
        assertEquals(10, notFoundRBP.y);
        assertEquals(40, rb.getSize());

        // 5 words exist 5 words do not exist
        Point fiveP = delete.batchDelete(avl, fiveWords);
        Point fiveRBP = delete.batchDelete(rb, fiveWords);

        // AVL
        assertEquals(5, fiveP.x); // 30 found so deleted
        assertEquals(5, fiveP.y); // 0 not found
        assertEquals(35, avl.getSize()); // check size after batch insertion

        // RB
        assertEquals(5, fiveRBP.x);
        assertEquals(5, fiveRBP.y);
        assertEquals(35, rb.getSize());

        // delete from empty files
        Point emptyP = insert.batchInsert(avl, empty);
        Point emptyRBP = delete.batchDelete(rb, empty);

        // AVL
        assertEquals(0, emptyP.x); // 30 found so deleted
        assertEquals(0, emptyP.y); // 0 not found
        assertEquals(35, avl.getSize()); // check size after batch insertion

        // RB
        assertEquals(0, emptyRBP.x);
        assertEquals(0, emptyRBP.y);
        assertEquals(35, rb.getSize());

        // delete till last element, 5 exist and five do not exist
        Point ABCDP = delete.batchDelete(avl, ABCD);
        Point ABCDRBP = delete.batchDelete(rb, ABCD);

        // AVL
        assertEquals(35, ABCDP.x); // 35 found so deleted
        assertEquals(65, ABCDP.y); // 65 not found
        assertEquals(0, avl.getSize()); // check size after batch insertion

        // RB
        assertEquals(35, ABCDRBP.x);
        assertEquals(65, ABCDRBP.y);
        assertEquals(0, rb.getSize());

    }

    @Test
    void batchInsert() {
        AVL<String> avlInsert = new AVL<>();
        RB<String> rbInsert = new RB<>();
        CommandInvoker commands = new CommandInvoker();
        BatchInsert insert = (BatchInsert) commands.invoke(Commands.BATCHINSERT);
        FileManager reader = new FileManager();

        List<String>    A = reader.readFile("target/batch_cases/30A.txt"),
                        B = reader.readFile("target/batch_cases/30B.txt"),
                        C = reader.readFile("target/batch_cases/30C.txt"),
                        ABCD = reader.readFile("target/batch_cases/100A.txt"),
                        empty = reader.readFile("target/batch_cases/empty.txt");

        // insert all words to an empty tree
        Point AP = insert.batchInsert(avlInsert, A);
        Point RBAP = insert.batchInsert(rbInsert, A);

        // AVL
        assertEquals(30, AP.y); // 30 not found so inserted
        assertEquals(0, AP.x); // 0 found
        assertEquals(30, avlInsert.getSize()); // check size after batch insertion

        // RB
        assertEquals(30, RBAP.y);
        assertEquals(0, RBAP.x);
        assertEquals(30, rbInsert.getSize());

        // words all don't exist
        Point BP = insert.batchInsert(avlInsert, B);
        Point RBBP = insert.batchInsert(rbInsert, B);

        // AVL
        assertEquals(30, BP.y); // 30 not found so inserted
        assertEquals(0, BP.x); // 0 found
        assertEquals(60, avlInsert.getSize()); // check size after batch insertion

        // RB
        assertEquals(30, RBBP.y);
        assertEquals(0, RBBP.x);
        assertEquals(60, rbInsert.getSize());

        // words all don't exist
        Point CP =  insert.batchInsert(avlInsert, C);
        Point CRBP =  insert.batchInsert(rbInsert, C);

        // AVL
        assertEquals(30, CP.y); // 30 not found so inserted
        assertEquals(0, CP.x); // 0 found
        assertEquals(90, avlInsert.getSize()); // check size after batch insertion

        // RB
        assertEquals(30, CRBP.y);
        assertEquals(0, CRBP.x);
        assertEquals(90, avlInsert.getSize());

        // words some exist and some not
        Point ABCDP = insert.batchInsert(avlInsert, ABCD);
        Point ABCDRBP = insert.batchInsert(rbInsert, ABCD);

        // AVL
        assertEquals(90, ABCDP.x); // 90 already exist not added
        assertEquals(10, ABCDP.y); // 10 not exist so add them
        assertEquals(100, avlInsert.getSize()); // tree size check

        // RB
        assertEquals(90, ABCDRBP.x);
        assertEquals(10, ABCDRBP.y);
        assertEquals(100, rbInsert.getSize());

        // Insert from empty file
        Point emptyP = insert.batchInsert(avlInsert, empty);
        Point emptyRBP = insert.batchInsert(avlInsert, empty);

        // AVL
        assertEquals(0, emptyP.x);
        assertEquals(0, emptyP.y);
        assertEquals(100, avlInsert.getSize()); // tree size check

        // RB
        assertEquals(0, emptyRBP.x);
        assertEquals(0, emptyRBP.y);
        assertEquals(100, rbInsert.getSize()); // tree size check
    }

}