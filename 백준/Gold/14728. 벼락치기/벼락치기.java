import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//DP, 0/1 냅색 문제
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int totalStudyTime = Integer.parseInt(st.nextToken());

        int[] studyTime = new int[N + 1];
        int[] score = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            studyTime[i] = Integer.parseInt(st.nextToken());
            score[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][totalStudyTime + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < totalStudyTime + 1; j++) {
                //i번째 단원을 공부하는 경우와 안하는 경우 중 점수가 높은 것으로 갱신
                if (j - studyTime[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j - studyTime[i]] + score[i], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];  //갱신 안하는 경우엔, i-1단원의 점수 그대로 가져와야 안틀림
                }
            }
        }
        System.out.println(dp[N][totalStudyTime]);
    }
}
