import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//DP
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] energy = new int[N][N];
        StringTokenizer st;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            energy[i][0] = Integer.parseInt(st.nextToken());  //i번 돌에서 한 칸 점프할 때의 에너지
            energy[i][1] = Integer.parseInt(st.nextToken());  //i번 돌에서 두 칸 점프할 때의 에너지
        }
        int K = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][2];
        //큰 수로 초기화
        for (int i = 1; i < N + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[1][0] = 0;  //K점프 x
        dp[1][1] = 0;  //K점프 o

        for (int i = 2; i < N + 1; i++) {
            //K점프 없이 최소 에너지
            dp[i][0] = dp[i - 1][0] + energy[i - 1][0];
            if (i > 2) {
                dp[i][0] = Math.min(dp[i][0], dp[i - 2][0] + energy[i - 2][1]);
            }

            //K점프 포함한 최소 에너지
            dp[i][1] = dp[i - 1][1] + energy[i - 1][0];
            if (i > 2) {
                dp[i][1] = Math.min(dp[i][1], dp[i - 2][1] + energy[i - 2][1]);
            }
            if (i > 3) {  //i번 돌로 올 때 K 점프를 사용하는 경우 -> 이전엔 K 점프를 안해야함
                dp[i][1] = Math.min(dp[i][1], dp[i - 3][0] + K);
            }
        }
        System.out.println(Math.min(dp[N][0], dp[N][1]));
    }
}
