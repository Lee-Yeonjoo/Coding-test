import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//DFS, BFS
public class Main {
    static int N, M;
    static char[][] map;
    static int count;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());  //가로 - 문제 잘 읽기!!@!
        N = Integer.parseInt(st.nextToken());  //세로
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        visited = new boolean[N][M];
        int white = 0;
        int blue = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, map[i][j]);
                    int result = count * count;
                    if (map[i][j] == 'W') {
                        white += result;
                    } else blue += result;
                }
            }
        }
        System.out.println(white + " " + blue);
    }

    public static void bfs(int x, int y, char color) {
        Queue<Node> q = new ArrayDeque<>();  //뭔 차이인지 알아보기
        q.add(new Node(x, y, color));
        visited[x][y] = true;
        count = 1;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny] && map[nx][ny] == curr.color) {
                    q.add(new Node(nx, ny, map[nx][ny]));
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
    }
}

class Node {
    int x;
    int y;
    char color;
    Node(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}