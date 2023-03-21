package Data_Structures.Tree;

import Data_Structures.Tree.HelpingClasses.TreeCloner;
import Service.FileManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComparisonTests {
    public static <T> long measureRunningTime(Consumer<T> function, T argument) {
        long startTime = System.nanoTime();
        function.accept(argument);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    @Test
    void comparison_singleInsertion() throws CloneNotSupportedException, IOException{
        BST<Integer> red_black = new RB<>();
        BST<Integer> AVL = new AVL<>();
        TreeCloner<Integer> rb_cloner = new TreeCloner<>(red_black);
        TreeCloner<Integer> avl_cloner = new TreeCloner<>(AVL);
        List<Long> rb_insertionTimes = new ArrayList<>();
        List<Long> avl_insertionTimes = new ArrayList<>();
        int[] inputValues = {-207,-94,-173,-228,-112,-75,-150,157,-45,-40,-189,112,173,-214,-25,200,152,-68,109,-126,92,-70,212,128,-233,139,140,236,31,44,-138,-215,88,133,19,-95,14,-222,6,-185,245,89,210,4,114,-210,208,233,47,-165,15,78,237,217,-179,-160,-91,51,151,-98,181,-28,-143,96,160,49,-146,61,166,215,94,202,-175,0,225,75,62,115,243,-124,23,205,192,-13,159,-142,32,164,147,161,93,218,-16,-104,55,-66,134,7,176,242};

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

            FileManager.writeToFile(rb_insertionTimes, "Red-Black insertion time values.txt");
            FileManager.writeToFile(avl_insertionTimes, "AVL insertion time values.txt");

            red_black.insert(inputValues[j]);
            AVL.insert(inputValues[j]);
        }
    }

    @Test
    void comparison_singleDeletion() throws CloneNotSupportedException, IOException{
        BST<Integer> red_black = new RB<>();
        BST<Integer> AVL = new AVL<>();
        TreeCloner<Integer> rb_cloner = new TreeCloner<>(red_black);
        TreeCloner<Integer> avl_cloner = new TreeCloner<>(AVL);
        List<Long> rb_deletionTimes = new ArrayList<>();
        List<Long> avl_deletionTimes = new ArrayList<>();
        int[] inputValues = {-207,-94,-173,-228,-112,-75,-150,157,-45,-40,-189,112,173,-214,-25,200,152,-68,109,-126,92,-70,212,128,-233,139,140,236,31,44,-138,-215,88,133,19,-95,14,-222,6,-185,245,89,210,4,114,-210,208,233,47,-165,15,78,237,217,-179,-160,-91,51,151,-98,181,-28,-143,96,160,49,-146,61,166,215,94,202,-175,0,225,75,62,115,243,-124,23,205,192,-13,159,-142,32,164,147,161,93,218,-16,-104,55,-66,134,7,176,242};

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

            FileManager.writeToFile(rb_deletionTimes, "Red-Black deletion time values.txt");
            FileManager.writeToFile(avl_deletionTimes, "AVL deletion time values.txt");

            red_black.delete(inputValues[j]);
            AVL.delete(inputValues[j]);
        }
    }
}
