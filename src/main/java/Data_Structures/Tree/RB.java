package Data_Structures.Tree;

import Data_Structures.Node.NilNode;
import Data_Structures.Node.RBNode;

import java.util.ArrayList;

public class RB<T extends Comparable<T>>  extends BST<T> {
    /**
     * Each tree has its own root, so it is removed from the parent class
     * We can just add it for normal node, but it's preferred to make each tree has its own root!
     * */

    private final RBNode<T> nil = new NilNode<>();

    public RB(T data) {
        this.root = new RBNode<>(data);
        this.root.setLeft(nil);
        this.root.setRight(nil);
        this.size++;
    }
    public RB(RBNode<T> root) {
        this.root = root;
        if(this.root.getRight() == null)
            this.root.setRight(nil);
        if(this.root.getLeft() == null)
            this.root.setLeft(nil);
    }
    public RB() { }

    public RBNode<T> getRoot(){
        return (RBNode<T>) this.root;
    }

    public int getHeight() {
        return super.getHeight();
    }

    public boolean insert(T data){
        if(data == null)
            return false;
        RBNode<T> newNode = new RBNode<>(data);
        newNode.setLeft(nil);
        newNode.setRight(nil);
        if(node_isNull(this.root)){
            newNode.setBlack(true);
            this.root = newNode;
            size++;
            return true;
        }
        if(node_isNull(super.insert(this.root, null, newNode)))
            return false;
        checkAndFix(newNode);
        return true;
    }

    public boolean delete(T key) {
        if(key == null)
            return false;
        RBNode<T> node = (RBNode<T>) super.search(this.root, key);
        if(node == null)
            return false;
        RBNode<T> deletedNode = (RBNode<T>) super.delete(node);
        fixDelete(deletedNode);
        if(deletedNode == this.root)
            this.root = null;
        else if(deletedNode.isLeftChild())
            deletedNode.getParent().setLeft(nil);
        else
            deletedNode.getParent().setRight(nil);
        size--;
        this.getRoot().setBlack(true);
        return true;
    }

    private void checkAndFix(RBNode<T> node) {
        if(node == this.root || node.getParent() == this.root || node.getParent().isBlack())
            return;
        RBNode<T> uncle = (RBNode<T>) node.getUncle();
        if(uncle.isBlack())
            rotateFix(node); //rotate and done
        else
            colorFix(node); //color fix and recheck
    }
    private void colorFix(RBNode <T> node) {
        RBNode<T> parent = node.getParent();
        RBNode<T> grandParent = node.getParent().getParent();
        parent.setBlack(true);
        parent.getSibling().setBlack(true);
        grandParent.setBlack(false);
        checkAndFix(grandParent);
    }
    private void rotateFix(RBNode<T> node) {
        RBNode<T> parent = node.getParent();
        RBNode<T> grandParent = parent.getParent();
        int rotateType = rotate(node, parent, grandParent);
        switch (rotateType) {
            case 1, 0 -> {
                node.setBlack(false);
                parent.setBlack(true);
                grandParent.setBlack(false);
            }
            case 2, 3 -> {
                node.setBlack(true);
                parent.setBlack(false);
                grandParent.setBlack(false);
            }
        }
        ((RBNode<T>) this.root).setBlack(true);
    }

    private void fixDelete(RBNode<T> DB) {
        if(!DB.isBlack() || DB == root) {
            DB.setBlack(true);
            return;
        }
        int deleteCase = getDeleteCase(DB);
        switch (deleteCase) {
            case 1 -> {  //when sibling is red
                DB.getParent().recolor();
                DB.getSibling().recolor();
                rotateNear(DB, DB.getParent());
                fixDelete(DB);
            }
            case 2 -> {  //when both sibling's children are black
                DB.getSibling().recolor();
                fixDelete(DB.getParent());
            }
            case 3 -> {  //when sibling's far child is red
                DB.getParent().swapColors(DB.getSibling());
                DB.getFarNephew().recolor();
                rotateNear(DB, DB.getParent());
            }
            case 4 -> {  //when sibling's near child is red and far child is black
                DB.getSibling().recolor();
                DB.getNearNephew().recolor();
                rotateFar(DB, DB.getSibling());
                fixDelete(DB);
            }
        }
    }
    private int getDeleteCase(RBNode<T> DB) {
        if(!DB.getSibling().isBlack())  //when sibling is red
            return 1;
        else if(DB.getNearNephew().isBlack() && DB.getFarNephew().isBlack())  //when both sibling's children are black
            return 2;
        else if(!DB.getFarNephew().isBlack())  //when sibling's far child is red
            return 3;
        else if(!DB.getNearNephew().isBlack())  //when sibling's near child is red and far child is black
            return 4;
        return 0;
    }
    private void rotateNear(RBNode<T> DB, RBNode<T> node) {
        if (DB.isLeftChild())
            rotateLeft(node);
        else
            rotateRight(node);
    }
    private void rotateFar(RBNode<T> DB, RBNode<T> node) {
        if (DB.isLeftChild())
            rotateRight(node);
        else
            rotateLeft(node);
    }

    public ArrayList<Integer> coloredDFS() {
        ArrayList<Integer> colors = new ArrayList<>();
        recursiveColoredDFS((RBNode<T>)this.root, colors);
        return colors;
    }

    private void recursiveColoredDFS(RBNode<T> node, ArrayList<Integer> colors) {
        if(node instanceof NilNode<T>)
            return;
        colors.add(node.isBlack()? 1 : 0);
//        int color = node.isBlack()? 1 : 0;
//        System.out.print(node.getData() + ": " + color + ",  ");
        recursiveColoredDFS(node.getLeft(), colors);
        recursiveColoredDFS(node.getRight(), colors);
    }

}
