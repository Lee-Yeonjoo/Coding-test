import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] nums;

    //상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static boolean[][] visited;

    static int answer = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];

        //모든 칸에 대해 각 칸을 기준으로 4칸 탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 1, nums[i][j]);
                visited[i][j] = false;  //마지막꺼도 백트래킹 해줘야함!!! 주의!

                //예외) dfs로 안나오는 ㅗ,ㅜ,ㅓ,ㅏ모양에 대해 추가로 탐색
                exception(i, j);
            }
        }

        System.out.println(answer);
    }

    static void dfs(int x, int y, int count, int sum) {  //count는 4칸인지 세는 변수, sum은 4칸의 합을 저장

        visited[x][y] = true;

        //4칸째면 더 깊게 탐색x
        if (count == 4) {
            answer = Math.max(answer, sum);  //최댓값보다 크면 갱신
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }

            if (!visited[nx][ny]) {
                dfs(nx, ny, count + 1, sum + nums[nx][ny]);  //칸 수를 증가시켜 전달
                visited[nx][ny] = false;  //백트래킹 - 4칸이 되는 모든 경우의 수를 탐색하기 위해
            }
        }
    }

    //예외) dfs로 안나오는 ㅗ모양에 대해 추가로 탐색
    static void exception(int i, int j) {

        int sum = nums[i][j];

        //ㅗ모양
        if (i > 0 && j < M - 2) {
            sum += nums[i][j + 1];
            sum += nums[i][j + 2];
            sum += nums[i - 1][j + 1];
            answer = Math.max(answer, sum);
        }

        //ㅜ모양
        if (i < N - 1 && j < M - 2) {
            sum = nums[i][j];
            sum += nums[i][j + 1];
            sum += nums[i][j + 2];
            sum += nums[i + 1][j + 1];
            answer = Math.max(answer, sum);
        }

        //ㅏ모양
        if (i < N - 2 && j < M - 1) {
            sum = nums[i][j];
            sum += nums[i + 1][j];
            sum += nums[i + 2][j];
            sum += nums[i + 1][j + 1];
            answer = Math.max(answer, sum);
        }

        //ㅓ모양
        if (i < N - 2 && j > 0) {
            sum = nums[i][j];
            sum += nums[i + 1][j];
            sum += nums[i + 2][j];
            sum += nums[i + 1][j - 1];
            answer = Math.max(answer, sum);
        }
    }
}
