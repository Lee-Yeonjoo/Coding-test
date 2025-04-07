import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    //방향 벡터 - 12시부터 시계 방향
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M;
    static int[][] farm;

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        farm = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                farm[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;  //산봉우리 개수 카운트
        //모든 농장 좌표에 대해 dfs
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boolean check = false;  //산봉우리 여부 표시
                //방문하지 않았고, 농장 높이가 0이면 산봉우리는 될 수 없으므로 0보다 클 때 dfs
                if (!visited[i][j] && farm[i][j] > 0) {
                    check = dfs(i, j, true);
                    //산봉우리가 맞으면 카운트
                    if (check) {
                        count ++;
                    }
                }
                //System.out.println("" + i + " " + j + " " + check + " " + count);
            }
        }

        System.out.println(count);
    }

    static boolean dfs(int x, int y, boolean check) {

        visited[x][y] = true;

        //이웃 좌표 8방향에 대해 탐색
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }

            //근처에 자기보다 큰 산이 있으므로 산봉우리가 될 수 없음 - 인접 위치를 다 탐색해야하기 때문에 return하면 안됨
            if (farm[x][y] < farm[nx][ny]) {
                check = false;
            }

            //방문하지 않았고, 현재 위치보다 작거나 같으면 인접한 위치에 대해서도 dfs
            if (!visited[nx][ny] && farm[x][y] == farm[nx][ny]) {
                check = dfs(nx, ny, check);
            }
        }
        return check;
    }
}
