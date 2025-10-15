import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DP - LCS 복습
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i < str1.length() + 1; i++) {
            for (int j = 1; j < str2.length() + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {  //i번째 문자와 j번째 문자가 같은 경우 -> 증가
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {  //다를 경우, 더 큰 값으로
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[str1.length()][str2.length()]);
    }
}
