import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//bfs
public class Main {

    static int count = 0;  //한 번 탐색할 때의 가장 먼 거리를 세는 변수
    static int maxCost = 0;  //count들 중 가장 큰 값을 저장. answer 
    static int N, M;

    //상하좌우 방향벡터
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] treasureMap;

    static class Node {

        int x;
        int y;
        int cost;  //각 노드의 최단 거리 

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        treasureMap = new char[N][M];
        for (int i = 0; i < N; i++) {
            String x = br.readLine();
            for (int j = 0; j < M; j++) {
                treasureMap[i][j] = x.charAt(j);
            }
        }

        //모든 Land 노드에 대해 bfs 탐색, 최단 거리 구하고, max값이면 갱신 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (treasureMap[i][j] == 'L') {
                    count = 0;
                    bfs(i, j);
                    maxCost = Math.max(maxCost, count);
                }
            }
        }

        System.out.println(maxCost);
    }

    static void bfs(int x, int y) {

        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new Node(x, y, 0));
        visited[x][y] = true;

        while (!q.isEmpty()) {

            Node currNode = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = currNode.x + dx[i];
                int ny = currNode.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny] && treasureMap[nx][ny] == 'L') {
                    count = currNode.cost + 1;  //가장 거리가 먼 곳을 구하기 위해 저장 
                    q.add(new Node(nx, ny, currNode.cost + 1));
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
