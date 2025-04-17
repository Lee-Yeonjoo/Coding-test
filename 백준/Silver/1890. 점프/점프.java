import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//dp
public class Main {
    static int N;
    static int[][] gameBoard;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        gameBoard = new int[N+1][N+1];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                gameBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //dp값 -1로 초기화. 경로의 최대 개수가 int범위를 넘으므로 long으로 선언 
        dp = new long[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -1);  //2차원 배열은 Arrays.fill 한 번으로 초기화가 안됨
        }

        //dp 초기값 저장 - 출발지에서 갈 수 있는 위치에 1 저장
        int temp = 1 + gameBoard[1][1];
        if (temp >= 1 && temp < N + 1) {
            dp[1][temp] = 1;
            dp[temp][1] = 1;
        }

        System.out.println(solve(N, N));
        br.close();
    }

    static long solve(int x, int y) {

        //이미 저장한 dp값이 있다면 리턴 
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        long sum = 0;
        //세로줄에 대해 탐색
        for (int i = 1; i <= x - 1; i++) {
            //만약 (x,y)로 이동이 가능한 경우라면 sum에 더한다.
            if (i == (x - gameBoard[i][y])) {
                sum += solve(i, y);
            }
        }

        //가로줄에 대해 탐색
        for (int i = 1; i <= y - 1; i++) {
            if (i == (y - gameBoard[x][i])) {
                sum += solve(x, i);
            }
        }

        dp[x][y] = sum;
        return sum;
    }
}
