package Data_Structures.Node;

public class Node<T extends Comparable> implements Comparable<Node>{
    protected T data;
    Node<T> parent = null;
    Node<T> left = null;
    Node<T> right = null;

    public T getData() {
            return data;
    }

    public void setData(T data){
        this.data = data;
    }

    @Override
    public int compareTo(Node o) {
        return this.data.compareTo(o.data);
    }

}
