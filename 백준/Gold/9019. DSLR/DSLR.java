import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS
public class Main {

    static class Node {

        int num;  //숫자
        String command;  //num까지의 경로(=명령어 나열)를 저장

        Node (int num, String command) {
            this.num = num;
            this.command = command;
        }
    }

    static int A, B;
    static boolean[] visited;
    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            System.out.println(bfs(A));
        }
    }

    static String bfs(int n) {

        q = new LinkedList<>();
        q.add(new Node(n, ""));
        visited[n] = true;

        while (!q.isEmpty()) {
            Node currNode = q.poll();

            //목적지에 도착
            if (currNode.num == B) {
                return currNode.command;
            }

            //4가지 명령어로 될 수 있는 이웃 숫자를 탐색
            int adjNum;
            //D 명령일 때 갈 수 있는 이웃 숫자
            adjNum = (currNode.num * 2) % 10000;
            visitNode(adjNum, currNode.command, "D");

            //S
            adjNum = currNode.num - 1;
            if (adjNum < 0) {  //0보다 작아질 경우 9999로 변환
                adjNum = 9999;
            }
            visitNode(adjNum, currNode.command, "S");

            //L
            adjNum = (currNode.num % 1000) * 10 + (currNode.num / 1000);  //왼쪽으로 회전 -> ArrayList로 회전시키는거보다 수로 연산하는게 더 빠르다. ArrayList쓰니까 시간초과 남 
            visitNode(adjNum, currNode.command, "L");

            //R
            adjNum = 1000 * (currNode.num % 10) + (currNode.num / 10);  //오른쪽으로 회전 
            visitNode(adjNum, currNode.command, "R");
        }

        return "";
    }

    //이웃 숫자를 방문하고, 큐에 경로를 추가하여 넣는 함수
    private static void visitNode(int adjNum, String currCommand, String adjCommand) {
        if (!visited[adjNum]) {
            q.add(new Node(adjNum, currCommand + adjCommand));
            visited[adjNum] = true;
        }
    }
}
