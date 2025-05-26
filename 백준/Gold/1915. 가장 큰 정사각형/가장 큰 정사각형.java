import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] square = new int[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                square[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
            }
        }

        int[][] dp = new int[N][M];  //(i,j)가 정사각형의 오른쪽 아래 꼭짓점이 될 때의 가장 큰 정사각형의 변의 길이를 저장
        int maxSquare = 0;  //dp 값을 채울 때 가장 큰 값을 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //입력값이 0이면 패스
                if (square[i][j] == 0) {
                    continue;
                }

                //(i-1, j) (i-1, j-1) (i, j-1)의 값을 탐색
                if (i - 1 < 0 || j - 1 < 0) {  //범위에서 벗어나므로 가장 큰 정사각형의 변의 길이는 무조건 1이 됨
                    dp[i][j] = 1;
                } else {
                    //3개의 값 중 최소값보다 1 커진다.
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
                }

                if (maxSquare < dp[i][j]) {
                    maxSquare = dp[i][j];
                }
            }
        }
        System.out.println(maxSquare * maxSquare);
    }
}
