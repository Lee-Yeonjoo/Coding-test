import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {

        int num;
        boolean isLadder;
        boolean isSnake;

        public Node(int num, boolean isLadder, boolean isSnake) {
            this.num = num;
            this.isLadder = isLadder;
            this.isSnake = isSnake;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> board = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            board.add(new ArrayList<>());
        }

        for (int i = 1; i < 101; i++) {
            for (int j = i + 1; j <= i + 6; j++) {
                if (j <= 100) {
                    board.get(i).add(new Node(j, false, false));
                }
            }
        }

        //사다리 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeNum = Integer.parseInt(st.nextToken());
            board.get(nodeNum).clear();
            board.get(nodeNum).add(new Node(Integer.parseInt(st.nextToken()), true, false));
        }

        //뱀 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeNum = Integer.parseInt(st.nextToken());
            board.get(nodeNum).clear();
            board.get(nodeNum).add(new Node(Integer.parseInt(st.nextToken()), false, true));
        }

        /*for (ArrayList<Node> nodes : board) {
            for (Node node : nodes) {
                System.out.print(node.num + "/" + node.isLadder + "/" + node.isSnake + " ");
            }
            System.out.println();
        }*/

        boolean[] visited = new boolean[101];
        int[] diceCount = new int[101];
        Arrays.fill(diceCount, 1000);
        diceCount[1] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {

            int currNum = q.poll();

            if (currNum == 100) break;

            for (Node nextNode : board.get(currNum)) {

                //만약 100번 노드라면


                //만약 다음 노드가 사다리나 뱀이 있다면
                if (nextNode.isLadder || nextNode.isSnake) {
                    //System.out.println(currNum + " 다음노드: " + nextNode.num);
                    if (diceCount[nextNode.num] > diceCount[currNum]) {
                        diceCount[nextNode.num] = diceCount[currNum];
                        //visited[nextNode.num] = true;
                    }
                }

                if (!visited[nextNode.num]) {
                    visited[nextNode.num] = true;
                    q.add(nextNode.num);

                    //System.out.println(nextNode.num + " " + diceCount[nextNode.num] + "  현재 노드:" + currNum + (diceCount[currNum] + 1));
                      //주사위 굴리는 횟수 증가
                    //System.out.println(nextNode.num + " " + diceCount[nextNode.num]);
                }

                if (diceCount[nextNode.num] > diceCount[currNum] + 1)
                    diceCount[nextNode.num] = diceCount[currNum] + 1;
            }
        }

        System.out.println(diceCount[100]);
       /* for (int i : diceCount) {
            System.out.print(i + " ");
        }*/
        br.close();
    }
}
