import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS
public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;
    static int[][] graph, answer;

    static class Node {

        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        answer = new int[n][m];

        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());

                //원래 갈 수 없는 땅을 제외하고 -1로 초기화! 전부 다 -1로 초기화하면 0이 출력돼야할 부분에 -1이 출력돼서 틀림!!
                if (graph[i][j] != 0) {
                    answer[i][j] = -1;  //-1로 초기화. 도달할 수 없는 위치
                }
                
                //목표 지점 저장
                if (graph[i][j] == 2) {
                    x = i;
                    y = j;
                }
            }
        }

        bfs(x, y);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void bfs(int x, int y) {

        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        q.add(new Node(x, y, 0));
        visited[x][y] = true;

        while (!q.isEmpty()) {

            Node currNode = q.poll();

            //최단 겨리 저장
            answer[currNode.x][currNode.y] = currNode.dist;

            for (int i = 0; i < 4; i++) {

                int nx = currNode.x + dx[i];
                int ny = currNode.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (graph[nx][ny] == 0) {
                    answer[nx][ny] = 0;  //갈 수 없는 땅 표시
                    continue;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, currNode.dist + 1));
                }
            }
        }

    }
}
