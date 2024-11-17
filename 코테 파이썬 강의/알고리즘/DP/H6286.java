import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class H6286 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] L = new int[N][K];
        int[][][] mTime = new int[N-1][K][K];

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < K; j++) {
                L[i][j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < K; j++) {
                for (int s = 0; s < K; s++) {
                    if (j == s) {
                        mTime[i][j][s] = 0;
                    } else {
                        mTime[i][j][s] = Integer.parseInt(st.nextToken());
                    }
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            L[N-1][i] = Integer.parseInt(st.nextToken());
        }

        //입력 끝

        int[][] dp = new int[N][K];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, 1000000001));  //매우 큰 수로 dp배열 초기화

        for (int i = 0; i < K; i++) {
            dp[0][i] = L[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < K; j++) {
                for (int s = 0; s < K; s++) {
                    dp[i][j] = Math.min(dp[i][j], L[i][j] + dp[i-1][s] + mTime[i-1][s][j]);
                }
            }
        }

        int result = 1000000001;

        for (int i = 0; i < K; i++) {
            if (dp[N - 1][i] < result) {
                result = dp[N - 1][i];
            }
        }

        System.out.println(result);
    }
}
