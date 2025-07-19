import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DP - LCS문제인데, 공통 부분 문자열이 연속되어야함
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int max = 0;

        for (int i = 1; i < str1.length() + 1; i++) {
            for (int j = 1; j < str2.length() + 1; j++) {
                //i번째 문자와 j번째 문자가 같으면 dp[i-1][j-1]에 1 더한다. dp[i-1][j-1]은 직전 문자열의 연속된 최대 공통 부분 길이니까
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);  //동시에 최댓값도 찾는다.
                }
            }
        }
        System.out.println(max);
    }
}
