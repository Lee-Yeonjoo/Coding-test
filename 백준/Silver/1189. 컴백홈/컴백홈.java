import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;  //값 유지하기 위해 static 변수
    static int R,C,K;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new int[R][C];

        for (int i = 0; i < R; i++) {
            String x = br.readLine();
            for (int j = 0; j < C; j++) {
                //못 가는 부분 표시
                if (x.charAt(j) == 'T') {
                    visited[i][j] = -1;
                }
            }
        }

        dfs(R - 1, 0, visited, 1, R, C, K);
        System.out.println(answer);
    }

    public static void dfs(int i, int j, int[][] visited, int k, int R, int C, int K) {
        //도착했고, 거리도 K와 같다면 조건을 만족하므로 answer 카운트
        if (i == 0 && j == C - 1 && k == K) {
            answer++;
            return;
        }

        //거리K는 만족하지 않지만 도착지에 왔다면 종료
        if (i == 0 && j == C - 1) {
            return;
        }

        visited[i][j] = 1;  //방문 표시

        //이웃 탐색
        for (int s = 0; s < 4; s++) {
            int nx = i + dx[s];
            int ny = j + dy[s];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }

            if (visited[nx][ny] == 0) {
                dfs(nx, ny, visited, k+1, R, C, K);
                //백트래킹
                visited[nx][ny] = 0;
            }
        }
    }
}
