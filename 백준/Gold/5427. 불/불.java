import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//BFS
public class Main {
    static class Node {
        int x;
        int y;
        int time;
        boolean isFire;  //불인지 상근이인지
        public Node(int x, int y, int time, boolean isFire) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.isFire = isFire;
        }
    }
    static int w, h;
    static Queue<Node> q;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            q = new LinkedList<>();

            int x = 0, y = 0;
            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = input.charAt(j);
                    if (map[i][j] == '*') {
                        q.add(new Node(i, j, 0, true));
                    }
                    if (map[i][j] == '@') {
                        x = i;
                        y = j;
                    }
                }
            }
            bfs(x, y);
        }
    }

    static void bfs(int x, int y) {
        boolean[][] visited = new boolean[h][w];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        q.add(new Node(x, y, 0, false));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (!node.isFire) {
                //만약 상근이가 가장자리라면 탈출 가능
                if (node.x == 0 || node.x == h - 1 || node.y == 0 || node.y == w - 1) {
                    System.out.println(node.time + 1);
                    return;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
                    continue;
                }

                //불인 경우 - '.'나 '@' 자리로 이동 가능
                if (node.isFire && (map[nx][ny] == '.' || map[nx][ny] == '@')) {
                    q.add(new Node(nx, ny, node.time + 1, true));
                    map[nx][ny] = '*';  //불 표시(방문 처리)
                }

                //상근이인 경우
                if (!node.isFire && !visited[nx][ny]) {
                    if (map[nx][ny] == '.') {  //'.'일 경우만 가능
                        q.add(new Node(nx, ny, node.time + 1, false));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }
}
