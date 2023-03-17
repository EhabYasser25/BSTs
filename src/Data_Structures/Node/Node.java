package Data_Structures.Node;

public class Node<T extends Comparable<T>> {
    protected T data;
    public Node<T> parent = null;
    public Node<T> left = null;
    public Node<T> right = null;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
            return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public int compareTo(T o) {
        return this.data.compareTo(o);
    }

}
