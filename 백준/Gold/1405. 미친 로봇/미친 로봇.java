import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

//백트래킹, 브루트포스, dfs
public class Main {
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};  //동서남북
    static int[] dy = {1, -1, 0, 0};
    static double[] percent;
    static double answer = 0.0;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        percent = new double[4];
        for (int i = 0; i < 4; i++) {
            percent[i] = Integer.parseInt(st.nextToken()) * 0.01;
        }

        M = 2 * N + 1;
        visited = new boolean[M][M];  //(2N+1) * (2N+1) 크기
        dfs(N, N, 0, 1);  //중간에서 시작
        DecimalFormat df = new DecimalFormat("0.0###################");  //지수 표기 없애기
        System.out.println(df.format(answer));
    }

    static void dfs(int x, int y, int depth, double p) {
        visited[x][y] = true;

        //N번 이동한 경우
        if (depth == N) {
            answer += p;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= M) {
                continue;
            }

            if (!visited[nx][ny] && percent[i] != 0) {
                dfs(nx, ny, depth + 1, p * percent[i]);
                visited[nx][ny] = false;  //백트래킹
            }
        }
    }
}
