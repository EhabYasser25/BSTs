package Data_Structures.Tree;

import Data_Structures.Node.NilNode;
import Data_Structures.Node.RBNode;

public class RB<T extends Comparable<T>>  extends BST<T> {

    private final RBNode<T> nil = new NilNode<>();
    public RB(T data) {
        this.root = new RBNode<>(data);
        this.root.setParent(nil);
        this.root.setLeft(nil);
        this.root.setRight(nil);
    }
    public RB(RBNode<T> root) {
        this.root = root;
        if(this.root.getParent() == null)
            this.root.setParent(nil);
        if(this.root.getRight() == null)
            this.root.setRight(nil);
        if(this.root.getLeft() == null)
            this.root.setLeft(nil);
    }
    public RB() {}
    public boolean insert(T data) {
        RBNode<T> newNode = new RBNode<>(data);
        newNode.setLeft(nil);
        newNode.setRight(nil);
        if(node_isNull(this.root)){
            this.root = newNode;
            return true;
        }
        if(node_isNull(super.insert(this.root, null, newNode)))
            return false;
        checkAndFix(newNode);
        return true;
    }
    public boolean delete(T key) {
        RBNode<T> node = (RBNode<T>) super.search(this.root, key);
        RBNode<T> deletedNode = (RBNode<T>) super.delete(node);
        fixDelete(deletedNode);
        if(deletedNode == this.root)
            this.root = null;
        else if(deletedNode.isLeftChild())
            deletedNode.getParent().setLeft(nil);
        else
            deletedNode.getParent().setRight(nil);
        if(node != null)
            size--;
        return node != null;
    }
    public RBNode<T> getRoot(){
        return (RBNode<T>) this.root;
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

    private void fixDelete(RBNode<T> node) {
        RBNode<T> DB = node;
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
                System.out.println("case 1");
                fixDelete(DB);
            }
            case 2 -> {  //when both sibling's children are black
                DB.getSibling().recolor();
                System.out.println("case 2");
                fixDelete(DB.getParent());
            }
            case 3 -> {  //when sibling's far child is red
                DB.getParent().swapColors(DB.getSibling());
                DB.getFarNephew().recolor();
                rotateNear(DB, DB.getParent());
                System.out.println("case 3");
            }
            case 4 -> {  //when sibling's near child is red and far child is black
                DB.getSibling().recolor();
                DB.getNearNephew().recolor();
                rotateFar(DB, DB.getSibling());
                System.out.println("case 4");
                fixDelete(DB);
            }
        }
    }

    private int getDeleteCase(RBNode<T> DB) {
        System.out.println("node: " + DB.getData());
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

}
