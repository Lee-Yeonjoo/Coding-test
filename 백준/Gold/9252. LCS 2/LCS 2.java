import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DP - LCS 문제
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        //LCS1 문제와 같은 방법
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];  //0을 이용할거라 하나 더 큰 크기로 선언
        for (int i = 1; i < str1.length() + 1; i++) {
            for (int j = 1; j < str2.length() + 1; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        //dp에 저장된 값을 바탕으로 LCS 문자열 구하기
        //문자열 뒤쪽부터 구해야함
        int i = str1.length();
        int j = str2.length();
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            //왼쪽 칸의 수나 위쪽 칸의 수와 같다면 거기로 이동
            if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            }
            //왼쪽 칸, 위쪽 칸의 수들과 다르다면 현재 칸의 알파벳이 LCS 길이를 증가시킨 것이므로 문자열에 포함되어야 한다.
            else {
                lcs.insert(0, str1.charAt(i - 1));  //뒤쪽부터 구하기 때문에 문자열 앞에 포함시킨다.
                //i,j 둘 다 감소시켜 다음 알파벳을 찾으러 이동
                i--;
                j--;
            }
        }

        System.out.println(dp[str1.length()][str2.length()]);
        System.out.println(lcs);
    }
}
