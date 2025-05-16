import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] path = new int[100001];  //자신의 이전 노드를 기록
    static class Node {
        int num;
        int time;

        public Node(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        //N이 더 크거나 같은 경우
        if (N >= K) {
            System.out.println(N - K);
            for (int i = N; i >= K; i--) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
            return;
        }

        //N이 K보다 작은 경우 bfs
        int time = bfs(N);
        System.out.println(time);
        int i = K;
        sb.append(K);
        //경로 출력
        while (i != N) {
            sb.insert(0, path[i] + " ");
            i = path[i];
        }
        System.out.println(sb);
    }

    static int bfs(int start) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        int[] dx = {-1, 1};

        q.add(new Node(start, 0));
        visited[start] = true;
        path[start] = start;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            //현재 노드가 K인 경우, 탐색 종료 
            if (curr.num == K) {
                return curr.time;
            }

            for (int i = 0; i < 3; i++) {
                int nx = 0;
                if (i == 2) {  //-1, 1, *2 방향으로 탐색 
                    nx = curr.num * 2;
                } else nx = curr.num + dx[i];
                
                if (nx < 0 || nx >= 100001) {
                    continue;
                }

                if (!visited[nx]) {
                    q.add(new Node(nx, curr.time + 1));
                    visited[nx] = true;
                    path[nx] = curr.num;  //부모 노드를 저장하여 경로 기록
                }
            }
        }
        return -1;  //동생을 못 찾은 경우(없긴 함)
    }
}
