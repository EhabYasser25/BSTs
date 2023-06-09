package Data_Structures.Tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RBTest extends BST {

    @Test
    void insertion_focusedTest() {
        RB<Integer> testTree = new RB<>(5);
        assertTrue(testTree.insert(3));
        assertTrue(testTree.insert(8));
        assertTrue(testTree.insert(7));
        assertTrue(testTree.insert(10));
        assertTrue(testTree.insert(4));
        assertTrue(testTree.insert(2));
        assertTrue(testTree.insert(35));
        assertTrue(testTree.insert(45));
        assertTrue(testTree.insert(77));
        assertTrue(testTree.insert(75));
        assertTrue(testTree.insert(13));
        assertTrue(testTree.insert(1));
        assertTrue(testTree.insert(6));
        assertTrue(testTree.insert(23));

        assertFalse(testTree.insert(23)); //check inserting a pre-existing element
        assertFalse(testTree.insert(null)); //check inserting a null element

        //This should be the inorder arrangement of the tree after balancing
        assertEquals(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 10, 13, 23, 35, 45, 75, 77)), testTree.visit(VisitType.DFS));

        assertTrue(testTree.delete(13));
        assertTrue(testTree.delete(4));
        assertTrue(testTree.delete(2));
        assertTrue(testTree.delete(35));
        assertTrue(testTree.delete(45));
        assertTrue(testTree.delete(77));

        //inserting elements that just got deleted
        assertTrue(testTree.insert(4));
        assertTrue(testTree.insert(35));
        assertTrue(testTree.insert(13));

        assertEquals(new ArrayList<>(Arrays.asList(1, 3, 4, 5, 6, 7, 8, 10, 13, 23, 35, 75)), testTree.visit(VisitType.DFS));
    }

    @Test
    void searching_focusedTest(){
        RB<Double> testTree = new RB<>(42.8);
        assertTrue(testTree.insert(44.7));
        assertTrue(testTree.insert(47.254));
        assertTrue(testTree.insert(81.0));
        assertTrue(testTree.insert(82.789));
        assertTrue(testTree.insert(23.451));
        assertTrue(testTree.insert(78.789));
        assertTrue(testTree.insert(37.0004));
        assertTrue(testTree.insert(89.457));
        assertTrue(testTree.insert(49.97));
        assertTrue(testTree.insert(21.2454));
        assertTrue(testTree.insert(3.4785));
        assertTrue(testTree.insert(45.35714));
        assertTrue(testTree.insert(49.457));
        assertTrue(testTree.insert(26.78453));
        assertTrue(testTree.insert(66.111));
        assertTrue(testTree.insert(16.030405));
        assertEquals(testTree.size, 17); //checking for size after large insertion

        assertEquals(new ArrayList<>(Arrays.asList(3.4785, 16.030405, 21.2454, 23.451, 26.78453, 37.0004, 42.8, 44.7, 45.35714, 47.254, 49.457, 49.97, 66.111, 78.789, 81.0, 82.789, 89.457)), testTree.visit(VisitType.DFS));

        assertFalse(testTree.search(21.245399)); //searching for an element that doesn't exist
        assertEquals(testTree.size, 17); //asserting that size didn't change after searching

        assertTrue(testTree.search(44.7)); //search for the root
        assertTrue(testTree.search(16.030405)); //search for a leaf

        assertTrue(testTree.delete(37.0004));
        assertFalse(testTree.search(37.0004)); //search for a deleted element
        assertEquals(testTree.size, 16); //asserting that size didn't change after searching

        assertFalse(testTree.search(null)); //search for null

        //This should be the inorder ordering of the tree after deletion
        assertEquals(new ArrayList<>(Arrays.asList(3.4785, 16.030405, 21.2454, 23.451, 26.78453, 42.8, 44.7, 45.35714, 47.254, 49.457, 49.97, 66.111, 78.789, 81.0, 82.789, 89.457)), testTree.visit(VisitType.DFS));

        //checking the colour of various nodes in the tree
        assertTrue(testTree.getRoot().isBlack());
        assertFalse(testTree.getRoot().getRight().getLeft().getRight().getLeft().isBlack());
        assertTrue(testTree.getRoot().getLeft().getRight().getLeft().isBlack());
    }

    @Test
    void deletion_focusedTest() {
        RB<Integer> testTree = new RB<>();
        assertEquals(testTree.getSize(), 0);  //check initial size is 0
        assertEquals(testTree.height, 0);  //check initial height is 0
        assertTrue(testTree.insert(5));  //check insertion is done
        assertTrue(testTree.getRoot().isBlack());  //check root added is black
        assertTrue(testTree.insert(2));
        assertTrue(testTree.insert(9));
        assertTrue(testTree.insert(4));
        assertTrue(testTree.insert(7));
        assertFalse(testTree.insert(7));  //check repeated insertion
        assertTrue(testTree.insert(6));
        assertTrue(testTree.insert(29));
        assertTrue(testTree.insert(0));
        assertEquals(testTree.size, 8);
        assertTrue(testTree.search(4));  //check searching on existing node
        assertTrue(testTree.delete(29));  //check deletion red leaf node
        assertTrue(testTree.delete(5));  //check deletion of root
        assertTrue(testTree.delete(7));  //random deletions
        assertTrue(testTree.delete(4));
        assertTrue(testTree.delete(2));
        assertEquals(testTree.getSize(), 3);  //check final size
    }

    @Test
    void tricky_deletion_focusedTest() {
        RB<Integer> testTree = new RB<>();
        assertTrue(testTree.insert(10));
        assertTrue(testTree.insert(45));
        assertTrue(testTree.insert(47));
        assertTrue(testTree.insert(85));
        assertTrue(testTree.insert(12));
        assertTrue(testTree.insert(74));
        assertTrue(testTree.insert(37));
        assertTrue(testTree.insert(9));
        assertTrue(testTree.insert(84));
        assertTrue(testTree.insert(23));
        assertTrue(testTree.insert(78));
        assertTrue(testTree.insert(2));
        assertTrue(testTree.insert(79));
        assertTrue(testTree.insert(7));
        assertTrue(testTree.insert(31));
        assertTrue(testTree.insert(66));
        assertTrue(testTree.insert(99));
        assertTrue(testTree.insert(17));
        assertTrue(testTree.insert(33));
        assertTrue(testTree.insert(16));
        assertTrue(testTree.insert(55));
        assertTrue(testTree.insert(5));
        assertTrue(testTree.insert(70));
        assertTrue(testTree.insert(11));
        assertEquals(testTree.getSize(), 24);  //check size

        assertTrue(testTree.delete(31));  //actual delete red node
        assertTrue(testTree.delete(33));  //actual delete black node that have black sibling and far red nephew
        assertTrue(testTree.delete(16));  //actual delete black node that have black sibling, far black nephew and near red nephew
        assertTrue(testTree.delete(17));  //actual delete black node that have black sibling and two black nephews
        assertTrue(testTree.delete(12));
        assertTrue(testTree.delete(23));  //actual delete black node that have red sibling
        assertTrue(testTree.delete(47));  //actual delete black node that have black sibling, far red nephew and near black nephew

        //delete the root
        assertEquals(testTree.getRoot().getData(), 45);  //check the dat of the root
        assertTrue(testTree.delete((45)));

        //some further deletions to check many cases
        assertTrue(testTree.delete((66)));
        assertTrue(testTree.delete((74)));
        assertTrue(testTree.delete((84)));
        assertTrue(testTree.delete((11)));
        assertTrue(testTree.delete((9)));

        //test size after these deletions
        assertEquals(testTree.getSize(), 11);
    }

    @Test
    void size_focusedTest() {
        RB<Integer> testTree = new RB<>(42);
        assertTrue(testTree.insert(102));
        assertTrue(testTree.insert(101));
        assertTrue(testTree.insert(33));
        assertTrue(testTree.insert(10));
        assertTrue(testTree.insert(4));
        assertTrue(testTree.insert(72));
        assertTrue(testTree.insert(20));
        assertTrue(testTree.insert(9));
        assertTrue(testTree.insert(16));
        assertTrue(testTree.insert(8));
        assertTrue(testTree.insert(90));
        assertTrue(testTree.insert(-3));
        assertEquals(testTree.size, 13); //checking for size after large insertion

        assertEquals(new ArrayList<>(Arrays.asList(-3, 4, 8, 9, 10, 16, 20, 33, 42, 72, 90, 101, 102)), testTree.visit(VisitType.DFS));
        assertFalse(testTree.insert(10)); //trying to insert element that exists
        assertEquals(testTree.size, 13); //asserting that size didn't change after false insert

        assertFalse(testTree.delete(-100));
        assertEquals(testTree.size, 13); //asserting that size didn't change after false deletion

        assertTrue(testTree.delete(4));
        assertTrue(testTree.delete(9));
        assertTrue(testTree.delete(20));
        assertTrue(testTree.delete(101));
        assertTrue(testTree.delete(-3));
        assertTrue(testTree.delete(33));

        //This should be the inorder ordering of the tree after deletion
        assertEquals(new ArrayList<>(Arrays.asList(8, 10, 16, 42, 72, 90, 102)), testTree.visit(VisitType.DFS));
        assertEquals(testTree.size, 7); //asserting the size after large deletion
    }

    @Test
    void height_focusedTest() {
        RB<Integer> testTree = new RB<>();

        //checking height after insertions
        assertEquals(testTree.getHeight(), -1);
        assertTrue(testTree.insert(10));
        assertEquals(testTree.getHeight(),  0);
        assertTrue(testTree.insert(45));
        assertEquals(testTree.getHeight(),  1);
        assertTrue(testTree.insert(47));
        assertEquals(testTree.getHeight(),  1);
        assertTrue(testTree.insert(85));
        assertEquals(testTree.getHeight(),  2);
        assertTrue(testTree.insert(12));
        assertEquals(testTree.getHeight(),  2);
        assertTrue(testTree.insert(90));
        assertEquals(testTree.getHeight(),  2);
        assertTrue(testTree.insert(100));
        assertEquals(testTree.getHeight(),  3);
        assertTrue(testTree.insert(9));
        assertEquals(testTree.getHeight(),  3);
        assertTrue(testTree.insert(84));
        assertEquals(testTree.getHeight(),  3);

        //checking height after some deletions
        assertTrue(testTree.delete(9));
        assertEquals(testTree.getHeight(),  3);
        assertTrue(testTree.delete(85));
        assertEquals(testTree.getHeight(),  3);
        assertTrue(testTree.delete(45));
        assertEquals(testTree.getHeight(),  2);
        assertTrue(testTree.delete(47));
        assertEquals(testTree.getHeight(),  2);
        assertTrue(testTree.delete(90));
        assertEquals(testTree.getHeight(),  2);
        assertTrue(testTree.delete(100));
        assertEquals(testTree.getHeight(),  1);
        assertTrue(testTree.delete(12));
        assertEquals(testTree.getHeight(),  1);
        assertTrue(testTree.delete(84));
        assertEquals(testTree.getHeight(),  0);
        assertTrue(testTree.delete(10));
        assertEquals(testTree.getHeight(),  -1);
    }

    @Test
    void coloring_focusedTest(){
        RB<Integer> testTree = new RB<>();
        assertTrue(testTree.insert(10));
        assertTrue(testTree.insert(45));
        assertTrue(testTree.insert(47));
        assertTrue(testTree.insert(85));
        assertTrue(testTree.insert(12));
        assertTrue(testTree.insert(74));
        assertTrue(testTree.insert(37));
        assertTrue(testTree.insert(9));
        assertTrue(testTree.insert(84));
        assertTrue(testTree.insert(23));
        assertTrue(testTree.insert(78));
        assertTrue(testTree.insert(2));
        assertTrue(testTree.insert(79));
        assertTrue(testTree.insert(7));
        assertTrue(testTree.insert(31));
        assertTrue(testTree.insert(66));
        assertTrue(testTree.insert(99));
        assertTrue(testTree.insert(17));
        assertTrue(testTree.insert(33));
        assertTrue(testTree.insert(16));
        assertTrue(testTree.insert(55));
        assertTrue(testTree.insert(5));
        assertTrue(testTree.insert(70));
        assertTrue(testTree.insert(11));
        assertEquals(testTree.getSize(), 24);  //check size

        //arraylist to store colors of nodes in preorder where 1 means black node and 0 means red node
        ArrayList<Integer> colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0)));

        //check coloring after some normal and corner deletions
        assertTrue(testTree.delete(31));  //actual delete red node
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0)));
        assertTrue(testTree.delete(33));  //actual delete black node that have black sibling and far red nephew
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0)));
        assertTrue(testTree.delete(16));  //actual delete black node that have black sibling, far black nephew and near red nephew
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0)));
        assertTrue(testTree.delete(17));  //actual delete black node that have black sibling and two black nephews
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0)));
        assertTrue(testTree.delete(12));
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0)));
        assertTrue(testTree.delete(23));  //actual delete black node that have red sibling
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0)));
        assertTrue(testTree.delete(47));  //actual delete black node that have black sibling, far red nephew and near black nephew
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0)));

        //check coloring after some normal and corner insertions
        assertFalse(testTree.insert(10));  //insert already exist key
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0)));
        assertTrue(testTree.insert(80));
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0)));
        assertTrue(testTree.insert(81));
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0)));
        assertTrue(testTree.insert(105));
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0)));
        assertTrue(testTree.insert(1));
        colors = testTree.coloredDFS();
        assertEquals(colors, new ArrayList<>(Arrays.asList(1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0)));
    }

    @Test
    void all_focusedTest(){
        RB<String> testTree = new RB<>();
        assertTrue(testTree.insert("morning"));
        assertTrue(testTree.insert("creation"));
        assertTrue(testTree.insert("sacrifice"));
        assertTrue(testTree.insert("appear"));
        assertTrue(testTree.insert("front"));
        assertTrue(testTree.insert("23.451"));
        assertTrue(testTree.insert("interrupt"));
        assertTrue(testTree.insert("pick"));
        assertTrue(testTree.insert("college"));
        assertTrue(testTree.insert("fair"));
        assertTrue(testTree.insert("jealous"));
        assertTrue(testTree.insert( "4.7"));
        assertTrue(testTree.insert("16.030405"));
        assertTrue(testTree.insert("flex"));
        assertTrue(testTree.insert("bounce"));
        assertTrue(testTree.insert("shallow"));
        assertTrue(testTree.insert("21.245399"));
        assertEquals(testTree.size, 17); //checking for size after large insertion

        assertTrue(testTree.search("bounce"));

        //This is the final inorder ordering of the tree
        assertEquals(new ArrayList<>(Arrays.asList("16.030405", "21.245399", "23.451", "4.7", "appear", "bounce", "college", "creation", "fair", "flex", "front", "interrupt", "jealous", "morning", "pick", "sacrifice", "shallow")), testTree.visit(VisitType.DFS));

        assertFalse(testTree.search("software")); //searching for an element that doesn't exist

        //Trying to mass insert elements some of which are in the tree already then checking to be correct
        assertTrue(testTree.insert("forbid"));
        assertFalse(testTree.insert("college"));
        assertTrue(testTree.insert("pin"));
        assertFalse(testTree.insert("fair"));
        assertTrue(testTree.insert("hemisphere"));
        assertTrue(testTree.insert("arena"));
        assertFalse(testTree.insert("front")); //trying to reinsert the root
        assertFalse(testTree.insert("bounce")); //trying to insert an existing leaf
        assertEquals(testTree.size, 21); //check that size didn't change after 3 failed insertion

        //mass deletion then check size
        assertTrue(testTree.delete("shallow"));
        assertTrue(testTree.delete("creation"));
        assertTrue(testTree.delete("jealous"));
        assertTrue(testTree.delete("front"));
        assertTrue(testTree.delete("4.7"));
        assertTrue(testTree.delete("pick"));
        assertFalse(testTree.search("shallow")); //search for element that just got deleted
        assertEquals(testTree.size, 15); //check the size after large deletion

        //reinserting elements that were just deleted
        assertTrue(testTree.insert("front"));
        assertTrue(testTree.insert("jealous"));
        assertTrue(testTree.insert("shallow"));
        assertTrue(testTree.search("shallow"));
        assertEquals(testTree.size, 18); //check the size after insertion

        assertEquals(new ArrayList<>(Arrays.asList("16.030405", "21.245399", "23.451", "appear", "arena", "bounce", "college", "fair", "flex", "forbid", "front", "hemisphere", "interrupt", "jealous", "morning", "pin", "sacrifice", "shallow")), testTree.visit(VisitType.DFS));
        assertEquals("hemisphere", testTree.getRoot().getData());

        //checking the colour of some nodes to make sure of the correctness of colours
        assertFalse(testTree.getRoot().getRight().getLeft().getRight().isBlack());
        assertFalse(testTree.getRoot().getLeft().getRight().isBlack());
        assertFalse(testTree.getRoot().getLeft().getRight().getLeft().getLeft().isBlack());
    }

}