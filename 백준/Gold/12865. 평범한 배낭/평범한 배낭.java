import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP - 냅색 문제
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] weights = new int[N + 1];
        int[] values = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][K + 1];  //i번째 물건을 넣는 경우와 안 넣는 경우 중 가방 무게가 j일 때 가치의 최댓값
        for (int i = 1; i < N + 1; i++) {
            for (int j = 0; j < K + 1; j++) {
                if (j >= weights[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j - weights[i]] + values[i], dp[i - 1][j]);  //i번째 물건을 넣는 경우와 안 넣는 경우 중 더 큰 값 선택
                } else {
                    dp[i][j] = dp[i-1][j];  //현재 물건을 못 넣는 무게면, 이전 무게를 그대로 가져와야함!!!! 이거 때문에 틀림
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}
