import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line.split("")[j]);
                visited[i][j] = false;
            }
        }

        int cnt = 0;
        List<Integer> object = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    object.add(dfs(i, j, N, map, visited));
                    cnt++;
                }
            }
        }

        Collections.sort(object);

        StringBuilder sb = new StringBuilder();
        sb.append(cnt+"\n");
        for (Integer i : object) {
            sb.append(i+"\n");
        }
        System.out.print(sb);
    }

    public static int dfs(int x, int y, int N, int[][] map, boolean[][] visited) {

        visited[x][y] = true;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        int nx = 0;
        int ny = 0;
        int object = 1;
        for (int i = 0; i < 4; i++) {

            nx = x + dx[i];
            ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (map[nx][ny] == 1 && !visited[nx][ny]) {
                    object += dfs(nx, ny, N, map, visited);
                }
            }
        }

        return object;
    }
}