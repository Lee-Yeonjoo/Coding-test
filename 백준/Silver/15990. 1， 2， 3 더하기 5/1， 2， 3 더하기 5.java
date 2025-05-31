import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dp = new int[100001][4];
        int[] dpSum = new int[100001];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;
        dpSum[1] = 1;
        dpSum[2] = 1;
        dpSum[3] = 3;

        //모든 dp값 한 번에 구하기 - 테케마다 반복되는 것을 줄이기 위해..
        for (int i = 4; i <= 100000; i++) {
            for (int j = 1; j <= 3; j++) {
                dp[i][j] = (dpSum[i - j] - dp[i - j][j] + 1000000009) % 1000000009;  //j로 시작하는 조합의 가짓수, 음수가 될 수 있어서 1000000009를 더해준다..
                dpSum[i] = (dpSum[i] + dp[i][j]) % 1000000009;  //1로 시작하는 경우, 2로 시작하는 경우, 3으로 시작하는 경우의 합
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(dpSum[n]);
        }
    }
}
