import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int root = Integer.parseInt(br.readLine());
        tree = new int[1000001][2];
        String input;
        while ((input = br.readLine()) != null) {
            int x = Integer.parseInt(input);

            int i = root;
            int j = root > x? 0 : 1;
            while (tree[i][j] != 0) {
                if (x < i) {
                    i = tree[i][0];
                } else {
                    i = tree[i][1];
                }
                j = i > x? 0 : 1;
            }
            tree[i][j] = x;
        }
        postOrderTraverse(root);
    }

    //후위 순회 함수
    static void postOrderTraverse(int num) {
        int leftChild = tree[num][0];
        int rightChild = tree[num][1];
        //왼쪽 자식이 있다면 왼쪽 자식을 루트로 하는 서브트리에 대해 후위 순회
        if (leftChild != 0) {
            postOrderTraverse(leftChild);
        }
        //오른쪽 자식이 있다면 오른쪽 서브트리 후위 순회
        if (rightChild != 0) {
            postOrderTraverse(rightChild);
        }
        //자식이 없거나, 서브트리의 순회가 끝나면 자기 자신 출력
        System.out.println(num);
    }
}
