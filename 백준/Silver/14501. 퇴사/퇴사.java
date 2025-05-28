import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP - 시간초과
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] schedule = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            schedule[i][0] = Integer.parseInt(st.nextToken());
            schedule[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        int maxP = 0;
        //첫 상담이 N일을 넘기지 않는다면 초기화
        if (schedule[0][0] <= N) {
            dp[0] = schedule[0][1];
            maxP = dp[0];  //maxP 갱신
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (j + schedule[j][0] <= i) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }

            //i일의 상담이 N을 넘지 않으면 이익을 더함
            if (schedule[i][0] + i <= N) {
                dp[i] += schedule[i][1];
            }

            maxP = Math.max(maxP, dp[i]);
        }

        System.out.println(maxP);
    }
}
