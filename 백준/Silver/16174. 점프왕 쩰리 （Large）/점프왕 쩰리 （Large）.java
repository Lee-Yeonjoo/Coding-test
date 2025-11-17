import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DFS
public class Main {
    static boolean[][] visited;
    static int N;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        dfs(0, 0);
        System.out.println("Hing");
    }

    public static void dfs(int x, int y) {
        visited[x][y] = true;
        //도착
        if (x == N - 1 && y == N - 1) {
            System.out.println("HaruHaru");
            System.exit(0);
        }

        for (int i = 0; i < 2; i++) {
            int nx = x + map[x][y] * dx[i];
            int ny = y + map[x][y] * dy[i];

            if (nx >= N || ny >= N) {
                continue;
            }

            if (!visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }
}
