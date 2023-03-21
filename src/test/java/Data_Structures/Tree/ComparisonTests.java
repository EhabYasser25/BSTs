package Data_Structures.Tree;
import org.junit.jupiter.api.Test;

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
}
