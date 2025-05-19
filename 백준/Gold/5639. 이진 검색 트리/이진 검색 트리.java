import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static class Node {
        int num;
        Node lChild;
        Node rChild;

        public Node(int num, Node lChild, Node rChild) {
            this.num = num;
            this.lChild = lChild;
            this.rChild = rChild;
        }

        //트리에 n을 후손으로 추가
        void insert(int n) {
            if (n < this.num) {
                if (this.lChild == null) {
                    this.lChild = new Node(n, null, null);
                } else {
                    this.lChild.insert(n);
                }
            } else {
                if (this.rChild == null) {
                    this.rChild = new Node(n, null, null);
                } else {
                    this.rChild.insert(n);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //처음 입력받는 수는 무조건 루트 노드
        Node root = new Node(Integer.parseInt(br.readLine()), null, null);
        String input;
        while ((input = br.readLine()) != null) {
            root.insert(Integer.parseInt(input));
        }

        postOrderTraverse(root);
        System.out.println(sb);
    }

    //후위 순회
    static void postOrderTraverse(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraverse(node.lChild);
        postOrderTraverse(node.rChild);
        sb.append(node.num).append("\n");
    }
}
